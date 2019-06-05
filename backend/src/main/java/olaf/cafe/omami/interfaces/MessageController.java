package olaf.cafe.omami.interfaces;

import lombok.RequiredArgsConstructor;
import olaf.cafe.omami.domain.Message;
import olaf.cafe.omami.domain.MessageRepository;
import olaf.cafe.omami.domain.Room;
import olaf.cafe.omami.domain.RoomRepository;
import olaf.cafe.omami.domain.User;
import olaf.cafe.omami.domain.UserRepository;
import olaf.cafe.omami.domain.UserRoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/messages")
public class MessageController {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;
    private final MessageRepository messageRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sendNewMessage(@RequestBody MessageForm form) {
        User user = userRepository.findById(form.getUserId()).orElseThrow();
        Room room = roomRepository.findById(form.getRoomId()).orElseThrow();

        Message message = new Message(user, room, form.getData());
        messageRepository.save(message);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDTO> getAllMessagesForGivenUser(@RequestParam Integer userId) {
        List<Integer> roomsIdsForGivenUser = userRoomRepository.findRoomsForGivenUser(userId)
                .stream()
                .map(userRoom -> userRoom.getUserRoomId().getRoom().getId())
                .collect(Collectors.toList());

        return messageRepository.findByRoom_IdIn(roomsIdsForGivenUser)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private MessageDTO mapToDTO(Message message) {
        return new MessageDTO(message.getUser().getLogin(),
                message.getRoom().getId(),
                message.getData(),
                message.getTime());
    }
}
