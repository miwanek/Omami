package olaf.cafe.omami.interfaces;

import lombok.RequiredArgsConstructor;
import olaf.cafe.omami.domain.User;
import olaf.cafe.omami.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Void> checkIfLoginSuccessful(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userRepository.findByLoginAndPassword(username, password);

        if (user.isPresent()){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
