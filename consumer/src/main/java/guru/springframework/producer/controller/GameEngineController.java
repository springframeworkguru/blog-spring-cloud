package guru.springframework.producer.controller;

import guru.springframework.producer.domain.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URI;


@RestController
public class GameEngineController {
    private final RestTemplate restTemplate;
    public GameEngineController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @PostMapping(path = "/play/{game}", consumes = "application/json", produces = "application/json")
   public ResponseEntity<String> playGame(@RequestBody Player player, @PathVariable String game){
        ResponseEntity<String> response = this.restTemplate.exchange(
        RequestEntity
                .post(URI.create("http://localhost:8090/gamemanager?game="+game))
                .contentType(MediaType.APPLICATION_JSON)
                .body(player),String.class);
                 return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
   }

}
