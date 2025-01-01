package com.demo.First;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldTest {
    @RequestMapping("/test")
    public String test(){
        return "Hello from test!!!";
    }
}
