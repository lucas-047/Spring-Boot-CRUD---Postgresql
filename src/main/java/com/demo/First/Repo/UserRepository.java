package com.demo.First.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.First.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
}
