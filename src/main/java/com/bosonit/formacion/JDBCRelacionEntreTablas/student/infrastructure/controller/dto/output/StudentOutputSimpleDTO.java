package com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output;

import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.output.TeacherOutputDTO;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class StudentOutputSimpleDTO {

    String id_student;
    int num_hours_week;
    String comments;
    TeacherOutputDTO teacherOutputDTO;
    String branch;
    String id_studentMatter;

    public StudentOutputSimpleDTO(Student student){

        this.id_student = student.getId_student();
        this.num_hours_week = student.getNum_hours_week();
        this.comments = student.getComments();
        this.teacherOutputDTO = new TeacherOutputDTO(student.getTeacher());
        this.branch = student.getBranch();


    }


}
