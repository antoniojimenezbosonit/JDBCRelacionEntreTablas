package com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output;

import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;


@Data
@NoArgsConstructor
public class StudentOutputDTO {

    String id_student;
    PersonOutputDTO person;
    int num_hours_week;
    String comments;
    String id_teacher;
    String branch;

    public StudentOutputDTO(Student student){

        this.id_student = student.getId_student();
        this.person = new PersonOutputDTO(student.getPerson());
        this.num_hours_week = student.getNum_hours_week();
        this.comments = student.getComments();
        this.id_teacher = student.getId_teacher();
        this.branch = student.getBranch();


    }


}
