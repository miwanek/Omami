package olaf.cafe.omami.interfaces;

import lombok.RequiredArgsConstructor;
import olaf.cafe.omami.domain.User;
import olaf.cafe.omami.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/users")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping(path = "/login")
    public ResponseEntity<UserDTO> checkIfLoginSuccessful(@RequestBody LoginForm loginForm) {
        Optional<User> user = userRepository.findByLoginAndPassword(loginForm.getUsername(), loginForm.getPassword());

        if (user.isPresent()) {
            User loggedUser = user.get();

            return new ResponseEntity<>(new UserDTO(loggedUser.getId(),
                    loggedUser.getLogin()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(user.getId(), user.getLogin()))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createNewUser(@RequestBody RegisterForm registerForm) {
        User user = new User(registerForm.getUsername(), registerForm.getPassword());
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
