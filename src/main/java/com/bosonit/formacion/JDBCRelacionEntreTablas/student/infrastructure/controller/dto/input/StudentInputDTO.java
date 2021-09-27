package com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.input;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain.StudentMatter;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class StudentInputDTO {

    int id_person;
    int num_hours_week;
    String comments;
    String id_teacher;
    String branch;
    String id_studentMatters;

    public StudentInputDTO(Student student){

        this.id_person = student.getPerson().getId_person();
        this.id_studentMatters = student.getStudentMatters().get(0).getId_matter();
        this.num_hours_week = student.getNum_hours_week();
        this.comments = student.getComments();
        this.id_teacher = student.getTeacher().getId_teacher();
        this.branch = student.getBranch();

    }




}
