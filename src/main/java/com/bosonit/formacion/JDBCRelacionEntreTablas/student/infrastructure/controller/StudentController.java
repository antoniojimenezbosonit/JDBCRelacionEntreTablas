package com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller;


import com.bosonit.formacion.JDBCRelacionEntreTablas.student.application.port.StudentServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.input.StudentInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output.StudentOutputDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("student")
public class StudentController {

    StudentServicePort studentServiceUseCase;

    @GetMapping
    public List<StudentOutputDTO> getAllStudent(){
        List<Student> studentList = new ArrayList<>();
        studentList = studentServiceUseCase.getAllStudent();
        return studentList.stream()
                .map( l -> new StudentOutputDTO(l))
                .collect(Collectors.toList());
    }


    @PostMapping("{id_person}/{id_teacher}")
    @Transactional(rollbackOn = Exception.class)
    public StudentOutputDTO addStudent(@PathVariable int id_person, @PathVariable String id_teacher, @RequestBody StudentInputDTO s){

        return studentServiceUseCase.createStudent(id_person, id_teacher, s);
    }

    @GetMapping("{id_student}")
    public StudentOutputDTO getStudentById(@PathVariable String id_student) {

        return studentServiceUseCase.getStudentByID(id_student);
    }

    @Transactional(rollbackOn = Exception.class)
    @DeleteMapping("{id}")
    public  String deletePerson(@PathVariable String id){

        studentServiceUseCase.deleteStudent(id);
        return "student delete";
    }

    @PutMapping("{id}")
    @Transactional(rollbackOn = Exception.class)
    public StudentOutputDTO updateStudent(@PathVariable String id, @RequestBody StudentInputDTO s){

           return studentServiceUseCase.updateStudent(id, s);
    }



}
