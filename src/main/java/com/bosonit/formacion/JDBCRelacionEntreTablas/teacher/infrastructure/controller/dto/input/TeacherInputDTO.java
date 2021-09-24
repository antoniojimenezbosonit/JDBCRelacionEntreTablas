package com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.input;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherInputDTO {

    Person person;
    String comments;
    String branch;

    public TeacherInputDTO(Teacher teacher){

        this.person = teacher.getPerson();
        this.comments = teacher.getComments();
        this.branch = teacher.getBranch();

    }




}
