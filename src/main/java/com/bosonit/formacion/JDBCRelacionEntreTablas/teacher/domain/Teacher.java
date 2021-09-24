package com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.input.TeacherInputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id_teacher;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_persona")
    Person person;

    @Column(nullable = false)
    String comments;

    @Column(nullable = false)
    String branch;

    public Teacher(TeacherInputDTO t){
        setTeacher(t);
    }

    public void setTeacher(TeacherInputDTO t){
        if(t == null)
            return ;
        if(t.getPerson() != null) this.person = t.getPerson();
        if(t.getComments() != null) this.comments = t.getComments();
        if(t.getBranch() != null) this.branch = t.getBranch();

    }

}
