package com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.infrastructure.repository;


import com.bosonit.formacion.JDBCRelacionEntreTablas.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {


}
