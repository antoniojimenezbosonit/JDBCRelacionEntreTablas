package com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.input;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherInputDTO {

    int id_person;
    String comments;
    String branch;

    public TeacherInputDTO(Teacher teacher){

        this.id_person = teacher.getPerson().getId_person();
        this.comments = teacher.getComments();
        this.branch = teacher.getBranch();

    }




}
