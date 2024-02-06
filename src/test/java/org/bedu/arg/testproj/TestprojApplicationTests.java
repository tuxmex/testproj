package org.bedu.arg.testproj;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@Configuration
@ComponentScan(basePackages = "org.bedu.arg.testproj.services")
class TestprojApplicationTests {

	@Test
	void contextLoads() {
	}
}
