package com.bosonit.formacion.JDBCRelacionEntreTablas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JdbcRelacionEntreTablasApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcRelacionEntreTablasApplication.class, args);
	}

}
