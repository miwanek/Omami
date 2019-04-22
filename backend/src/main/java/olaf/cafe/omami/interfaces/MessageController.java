package olaf.cafe.omami.interfaces;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
@RequestMapping(path = "/messages")
public class MessageController {

    @GetMapping
    public ResponseEntity<String> getRandomMessage() {
        return new ResponseEntity<>("String", HttpStatus.OK);
    }
}
