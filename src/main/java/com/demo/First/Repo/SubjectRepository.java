package com.demo.First.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.First.Model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    
}
