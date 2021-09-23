package com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.input.PersonInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Person {
    @Id
    @GeneratedValue
    Integer id_person;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    Student student;

    @Column(nullable = false)
    @Size(min  = 6, max = 10)
    String user;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String surname;
    @Column(nullable = false)
    @Email
    String company_email;
    @Column(nullable = false)
    @Email
    String personal_email;
    @Column(nullable = false)
    String city;
    @Column(nullable = false)
    Boolean active;
    @Column(nullable = false)
    Date created_date = Date.from(Instant.now());
    String imagen_url;
    Date termination_date;

    public Person(PersonInputDTO p){
        setPerson(p);
    }

    public void setPerson(PersonInputDTO p){
        if(p == null)
            return ;
        //if(p.getStudent() != null) this.student = p.getStudent();
        if(p.getUser() != null) this.user = p.getUser();
        if(p.getPassword() != null) this.password = p.getPassword();
        if(p.getName() != null) this.name = p.getName();
        if(p.getSurname() != null) this.surname = p.getSurname();
        if(p.getCompany_email() != null) this.company_email = p.getCompany_email();
        if(p.getPersonal_email() != null) this.personal_email = p.getPersonal_email();
        if(p.getCity() != null) this.city = p.getCity();
        if(p.getActive() != null) this.active = p.getActive();
        if(p.getCreated_date() != null) this.created_date = p.getCreated_date();
        if(p.getImagen_url() != null) this.imagen_url = p.getImagen_url();
        if(p.getTermination_date() != null) this.termination_date = p.getTermination_date();
    }

}
