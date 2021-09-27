package com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output;

import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain.StudentMatter;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.output.TeacherOutputDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
public class StudentOutputDTO {

    String id_student;
    PersonOutputDTO personOutputDTO;
    int num_hours_week;
    String comments;
    TeacherOutputDTO teacherOutputDTO;
    String branch;
    String id_studentMatter;

    public StudentOutputDTO(Student student){

        this.id_student = student.getId_student();
        this.personOutputDTO = new PersonOutputDTO(student.getPerson());
        //this.id_studentMatter = student.getStudentMatters().get(0).getId_matter();
        this.num_hours_week = student.getNum_hours_week();
        this.comments = student.getComments();
        this.teacherOutputDTO = new TeacherOutputDTO(student.getTeacher());
        this.branch = student.getBranch();


    }


}
