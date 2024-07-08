package com.example.sis_comanda;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SisComandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisComandaApplication.class, args);
	}

	public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Sistema de Comanda").version(appVersion));
	}
}
