package com.example.demo.http;

import com.example.demo.model.StudentEntity;
import com.example.demo.model.dto.StudentDTO;
import com.example.demo.service.StudentService;
import com.example.demo.service.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentService = studentServiceImpl;
    }

    @GetMapping
    public ResponseEntity<?> getStudents() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveStudent(@RequestBody StudentEntity student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteById(@PathVariable("studentId") Long studentId) {
        studentService.deletebyId(studentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable("studentId") Long studentId,
                                           @RequestBody StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.updateStudent(studentId, studentDTO), HttpStatus.OK);
    }
}
