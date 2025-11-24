package com.example.demo.repository;

import com.example.demo.model.Student;
import com.example.demo.model.University;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:testdb"
})
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private UniversityRepository universityRepository;

    @Test
    void shouldSaveAndFindStudent() {
        // Given
        University university = new University();
        university.setName("Test University");
        University savedUniv = universityRepository.save(university);
        
        Student student = new Student();
        student.setName("John Doe");
        student.setAddress("123 Test Street");
        student.setUniversity(savedUniv);
        
        // When
        Student savedStudent = studentRepository.save(student);
        List<Student> students = studentRepository.findAll();
        
        // Then
        assertThat(students).hasSize(1);
        assertThat(students.get(0).getName()).isEqualTo("John Doe");
    }
    
    @Test
    void shouldFindStudentsByUniversity() {
        // Given
        University university = new University();
        university.setName("Paris University");
        University savedUniv = universityRepository.save(university);
        
        Student student = new Student();
        student.setName("Alice");
        student.setAddress("Paris");
        student.setUniversity(savedUniv);
        studentRepository.save(student);
        
        // When
        List<Object[]> results = studentRepository.findStudentsByUniversity("Paris University");
        
        // Then
        assertThat(results).hasSize(1);
        assertThat(results.get(0)[0]).isEqualTo("Alice");
        assertThat(results.get(0)[1]).isEqualTo("Paris University");
    }
}