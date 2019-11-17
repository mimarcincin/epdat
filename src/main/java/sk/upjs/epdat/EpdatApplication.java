package sk.upjs.epdat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EpdatApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpdatApplication.class, args);
    }

}
