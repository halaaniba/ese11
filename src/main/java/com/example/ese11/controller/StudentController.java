package com.example.ese11.controller;

import com.example.ese11.entities.Student;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.ese11.repository.StudentRepository;
import com.example.ese11.service.StudentService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @PostMapping("")
    public @ResponseBody Student create(@RequestBody Student student) {
        return studentRepository.save(student);

    }

    @GetMapping("/")
    public @ResponseBody List<Student> getList() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Student getSingle(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody Student update(@PathVariable Long id, @RequestBody @NonNull Student student) {
        student.setId(id);
        student.setName(student.getName());
        student.setSurname(student.getSurname());
        return studentRepository.save(student);
    }

    @PutMapping("/{id}/working")
    public @ResponseBody Student setStudentWorking(@PathVariable Long id, @RequestParam("working") boolean working) {
        return studentService.changeWorkingStatus(id, working);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }


}
