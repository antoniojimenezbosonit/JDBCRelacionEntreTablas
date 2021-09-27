package com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.input;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain.StudentMatter;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class StudentMatterInputDTO {

    List<String> id_student;
    String matter;
    String comments;
    Date initial_date = Date.from(Instant.now());
    Date finish_date;

    public StudentMatterInputDTO(StudentMatter studentMatter){


        this.matter = studentMatter.getMatter();
        this.comments = studentMatter.getComments();
        this.finish_date = studentMatter.getFinish_date();


    }




}
