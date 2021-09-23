package com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.input.StudentInputDTO;
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
public class Student {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id_student;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_persona")
    Person person;

    @Column(nullable = false)
    int num_hours_week;

    @Column(nullable = false)
    String comments;

    //@OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    String id_teacher;

    @Column(nullable = false)
    String branch;

    public Student(StudentInputDTO s){
        setStudent(s);
    }

    public void setStudent(StudentInputDTO s){
        if(s == null)
            return ;
        if(s.getPerson() != null) this.person = s.getPerson();
        if(s.getNum_hours_week() != 0) this.num_hours_week = s.getNum_hours_week();
        if(s.getComments() != null) this.comments = s.getComments();
        if(s.getId_teacher() != null) this.id_teacher = s.getId_teacher();
        if(s.getBranch() != null) this.branch = s.getBranch();

    }

}
