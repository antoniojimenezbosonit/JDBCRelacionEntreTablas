package com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller;


import com.bosonit.formacion.JDBCRelacionEntreTablas.person.application.port.PersonServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.input.PersonInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.controller.dto.output.PersonOutputDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PersonController {

    PersonServicePort personServiceUseCase;

    @GetMapping
    public List<PersonOutputDTO> getAllPerson(){
        List<Person> personList= new ArrayList<>();
        personList = personServiceUseCase.getAllPerson();
        return personList.stream()
                .map( l -> new PersonOutputDTO(l))
                .collect(Collectors.toList());
    }


    @PostMapping
    @Transactional(rollbackOn = Exception.class)
    public PersonOutputDTO addPerson(@RequestBody PersonInputDTO p){

        return personServiceUseCase.createPerson(p);
    }

    @GetMapping("{id_person}")
    public PersonOutputDTO getPersonById(@PathVariable Integer id_person) {

        return personServiceUseCase.getPersonByID(id_person);
    }

    @GetMapping("/getForUser/{user}")
    public List<PersonOutputDTO> getPersonByUser(@PathVariable String user){
            return personServiceUseCase.getPersonByUser(user);
    }


    @Transactional(rollbackOn = Exception.class)
    @DeleteMapping("{id}")
    public  String deletePerson(@PathVariable Integer id){

        personServiceUseCase.deletePerson(id);
        return "persona borrada";
    }

    @PutMapping("{id}")
    @Transactional(rollbackOn = Exception.class)
    public PersonOutputDTO updatePerson(@PathVariable Integer id, @RequestBody PersonInputDTO p){

           return personServiceUseCase.updatePerson(id, p);
    }



}
