package com.student.studentproject;

import com.student.studentproject.student.Student;
import com.student.studentproject.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void deleteStudentById(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("Student with the ID does not exist in the database");
        }
        studentRepository.deleteStudentById(studentId);
    }
}
