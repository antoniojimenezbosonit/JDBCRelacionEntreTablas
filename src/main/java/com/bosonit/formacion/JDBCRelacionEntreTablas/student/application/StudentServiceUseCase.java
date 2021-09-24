package com.bosonit.formacion.JDBCRelacionEntreTablas.student.application;


import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.NotFoundException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.UnprocesableException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.repository.PersonRepository;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.application.port.StudentServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.input.StudentInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output.StudentOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.repository.StudentRepository;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.repository.TeacherRepository;
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
public class StudentServiceUseCase implements StudentServicePort {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public List<Student> getAllStudent(){
        List<Student> p = new ArrayList<>();
        p = studentRepository.findAll();
        return p;
    }


    public StudentOutputDTO getStudentByID(String id){

        Optional<Student> p;
        try {
            p = studentRepository.findById(id);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }

        StudentOutputDTO personDTO = new StudentOutputDTO(p.get());

        return personDTO;

    }


    public StudentOutputDTO createStudent(int id_person, String id_teacher, StudentInputDTO s){
        Student student = new Student(s);
        personRepository.findById(id_person).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        teacherRepository.findById(id_teacher).orElseThrow(() -> new RuntimeException("Profesor no encontrada"));
        student.setPerson(personRepository.getById(id_person));
        student.setTeacher(teacherRepository.getById(id_teacher));
        validation(student);
        studentRepository.save(student);


        Person p = personRepository.getById(id_person);
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

    }
}
