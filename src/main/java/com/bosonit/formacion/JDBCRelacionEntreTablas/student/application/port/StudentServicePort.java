package com.bosonit.formacion.JDBCRelacionEntreTablas.student.application.port;

import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.input.StudentInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output.StudentOutputDTO;

import java.util.List;

public interface StudentServicePort {

    List<Student> getAllStudent();
    StudentOutputDTO getStudentByID(String id);
    StudentOutputDTO createStudent(int id_person, StudentInputDTO p);
    void deleteStudent(String id);
    StudentOutputDTO updateStudent(String id, StudentInputDTO person);
    void validation(Student student);
}

