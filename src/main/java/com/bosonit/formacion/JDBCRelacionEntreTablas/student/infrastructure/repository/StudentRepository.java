package com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.repository;


import com.bosonit.formacion.JDBCRelacionEntreTablas.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {


}
