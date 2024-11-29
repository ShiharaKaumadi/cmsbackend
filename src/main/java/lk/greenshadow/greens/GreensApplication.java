package lk.greenshadow.greens;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GreensApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreensApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){//get data dto to entity
		return new ModelMapper();
	}

}
