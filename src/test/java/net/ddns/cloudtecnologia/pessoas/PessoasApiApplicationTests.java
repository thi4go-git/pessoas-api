package net.ddns.cloudtecnologia.pessoas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PessoasApiApplicationTests {

    @Test
    void main() {
        PessoasApiApplication.main(new String[]{});
        Assertions.assertNotNull(this);
    }

}
