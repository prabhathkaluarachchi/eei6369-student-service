package com.ousl.student.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String registrationNo;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String degreeProgram;

    public Student() {}

    public Student(String registrationNo, String fullName, String degreeProgram) {
        this.registrationNo = registrationNo;
        this.fullName = fullName;
        this.degreeProgram = degreeProgram;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRegistrationNo() { return registrationNo; }
    public void setRegistrationNo(String registrationNo) { this.registrationNo = registrationNo; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getDegreeProgram() { return degreeProgram; }
    public void setDegreeProgram(String degreeProgram) { this.degreeProgram = degreeProgram; }
}
