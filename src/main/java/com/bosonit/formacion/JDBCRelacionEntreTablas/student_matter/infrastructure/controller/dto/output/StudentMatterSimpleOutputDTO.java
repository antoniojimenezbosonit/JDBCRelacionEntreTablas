package com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.infrastructure.controller.dto.output;

import com.bosonit.formacion.JDBCRelacionEntreTablas.student.infrastructure.controller.dto.output.StudentOutputDTO;
import com.bosonit.formacion.JDBCRelacionEntreTablas.student_matter.domain.StudentMatter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class StudentMatterSimpleOutputDTO {

    String id_studentMatter;
    String matter;
    String comments;
    Date initial_date = Date.from(Instant.now());
    Date finish_date;

    public StudentMatterSimpleOutputDTO(StudentMatter studentMatter){

        this.id_studentMatter = studentMatter.getId_matter();
        this.matter = studentMatter.getMatter();
        this.comments = studentMatter.getComments();
        this.finish_date = studentMatter.getFinish_date();


    }


}
