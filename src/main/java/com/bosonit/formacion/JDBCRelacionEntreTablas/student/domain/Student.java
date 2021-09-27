package com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.input.StudentInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain.StudentMatter;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_teacher")
    Teacher teacher;

    @Column(nullable = false)
    String branch;

    @ManyToMany( cascade = CascadeType.ALL, mappedBy = "students")
    List<StudentMatter> studentMatters;

    public Student(StudentInputDTO s){
        setStudent(s);
    }

    public void setStudent(StudentInputDTO s){
        if(s == null)
            return ;
        if(s.getNum_hours_week() != 0) this.num_hours_week = s.getNum_hours_week();
        if(s.getComments() != null) this.comments = s.getComments();
        if(s.getBranch() != null) this.branch = s.getBranch();

    }

}
