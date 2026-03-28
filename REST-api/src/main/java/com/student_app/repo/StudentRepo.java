package com.student_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student_app.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long>{

}
