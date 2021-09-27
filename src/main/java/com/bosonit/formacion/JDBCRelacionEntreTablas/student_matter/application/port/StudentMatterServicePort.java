package com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.application.port;

import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain.StudentMatter;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.input.StudentMatterInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.output.StudentMatterOutputDTO;

import java.util.List;

public interface StudentMatterServicePort {

    List<StudentMatter> getAllStudentMatter();
    StudentMatterOutputDTO getStudentMatterByID(String id);
    StudentMatterOutputDTO createStudentMatter(StudentMatterInputDTO sm);
    void deleteStudentMatter(String id);
    StudentMatterOutputDTO updateStudentMatter(String id, StudentMatterInputDTO studentMatterInputDTO);
    void validation(StudentMatter studentMatter);
    String asingMatterToStudent(List<String> id_student, String id_studentMatter);

}

