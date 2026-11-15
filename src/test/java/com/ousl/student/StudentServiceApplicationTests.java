package com.ousl.student;

import com.ousl.student.controller.StudentController;
import com.ousl.student.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;

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
    private StudentController studentController;

    @Test
    void testFullStudentCrudFlow() {
        // Create
        Map<String, String> payload = Map.of(
                "registrationNo", "S2200017",
                "fullName", "Prabhath",
                "degreeProgram", "BSE Honours"
        );
        Student created = studentController.createStudent(payload);
        assertNotNull(created.getId());

        // Read All
        List<Student> all = studentController.getAllStudents();
        assertFalse(all.isEmpty());

        // Read By ID (Found & Not Found)
        ResponseEntity<Student> found = studentController.getStudentById(created.getId());
        assertEquals(200, found.getStatusCode().value());
        assertEquals(404, studentController.getStudentById(99999L).getStatusCode().value());

        // Delete (Found & Not Found)
        assertEquals(204, studentController.deleteStudent(created.getId()).getStatusCode().value());
        assertEquals(404, studentController.deleteStudent(99999L).getStatusCode().value());
    }
}