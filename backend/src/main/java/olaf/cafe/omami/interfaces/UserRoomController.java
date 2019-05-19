package olaf.cafe.omami.interfaces;

import lombok.RequiredArgsConstructor;
import olaf.cafe.omami.domain.Room;
import olaf.cafe.omami.domain.UserRoom;
import olaf.cafe.omami.domain.UserRoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/user_rooms")
public class UserRoomController {

    private final UserRoomRepository userRoomRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoomDTO> getAllRoomsForUser(@RequestParam Integer userId)  {
        return userRoomRepository.findRoomsForGivenUser(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private RoomDTO mapToDTO(UserRoom userRoom) {
        Room room = userRoom.getUserRoomId().getRoom();

        return new RoomDTO(room.getId(), room.getName());
    }
}
