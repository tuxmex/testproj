package org.bedu.arg.testproj;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Configuration
@ComponentScan(basePackages = "org.bedu.arg.testproj.services")
class TestprojApplicationTests {
	int i = 1;
	@Test
	void contextLoads() {
		assertThat(i).isEqualTo(1);
	}
}
