package com.student.studentproject.student;

import com.student.studentproject.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents(@RequestBody Student student){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email is taken");
        }
        studentRepository.save(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable ("studentId") Long studentId){
    studentService.deleteStudentById(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable ("studentId") Long studentId,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) String email){

            studentService.updateStudent(studentId, name, email);
    }
}
