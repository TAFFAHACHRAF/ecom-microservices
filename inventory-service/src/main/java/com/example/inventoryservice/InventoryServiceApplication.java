package com.example.inventoryservice;

import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
			repositoryRestConfiguration.exposeIdsFor(Product.class);
			productRepository.saveAll(
					List.of(
							Product.builder().name("computer").price(500).quantity(15).build(),
							Product.builder().name("souris").price(100).quantity(18).build(),
							Product.builder().name("clavier").price(20).quantity(15).build()
					)
			);
			productRepository.findAll().forEach(c ->{
				System.out.println(c);
			});
		};
	}

}
