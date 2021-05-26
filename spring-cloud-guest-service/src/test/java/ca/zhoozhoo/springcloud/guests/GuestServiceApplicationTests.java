package ca.zhoozhoo.springcloud.guests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class GuestServiceApplicationTests {

	@Test
	void contextLoads() {
	}
}
