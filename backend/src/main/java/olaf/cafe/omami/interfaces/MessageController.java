package olaf.cafe.omami.interfaces;

import lombok.RequiredArgsConstructor;
import olaf.cafe.omami.domain.Message;
import olaf.cafe.omami.domain.MessageRepository;
import olaf.cafe.omami.domain.Room;
import olaf.cafe.omami.domain.RoomRepository;
import olaf.cafe.omami.domain.User;
import olaf.cafe.omami.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/messages")
public class MessageController {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sendNewMessage(@RequestBody MessageForm form) {
        User user = userRepository.findById(form.getUserId()).orElseThrow();
        Room room = roomRepository.findById(form.getRoomId()).orElseThrow();

        Message message = new Message(user, room, form.getData());
        messageRepository.save(message);
    }
}
