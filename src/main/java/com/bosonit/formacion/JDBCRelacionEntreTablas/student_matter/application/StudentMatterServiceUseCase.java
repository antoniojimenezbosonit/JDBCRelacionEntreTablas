package com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.application;


import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.NotFoundException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.UnprocesableException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.repository.StudentRepository;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.application.port.StudentMatterServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain.StudentMatter;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.ListIdStudent;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.input.StudentMatterInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.output.StudentMatterOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.repository.StudentMatterRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentMatterServiceUseCase implements StudentMatterServicePort {


    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentMatterRepository studentMatterRepository;

    public List<StudentMatter> getAllStudentMatter(){
        List<StudentMatter> sm = new ArrayList<>();
        sm = studentMatterRepository.findAll();
        return sm;
    }


    public StudentMatterOutputDTO getStudentMatterByID(String id){

        Optional<StudentMatter> sm;
        try {
            sm = studentMatterRepository.findById(id);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }

        StudentMatterOutputDTO studentMatterDTO = new StudentMatterOutputDTO(sm.get());

        return studentMatterDTO;

    }


    public StudentMatterOutputDTO createStudentMatter(StudentMatterInputDTO sm){
        StudentMatter studentMatter = new StudentMatter(sm);
        validation(studentMatter);
        studentMatterRepository.save(studentMatter);
        StudentMatterOutputDTO studentMatterOutputDTO = new StudentMatterOutputDTO(studentMatter);
        return studentMatterOutputDTO;
    }

    public String asingMatterToStudent(List<String> id_student, String id_studentMatter){

        var studentMatter = studentMatterRepository.findById(id_studentMatter).orElseThrow(()-> new RuntimeException("Materia: "+id_studentMatter+ "nO ENCONTRADO"));
        for (String idEstudiante : id_student) {
            var student=studentRepository.findById(idEstudiante).orElseThrow(()-> new RuntimeException("Estudiante: "+ "nO ENCONTRADO"));
            studentMatter.getStudents().add(student);
        }

        return "insertado con exito";
    }

    public void deleteStudentMatter(String id){
        try {
            studentMatterRepository.findById(id).orElseThrow(() -> new RuntimeException("materia no encontrada no encontrada"));
            studentMatterRepository.deleteById(id);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }
    }

    public StudentMatterOutputDTO updateStudentMatter(String id_student, StudentMatterInputDTO studentMatter){

        StudentMatterOutputDTO studentMatterSaved = new StudentMatterOutputDTO();
        try{
            StudentMatter studentMatter1 = studentMatterRepository.findById(id_student).orElseThrow(() -> new RuntimeException("asignatura no encontrada"));
            studentMatter1.setStudentMatter(studentMatter);
            studentMatterRepository.save(studentMatter1);
            studentMatterSaved = new StudentMatterOutputDTO(studentMatter1);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }

        return studentMatterSaved;
    }

    public void validation(StudentMatter studentMatter) throws UnprocesableException {


    }
}
