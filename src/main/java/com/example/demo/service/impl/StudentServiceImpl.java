package com.example.demo.service.impl;

import com.example.demo.model.StudentEntity;
import com.example.demo.model.dto.StudentDTO;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentEntity> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<StudentEntity> findStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    public StudentEntity save(StudentEntity student) {
        Optional<StudentEntity> studentByEmail = findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalThreadStateException("E-mail taken.");
        }
        return studentRepository.save(student);
    }

    @Override
    public void deletebyId(Long studentId) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
        }
    }

    @Transactional
    public StudentEntity updateStudent(Long studentId, StudentDTO studentDTO) {
        StudentEntity student = studentRepository.findById(studentId)
                                           .orElseThrow(() -> new IllegalStateException("Student with id " +
                                                           studentId + " does not exist."));

        String name = studentDTO.getName();
        String email = studentDTO.getEmail();

        if(name != null && !name.isEmpty() && !Objects.equals(student.getName(), name))
            student.setName(name);

        if(email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            findStudentByEmail(email).ifPresent(s -> {throw new IllegalStateException("E-mail taken");});
            student.setEmail(email);
        }

        return student;
    }

}