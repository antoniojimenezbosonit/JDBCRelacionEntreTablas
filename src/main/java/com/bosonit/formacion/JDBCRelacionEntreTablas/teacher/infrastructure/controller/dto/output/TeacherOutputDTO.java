package com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.output;

import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TeacherOutputDTO {

    String id_teacher;
    PersonOutputDTO person;
    String comments;
    String branch;

    public TeacherOutputDTO(Teacher teacher){

        this.person = new PersonOutputDTO(teacher.getPerson());
        this.comments = teacher.getComments();
        this.id_teacher = teacher.getId_teacher();
        this.branch = teacher.getBranch();


    }


}
