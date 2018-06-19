package guru.springframework.consumer.controller;

import guru.springframework.consumer.controller.domain.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(workOffline = true, ids = "guru.springframework:game-api-producer:+:stubs:8090")
@DirtiesContext
public class GameEngineControllerTest extends AbstractTest{
    @Autowired
    MockMvc mockMvc;
    @Autowired GameEngineController gameEngineController;

    public JacksonTester<Player> json;

    @Test
    public void should_allow_to_play_when_score_is_above_500() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/play/football")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(Player
                        .builder()
                        .name("Tim")
                        .score(600)
                        .build())
                        .getJson()))
                .andExpect(status().isOk())
                .andExpect(content().json("{'result':'ELIGIBLE'}"));

    }

    @Test
    public void should_not_allow_to_play_when_score_is_below_500() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/play/football")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(Player
                        .builder()
                        .name("Tim")
                        .score(300)
                        .build())
                        .getJson()))
                .andExpect(status().isOk())
        .andExpect(content().json("{'result':'NOT ELIGIBLE'}"));

    }
}