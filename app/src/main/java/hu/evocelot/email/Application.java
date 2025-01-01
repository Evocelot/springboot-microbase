package hu.evocelot.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starter point of the SpringBoot application.
 * 
 * @author mark.danisovszky
 */
@SpringBootApplication
public class Application {

    /**
     * The main method that runs the Spring application.
     * @param args - the application args.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
