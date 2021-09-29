package com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller;


import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.application.port.TeacherServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.input.TeacherInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.output.TeacherOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.utils.FeingServerInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    FeingServerInterface feingServerInterface;

    @GetMapping
    public List<Object> getAllTeacher(@RequestParam(required = false, defaultValue = "simple") String outputType){
        List<Object> teacherList = new ArrayList<>();
        teacherList = teacherServiceUseCase.getAllTeacher(outputType);
        return teacherList;
    }


    @PostMapping
    @Transactional(rollbackOn = Exception.class)
    public TeacherOutputDTO addTeacher(@RequestBody TeacherInputDTO t){

        return teacherServiceUseCase.createTeacher(t);
    }

    @GetMapping("{id_teacher}")
    public Object getTeacherById(@PathVariable String id_teacher, @RequestParam(required = false, defaultValue = "simple") String outputType) {

        return teacherServiceUseCase.getTeacherByID(id_teacher, outputType);
    }

    @GetMapping("/feign/{id_teacher}")
    public Object getTeacherByIdWithFeign(@PathVariable String id_teacher, @RequestParam(required = false, defaultValue = "simple") String outputType) {

        ResponseEntity<Object> respuesta = feingServerInterface.getTeacherByIdWithFeign(id_teacher);
        return respuesta;
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
