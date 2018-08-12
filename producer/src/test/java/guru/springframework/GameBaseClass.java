package guru.springframework;

import guru.springframework.producer.controller.GameController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

/**
 * Created by jt on 8/11/18.
 */
public class GameBaseClass {

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(new GameController());
    }
}
