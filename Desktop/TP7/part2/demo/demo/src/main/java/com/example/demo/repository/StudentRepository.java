package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    
    @Query("SELECT s.name, u.name FROM Student s JOIN s.university u")
    List<Object[]> getAllStudentsUniversity();
    
    @Query("SELECT s.name, u.name FROM Student s JOIN s.university u WHERE u.name = :univName")
    List<Object[]> findStudentsByUniversity(@Param("univName") String univName);
}