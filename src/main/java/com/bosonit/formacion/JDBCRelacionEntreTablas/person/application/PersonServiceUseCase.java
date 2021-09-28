package com.bosonit.formacion.JDBCRelacionEntreTablas.person.application;


import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.NotFoundException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.UnprocesableException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.application.port.PersonServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.input.PersonInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.output.PersonOutputSimpleDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.repository.PersonRepository;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output.StudentOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output.StudentOutputSimpleDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceUseCase implements PersonServicePort {

    @Autowired
    PersonRepository personRepository;

    public List<Object> getAllPerson(String outputType){
        List<Person> p = new ArrayList<>();
        p = personRepository.findAll();
        List<Object> list = new ArrayList<>();
        if(outputType.equals("full")) {

            p.stream().forEach((person) -> {
                PersonOutputDTO pt = new PersonOutputDTO(person);
                list.add(pt);
            });
            return list;
        }else if(outputType.equals("simple")){
            p.stream().forEach((person) -> {
                PersonOutputSimpleDTO pt = new PersonOutputSimpleDTO(person);
                list.add(pt);
            });
            return list;
        }
        return list;
    }


    public Object getPersonByID(Integer id, String outputType){

        Optional<Person> p;
        try {
            p = personRepository.findById(id);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }

        if(outputType.equals("full")) {

            PersonOutputDTO personDTO = new PersonOutputDTO(p.get());
            return personDTO;
        }else if(outputType.equals("simple")){

            PersonOutputSimpleDTO personSimpleDTO = new PersonOutputSimpleDTO(p.get());
            return personSimpleDTO;

        }else{
            return "parametro invalido";
        }

    }

    public List<Object> getPersonByUser(String user, String outputType){

        List<Optional<Person>> p = new ArrayList<>();
        try {
            p = personRepository.findByUser(user);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }

        if(outputType.equals("full")) {

            return p.stream().map( l -> new PersonOutputDTO(l.get())).collect(Collectors.toList());
        }else if(outputType.equals("simple")){

            return p.stream().map( l -> new PersonOutputSimpleDTO(l.get())).collect(Collectors.toList());

    }
        return p.stream().map( l -> new PersonOutputDTO(l.get())).collect(Collectors.toList());
    }

    public PersonOutputDTO createPerson(PersonInputDTO p){
        Person person = new Person(p);
        validation(person);
        personRepository.save(person);
        PersonOutputDTO personDTO = new PersonOutputDTO(person);
        return personDTO;
    }

    public void deletePerson(Integer id){
        try {
            personRepository.findById(id).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
            personRepository.deleteById(id);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }
    }

    public PersonOutputDTO updatePerson(Integer id, PersonInputDTO person){

        PersonOutputDTO personSaved = new PersonOutputDTO();
        try{
            Person person1 = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
            person1.setPerson(person);
            personRepository.save(person1);
            personSaved = new PersonOutputDTO(person1);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }

        return personSaved;
    }

    public void validation(Person person) throws UnprocesableException {
        if(person.getActive() == null) throw new UnprocesableException("active is null");
        if(person.getName() == null) throw new UnprocesableException("name is null");
        if(person.getPersonal_email() == null) throw new UnprocesableException("personal email is null");
        if(person.getCompany_email() == null) throw new UnprocesableException("company email is null");
        if(person.getSurname() == null) throw new UnprocesableException("surname is null");
        if(person.getCity() == null) throw new UnprocesableException("city is null");
        if(person.getImagen_url() == null) throw new UnprocesableException("imagen is null");
        if(person.getPassword() == null) throw new UnprocesableException("password is null");
        if(person.getTermination_date() == null) throw new UnprocesableException("termination date is null");
        if(person.getUser() == null) throw new UnprocesableException("user is null");
        if(person.getCreated_date()== null) throw new UnprocesableException("created date is null");
    }
}
