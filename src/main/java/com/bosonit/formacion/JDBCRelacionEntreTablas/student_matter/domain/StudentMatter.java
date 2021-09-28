package com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain;


import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.input.StudentMatterInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.utils.StringPrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.action.internal.OrphanRemovalAction;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentMatter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matter_seq")
    @GenericGenerator(
            name = "matter_seq",
            strategy = "com.bosonit.formacion.JDBCRelacionEntreTablas.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "MATTER"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    @Column(name = "id_matter")
    private String id_matter;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "students_get_studentsMatter",
            joinColumns = {@JoinColumn(name="id_matter")},
            inverseJoinColumns = {@JoinColumn(name = "id_student")})
    List<Student> students;

    @Column(nullable = false)
    String matter;

    @Column(nullable = false)
    String comments;

    @Column(nullable = false)
    Date initial_date = Date.from(Instant.now());

    @Column(nullable = false)
    Date finish_date;

    public StudentMatter(StudentMatterInputDTO sm){
        System.out.println(sm);
        setStudentMatter(sm);
    }

    public void setStudentMatter(StudentMatterInputDTO sm){
        if(sm == null)
            return ;
        if(sm.getComments() != null) this.comments = sm.getComments();
        if(sm.getMatter() != null) this.matter = sm.getMatter();
        if(sm.getFinish_date() != null ) this.finish_date = sm.getFinish_date();


    }

}
