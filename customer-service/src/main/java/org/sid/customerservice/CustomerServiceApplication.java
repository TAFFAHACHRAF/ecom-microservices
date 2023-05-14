package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository customerRepository,
                                               RepositoryRestConfiguration repositoryRestConfiguration){

        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(
                    List.of(
                            Customer.builder().name("achraf").email("taffah@gmail.com").build(),
                            Customer.builder().name("abdelmalek").email("abdelmalek@gmail.com").build(),
                            Customer.builder().name("abir").email("abir@gmail.com").build()
                    )
            );
            customerRepository.findAll().forEach(c ->{
                System.out.println(c);
            });
        };
    }
}
