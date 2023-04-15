package pl.java.spring;
// bez tych importów nie mamy springa tylka java SE
// słuzy jedynie do stratu aplikacji
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
