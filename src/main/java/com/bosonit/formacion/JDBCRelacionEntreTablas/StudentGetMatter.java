package com.bosonit.formacion.JDBCRelacionEntreTablas;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StudentGetMatter {

    List<String> id_student;
    String id_studentMatter;
}
