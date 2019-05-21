package olaf.cafe.omami.interfaces;

import lombok.RequiredArgsConstructor;
import olaf.cafe.omami.domain.Room;
import olaf.cafe.omami.domain.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/rooms")
public class RoomController {

    private final RoomRepository roomRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewRoom(@RequestParam String name) {
        Room room = new Room(name);

        roomRepository.save(room);
    }
}
