package com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.application.port;

import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.input.TeacherInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.output.TeacherOutputDTO;

import java.util.List;

public interface TeacherServicePort {

    List<Teacher> getAllTeacher();
    TeacherOutputDTO getTeacherByID(String id);
    TeacherOutputDTO createTeacher(TeacherInputDTO p);
    void deleteTeacher(String id);
    TeacherOutputDTO updateTeacher(String id, TeacherInputDTO teacherInputDTO);
    void validation(Teacher teacher);
}

