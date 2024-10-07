package com.wsr.apiTest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

import static org.springframework.boot.SpringApplication.run;

@Slf4j
@SpringBootApplication
public class ApiTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTestApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(GrupoRepository grupoRepository){
		return args ->{
			Stream.of(
					new Grupo("S7A"),
					new Grupo("S7B"),
					new Grupo("S6A")
			).forEach(grupoRepository::save);
		};


	}
}

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
class Grupo{
	@Id
	@GeneratedValue
	private Integer id;
	@NonNull
	private String name;
}


@RepositoryRestResource(path="grupos",collectionResourceRel="grupos")

@Repository
interface GrupoRepository extends JpaRepository<Grupo,Integer>{

}
