package com.ousl.student;

import com.ousl.student.model.Student;
import com.ousl.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
})
class StudentServiceApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testCreateAndReadStudent() {
        Student student = new Student("S2200017", "Prabhath", "BSE Honours");
        Student saved = studentRepository.save(student);
        assertNotNull(saved.getId());
        assertEquals("Prabhath", studentRepository.findById(saved.getId()).get().getFullName());
    }
}