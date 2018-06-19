package guru.springframework.consumer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.consumer.controller.domain.Player;
import org.junit.Before;
import org.springframework.boot.test.json.JacksonTester;

public abstract class AbstractTest {

	public JacksonTester<Player> json;
	@Before
	public void setup() {
		ObjectMapper objectMappper = new ObjectMapper();
		JacksonTester.initFields(this, objectMappper);
	}
}
