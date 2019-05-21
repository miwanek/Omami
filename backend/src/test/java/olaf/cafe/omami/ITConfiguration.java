package olaf.cafe.omami;

import olaf.cafe.omami.domain.MessageRepository;
import olaf.cafe.omami.domain.RoomRepository;
import olaf.cafe.omami.domain.UserRepository;
import olaf.cafe.omami.domain.UserRoomRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@ComponentScan
@Configuration
public class ITConfiguration {
    @MockBean
    RoomRepository roomRepositoryMock;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserRoomRepository userRoomRepository;

    @MockBean
    MessageRepository messageRepositoryMock;
}
