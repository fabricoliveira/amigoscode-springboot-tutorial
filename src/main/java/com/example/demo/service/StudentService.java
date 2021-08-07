package com.example.demo.service;

import com.example.demo.model.StudentEntity;
import com.example.demo.model.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentEntity> getStudents();

    Optional<StudentEntity> findStudentByEmail(String email);

    StudentEntity save(StudentEntity student);

    void deletebyId(Long studentId);

    StudentEntity updateStudent(Long studentId, StudentDTO studentDTO);
}
