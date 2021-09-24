package com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.input;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
public class StudentInputDTO {

    Person person;
    int num_hours_week;
    String comments;
    Teacher teacher;
    String branch;

    public StudentInputDTO(Student student){

        this.person = student.getPerson();
        this.num_hours_week = student.getNum_hours_week();
        this.comments = student.getComments();
        this.teacher = student.getTeacher();
        this.branch = student.getBranch();

    }




}
