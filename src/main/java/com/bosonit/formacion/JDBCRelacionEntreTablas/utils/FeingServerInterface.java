package com.bosonit.formacion.JDBCRelacionEntreTablas.utils;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@FeignClient(name="simpleFeign", url="http://localhost:8081/teacher/")
public interface FeingServerInterface {

    @GetMapping("{id_teacher}")
    ResponseEntity<Object> getTeacherByIdWithFeign(@PathVariable String id_teacher);
}
