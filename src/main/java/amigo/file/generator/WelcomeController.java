package amigo.file.generator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("")
public class WelcomeController {

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity welcomePage() {
        Map<String, String> welcomeMessageMap = Map.of("welcomeMessage", "Welcome to dynamic excel generator page");
        return ResponseEntity.ok(welcomeMessageMap);
    }
}

