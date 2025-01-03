package com.demo.First;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import com.demo.First.Repo.UserRepository;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FirstApplicationTests {

	@LocalServerPort
	private int port;

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

	@Autowired
	static UserRepository userRepository;

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
	}

	@Test
	@Order(1)
	void shouldCreateUser() {
		String request = """
				{
				  "userName": "Bob",
				  "firstName": "Bob",
				  "lastName": "Sean",
				  "email": "bob@abc.com",
				  "role": "STUDENT",
				  "dateOfBirth": "2000-09-01",
				  "phoneNumber": "+1234567890",
				  "address": "378 Side Street, Anand , Gujarat , India"
				}
				""";
		Response response = RestAssured.given()
				.contentType("application/json")
				.body(request)
				.when()
				.post("/api/user");
		response.then()
				.statusCode(201)
				.log().body()
				.body("data", Matchers.nullValue())
				.body("message", Matchers.equalTo("User Created Successfully"))
				.body("httpStatus", Matchers.equalTo("CREATED"));
	}

	@Test
	@Order(2)
	void shouldGetAllUser() {
		Response response = RestAssured.given()
				.when()
				.get("/api/user");
		response.then()
				.statusCode(200)
				.log().body()
				.body("data", Matchers.hasSize(Matchers.greaterThan(0)))
				.body("data.userName", Matchers.hasItem("Bob"))
				.body("message", Matchers.equalTo("User list fetched Successfully"))
				.body("httpStatus", Matchers.equalTo("OK"));
	}

	@Test
	@Order(3)
	void shouldUpdateUser() {
		String request = """
				{
					"userId": 1,
					"userName": "Alice"
				}
				""";
		Response response = RestAssured.given()
				.contentType("application/json")
				.body(request)
				.when()
				.put("/api/user");
		response.then()
				.statusCode(200)
				.log().body()
				.body("data", Matchers.nullValue())
				.body("message", Matchers.equalTo("User Updated Successfully"))
				.body("httpStatus", Matchers.equalTo("OK"));
	}

	@Test
	@Order(4)
	void shouldGetUser() {
		Response response = RestAssured.given()
				.when()
				.get("/api/user/1");
		response.then()
				.statusCode(200)
				.log().body()
				.body("data.userName", Matchers.equalTo("Alice"))
				.body("message", Matchers.equalTo("User fetched Successfully"))
				.body("httpStatus", Matchers.equalTo("OK"));
	}

	@Test
	@Order(5)
	void shouldDeleteUser() {
		Response response = RestAssured.given()
				.when()
				.delete("/api/user/1");
		response.then()
				.statusCode(200)
				.log().body()
				.body("data", Matchers.nullValue())
				.body("message", Matchers.equalTo("User Deleted Successfully"))
				.body("httpStatus", Matchers.equalTo("OK"));
	}

	@Test
	@Order(6)
	void shouldNotGetUser() {
		Response response = RestAssured.given()
				.when()
				.get("/api/user/1");
		response.then()
				.statusCode(404)
				.log().body()
				.body("message", Matchers.equalTo("User Not Found"))
				.body("httpStatus", Matchers.equalTo("NOT_FOUND"));
	}

	@Test
	@Order(7)
	void shouldNotUpdateUser() {
		String request = """
				{
					"userId": 1,
					"userName": "Alice"
				}
				""";
		Response response = RestAssured.given()
				.contentType("application/json")
				.body(request)
				.when()
				.put("/api/user");
		response.then()
				.statusCode(404)
				.log().body()
				.body("message", Matchers.equalTo("User Not Found"))
				.body("httpStatus", Matchers.equalTo("NOT_FOUND"));
	}

	@Test
	@Order(8)
	void shouldNotDeleteUser() {
		Response response = RestAssured.given()
				.when()
				.delete("/api/user/1");
		response.then()
				.statusCode(404)
				.log().body()
				.body("message", Matchers.equalTo("User Not Found"))
				.body("httpStatus", Matchers.equalTo("NOT_FOUND"));
	}
}
