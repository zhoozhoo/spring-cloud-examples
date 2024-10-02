package ca.zhoozhoo.springcloud.guests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = { "eureka.client.enabled=false", "spring.cloud.config.enabled=false" })
@ActiveProfiles("test")
class GuestServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
