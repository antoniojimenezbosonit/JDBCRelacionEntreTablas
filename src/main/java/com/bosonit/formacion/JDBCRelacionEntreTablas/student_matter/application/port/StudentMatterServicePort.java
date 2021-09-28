package com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.application.port;

import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain.StudentMatter;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.input.StudentMatterInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.output.StudentMatterOutputDTO;

import java.util.List;

public interface StudentMatterServicePort {

    List<Object> getAllStudentMatter(String outputType);
    Object getStudentMatterByID(String id, String outputType);
    StudentMatterOutputDTO createStudentMatter(StudentMatterInputDTO sm);
    void deleteStudentMatter(String id);
    void deleteAsingMatterToStudent(List<String> id_studentMatter, String id_student);
    StudentMatterOutputDTO updateStudentMatter(String id, StudentMatterInputDTO studentMatterInputDTO);
    void validation(StudentMatter studentMatter);
    String asingMatterToStudent(List<String> id_student, String id_studentMatter);

}

