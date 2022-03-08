package pong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PongController {

    @GetMapping("/pong")
    public String pong() {
        return "pong";
    }

}
