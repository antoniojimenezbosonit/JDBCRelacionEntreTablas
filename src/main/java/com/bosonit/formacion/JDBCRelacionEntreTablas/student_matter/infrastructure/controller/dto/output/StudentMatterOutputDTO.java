package com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.output;

import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output.StudentOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain.StudentMatter;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
public class StudentMatterOutputDTO {

    String id_studentMatter;
    List<StudentOutputDTO> studentOutputDTO;
    String matter;
    String comments;
    Date initial_date = Date.from(Instant.now());
    Date finish_date;

    public StudentMatterOutputDTO(StudentMatter studentMatter){

        this.id_studentMatter = studentMatter.getId_matter();
        this.matter = studentMatter.getMatter();
        this.comments = studentMatter.getComments();
        this.finish_date = studentMatter.getFinish_date();


    }


}
