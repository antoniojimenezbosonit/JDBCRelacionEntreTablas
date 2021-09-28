package com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.input.TeacherInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.utils.StringPrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    @GenericGenerator(
            name = "teacher_seq",
            strategy = "com.bosonit.formacion.JDBCRelacionEntreTablas.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TEACHER"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    @Column(name = "id_teacher")
    private String id_teacher;


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_persona")
    Person person;

    @Column(nullable = false)
    String comments;

    @Column(nullable = false)
    String branch;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.ALL})
    List<Student> student = new ArrayList<>();

    public Teacher(TeacherInputDTO t){
        setTeacher(t);
    }

    public void setTeacher(TeacherInputDTO t){
        if(t == null)
            return ;
        if(t.getComments() != null) this.comments = t.getComments();
        if(t.getBranch() != null) this.branch = t.getBranch();

    }

}
