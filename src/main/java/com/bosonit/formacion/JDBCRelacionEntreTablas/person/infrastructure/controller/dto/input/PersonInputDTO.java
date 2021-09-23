package com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.input;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
public class PersonInputDTO {

    Student student;
    String name;
    String surname;
    String user;
    String password;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date = Date.from(Instant.now());
    Date termination_date;
    String imagen_url;

    public PersonInputDTO(Person person){

        this.student = person.getStudent();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.user = person.getUser();
        this.password = person.getPassword();
        this.company_email = person.getCompany_email();
        this.personal_email = person.getPersonal_email();
        this.city = person.getCity();
        this.active = person.getActive();
        this.termination_date = person.getTermination_date();
        this.imagen_url = person.getImagen_url();

    }




}
