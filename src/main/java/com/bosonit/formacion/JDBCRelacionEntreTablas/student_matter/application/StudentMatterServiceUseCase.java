package com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.application;


import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.NotFoundException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.exceptions.UnprocesableException;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.repository.StudentRepository;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.application.port.StudentMatterServicePort;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain.StudentMatter;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.input.StudentMatterInputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.output.StudentMatterOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.output.StudentMatterSimpleOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.repository.StudentMatterRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentMatterServiceUseCase implements StudentMatterServicePort {


    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentMatterRepository studentMatterRepository;

    public List<Object> getAllStudentMatter(String outputType){
        List<StudentMatter> sm = new ArrayList<>();
        sm = studentMatterRepository.findAll();
        List<Object> list = new ArrayList<>();
        if(outputType.equals("full")){

            sm.stream().forEach((studentMatter) ->{
                StudentMatterOutputDTO smo = new StudentMatterOutputDTO(studentMatter);
                list.add(smo);
            });
            return list;
        }else if(outputType.equals("simple")){

            sm.stream().forEach((studentMatter) -> {
                StudentMatterSimpleOutputDTO smo = new StudentMatterSimpleOutputDTO(studentMatter);
                list.add(smo);
            });
            return list;
        }
        return list;
    }


    public Object getStudentMatterByID(String id, String outputType){

        Optional<StudentMatter> sm;
        try {
            sm = studentMatterRepository.findById(id);
        }catch (NoSuchElementException e){
            throw new NotFoundException("error");
        }

        if(outputType.equals("full")){

            StudentMatterOutputDTO studentMatterOutputDTO = new StudentMatterOutputDTO(sm.get());;
            return studentMatterOutputDTO;
        }else if(outputType.equals("simple")){

            StudentMatterSimpleOutputDTO studentMatterSimpleOutputDTO = new StudentMatterSimpleOutputDTO(sm.get());
            return studentMatterSimpleOutputDTO;
        }
        return "invalid param";


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

    @Override
    public void deleteAsingMatterToStudent(List<String> id_studentMatter, String id_student) {
        try{
            studentRepository.findById(id_student).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
            for (String studentMatter: id_studentMatter)
            {

                studentMatterRepository.findById(studentMatter).orElseThrow(() -> new RuntimeException("Materia no encontrado"));
                StudentMatter st = studentMatterRepository.getById(studentMatter);
                List<Student> list = st.getStudents();

                List<Student> listCopia= list.stream().collect(Collectors.toList());
                for (Student student: listCopia)
                {
                    if(student.getId_student().equals("STUDENT00000001")) {
                        st.getStudents().remove(student);
                    }
                }
                studentMatterRepository.save(st);

            }

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
