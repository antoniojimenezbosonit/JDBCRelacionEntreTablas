package com.bosonit.formacion.JDBCRelacionEntreTablas.person.application.port;

import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.input.PersonInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.output.PersonOutputDTO;

import java.util.List;

public interface PersonServicePort {

    List<Object> getAllPerson(String outputType);
    Object getPersonByID(Integer id, String outputType);
    List<Object> getPersonByUser(String user, String outputType);
    PersonOutputDTO createPerson(PersonInputDTO p);
    void deletePerson(Integer id);
    PersonOutputDTO updatePerson(Integer id, PersonInputDTO person);
    void validation(Person person);
}
