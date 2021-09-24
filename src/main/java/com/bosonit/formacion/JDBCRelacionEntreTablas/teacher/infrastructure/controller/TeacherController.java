package com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller;


import com.bosonit.formacion.JDBCRelacionEntreTablas.student.application.port.StudentServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.input.StudentInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output.StudentOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.application.port.TeacherServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.input.TeacherInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.output.TeacherOutputDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("teacher")
public class TeacherController {

    TeacherServicePort teacherServiceUseCase;

    @GetMapping
    public List<TeacherOutputDTO> getAllTeacher(){
        List<Teacher> teacherList = new ArrayList<>();
        teacherList = teacherServiceUseCase.getAllTeacher();
        return teacherList.stream()
                .map( l -> new TeacherOutputDTO(l))
                .collect(Collectors.toList());
    }


    @PostMapping("{id_teacher}")
    @Transactional(rollbackOn = Exception.class)
    public TeacherOutputDTO addTeacher(@PathVariable int id_teacher, @RequestBody TeacherInputDTO t){

        return teacherServiceUseCase.createTeacher(id_teacher, t);
    }

    @GetMapping("{id_teacher}")
    public TeacherOutputDTO getTeacherById(@PathVariable String id_teacher) {

        return teacherServiceUseCase.getTeacherByID(id_teacher);
    }

    @Transactional(rollbackOn = Exception.class)
    @DeleteMapping("{id}")
    public  String deleteTeacher(@PathVariable String id){

        teacherServiceUseCase.deleteTeacher(id);
        return "teacher delete";
    }

    @PutMapping("{id}")
    @Transactional(rollbackOn = Exception.class)
    public TeacherOutputDTO updateTeacher(@PathVariable String id, @RequestBody TeacherInputDTO t){

           return teacherServiceUseCase.updateTeacher(id, t);
    }



}
