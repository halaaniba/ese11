package com.example.ese11.service;

import com.example.ese11.entities.Student;
import com.example.ese11.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student changeWorkingStatus(Long studentId, boolean isWorking) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()) return null;
        student.get().setWorking(isWorking);
        return studentRepository.save(student.get());
    }
}