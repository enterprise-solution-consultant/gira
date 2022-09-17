package cybersoft.javabackend.java18.gira;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GiraApplicationTests {

    @Test
    void contextLoads() {
        assertThat(1 + 1).isEqualTo(2);
    }

}
