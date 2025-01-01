package com.demo.First.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.First.Model.Marks;

@Repository
public interface MarksRepository extends JpaRepository<Marks,Long> {
    
}
