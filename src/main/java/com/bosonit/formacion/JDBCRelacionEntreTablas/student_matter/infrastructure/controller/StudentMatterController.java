package com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller;


import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.application.port.StudentMatterServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain.StudentMatter;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.input.StudentMatterInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.output.StudentMatterOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.application.port.TeacherServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.input.TeacherInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.output.TeacherOutputDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("studentMatter")
public class StudentMatterController {

    StudentMatterServicePort studentMatterServiceUseCase;

    @GetMapping
    public List<StudentMatterOutputDTO> getAllStudentMatter(){
        List<StudentMatter> studentMatterList = new ArrayList<>();
        studentMatterList = studentMatterServiceUseCase.getAllStudentMatter();
        return studentMatterList.stream()
                .map( l -> new StudentMatterOutputDTO(l))
                .collect(Collectors.toList());
    }


    @PostMapping
    @Transactional(rollbackOn = Exception.class)
    public StudentMatterOutputDTO addStudentMatter(@RequestBody StudentMatterInputDTO sm){

        return studentMatterServiceUseCase.createStudentMatter(sm);
    }

    @PostMapping("/assign/{id_studentMatter}")
    @Transactional(rollbackOn = Exception.class)
    public void asingMatterToStudent(@RequestBody List<String> id_student, @PathVariable String id_studentMatter){

        studentMatterServiceUseCase.asingMatterToStudent(id_student, id_studentMatter);
    }

    @GetMapping("{id_studentMatter}")
    public StudentMatterOutputDTO getStudentMatterById(@PathVariable String id_studentMatter) {

        return studentMatterServiceUseCase.getStudentMatterByID(id_studentMatter);
    }

    @Transactional(rollbackOn = Exception.class)
    @DeleteMapping("{id}")
    public  String deleteStudentMatter(@PathVariable String id){

        studentMatterServiceUseCase.deleteStudentMatter(id);
        return "StudentMatter delete";
    }

    @PutMapping("{id}")
    @Transactional(rollbackOn = Exception.class)
    public StudentMatterOutputDTO updateStudentMatter(@PathVariable String id, @RequestBody StudentMatterInputDTO sm){

           return studentMatterServiceUseCase.updateStudentMatter(id, sm);
    }



}
