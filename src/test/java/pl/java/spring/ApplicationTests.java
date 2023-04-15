package pl.java.spring;
// to jest domyślny test który spring dołącza - daje info że się uruchamia
// najelpiej go nie usuwac
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

}
