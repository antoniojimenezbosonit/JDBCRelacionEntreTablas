package com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.application;


import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.NotFoundException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.UnprocesableException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.domain.Person;
import com.bosonit.formacion.JDBCRelacionEntreTablas.person.infrastructure.repository.PersonRepository;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.application.port.TeacherServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.input.TeacherInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.controller.dto.output.TeacherOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherServiceUseCase implements TeacherServicePort {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    PersonRepository personRepository;

    public List<Teacher> getAllTeacher(){
        List<Teacher> t = new ArrayList<>();
        t = teacherRepository.findAll();
        return t;
    }


    public TeacherOutputDTO getTeacherByID(String id){

        Optional<Teacher> t;
        try {
            t = teacherRepository.findById(id);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }

        TeacherOutputDTO teacherDTO = new TeacherOutputDTO(t.get());

        return teacherDTO;

    }


    public TeacherOutputDTO createTeacher(TeacherInputDTO t){
        Teacher teacher = new Teacher(t);
        personRepository.findById(t.getId_person()).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        teacher.setPerson(personRepository.getById(t.getId_person()));
        validation(teacher);
        teacherRepository.save(teacher);
        Person p = new Person();
        p = personRepository.getById(t.getId_person());
        p.setTeacher(teacher);
        personRepository.save(p);
        TeacherOutputDTO teacherDTO = new TeacherOutputDTO(teacher);
        return teacherDTO;
    }

    public void deleteTeacher(String id){
        try {
            teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Profesor no encontrada"));
            teacherRepository.deleteById(id);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }
    }

    public TeacherOutputDTO updateTeacher(String id, TeacherInputDTO teacher){

        TeacherOutputDTO teacherSaved = new TeacherOutputDTO();
        try{
            Teacher teacher1 = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
            teacher1.setTeacher(teacher);
            teacherRepository.save(teacher1);
            teacherSaved = new TeacherOutputDTO(teacher1);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }

        return teacherSaved;
    }

    public void validation(Teacher teacher) throws UnprocesableException {

    }
}
