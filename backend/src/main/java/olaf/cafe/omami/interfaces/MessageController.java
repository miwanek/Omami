package olaf.cafe.omami.interfaces;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/messages")
public class MessageController {

    @PostMapping
    public ResponseEntity<String> getRandomMessage() {
        return new ResponseEntity<>("String", HttpStatus.OK);
    }
}
