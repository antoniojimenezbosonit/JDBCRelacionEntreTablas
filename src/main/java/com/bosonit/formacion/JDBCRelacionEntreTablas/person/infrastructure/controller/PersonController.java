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
@RequestMapping("person")
public class PersonController {

    PersonServicePort personServiceUseCase;

    @GetMapping
    public List<Object> getAllPerson(@RequestParam(required = false, defaultValue = "simple") String outputType){
        List<Object> personList= new ArrayList<>();
        personList = personServiceUseCase.getAllPerson(outputType);
        return personList;
    }


    @PostMapping
    @Transactional(rollbackOn = Exception.class)
    public PersonOutputDTO addPerson(@RequestBody PersonInputDTO p){

        return personServiceUseCase.createPerson(p);
    }

    @GetMapping("{id_person}")
    public Object getPersonById(@PathVariable Integer id_person, @RequestParam(required = false, defaultValue = "simple") String outputType) {

        return personServiceUseCase.getPersonByID(id_person, outputType);
    }

    @GetMapping("/getByUser/{user}")
    public List<Object> getPersonByUser(@PathVariable String user, @RequestParam(required = false, defaultValue = "simple") String outputType){
            return personServiceUseCase.getPersonByUser(user, outputType);
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
