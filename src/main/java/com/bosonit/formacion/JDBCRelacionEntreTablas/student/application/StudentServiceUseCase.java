package com.bosonit.formacion.JDBCRelacionEntreTablas.student.application;


import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.NotFoundException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.UnprocesableException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.repository.PersonRepository;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.application.port.StudentServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.input.StudentInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output.StudentOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output.StudentOutputSimpleDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.repository.StudentRepository;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.repository.TeacherRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceUseCase implements StudentServicePort {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public List<Object> getAllStudent(String outputType){
        List<Student> s = new ArrayList<>();
        s = studentRepository.findAll();
        List<Object> list = new ArrayList<>();
        if(outputType.equals("full")) {

            s.stream().forEach((student) -> {
                StudentOutputDTO st = new StudentOutputDTO(student);
                list.add(st);
            });
            return list;
        }else if(outputType.equals("simple")){
            s.stream().forEach((student) -> {
                StudentOutputSimpleDTO st = new StudentOutputSimpleDTO(student);
                list.add(st);
            });
            return list;
        }
            return list;
    }


    public Object getStudentByID(String id, String outputType){



        Optional<Student> p;
        try {
            p = studentRepository.findById(id);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }

        if(outputType.equals("full")) {

            StudentOutputDTO studentDTO = new StudentOutputDTO(p.get());
            return studentDTO;
        }else if(outputType.equals("simple")){

            StudentOutputSimpleDTO studentSimpleDTO = new StudentOutputSimpleDTO(p.get());
            return studentSimpleDTO;

        }else{
            return "parametro invalido";
        }

    }


    public StudentOutputDTO createStudent(StudentInputDTO s){
        Student student = new Student(s);
        personRepository.findById(s.getId_person()).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        teacherRepository.findById(s.getId_teacher()).orElseThrow(() -> new RuntimeException("Profesor no encontrada"));
        student.setPerson(personRepository.getById(s.getId_person()));
        student.setTeacher(teacherRepository.getById(s.getId_teacher()));
        validation(student);
        studentRepository.save(student);


        Person p = personRepository.getById(s.getId_person());
        p.setStudent(student);
        personRepository.save(p);

        StudentOutputDTO personDTO = new StudentOutputDTO(student);
        return personDTO;
    }

    public void deleteStudent(String id){
        try {
            studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
            studentRepository.deleteById(id);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }
    }

    public StudentOutputDTO updateStudent(String id, StudentInputDTO student){

        StudentOutputDTO personSaved = new StudentOutputDTO();
        try{
            Student student1 = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
            student1.setStudent(student);
            studentRepository.save(student1);
            personSaved = new StudentOutputDTO(student1);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }

        return personSaved;
    }

    public void validation(Student student) throws UnprocesableException {
        if(student.getNum_hours_week() == 0) throw new UnprocesableException("Number hours week is null");
        if(student.getComments() == null) throw new UnprocesableException("comments week is null");
        if(student.getBranch() == null) throw new UnprocesableException("branch hours week is null");


    }
}
