package olaf.cafe.omami.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import olaf.cafe.omami.ITConfiguration;
import olaf.cafe.omami.domain.Room;
import olaf.cafe.omami.domain.User;
import olaf.cafe.omami.domain.UserRoom;
import olaf.cafe.omami.domain.UserRoomId;
import olaf.cafe.omami.domain.UserRoomRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ITConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRoomControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    UserRoomRepository userRoomRepositoryMock;

    @Test
    public void shouldReturnAllRoomsForGivenClient() {
        //given
        User user = new User(1, "a", "a");
        Room room = new Room(1, "room");
        UserRoomId id = new UserRoomId(user, room);

        //and
        when(userRoomRepositoryMock.findRoomsForGivenUser(anyInt()))
                .thenReturn(Arrays.asList(new UserRoom(id)));

        //when
        var response = RestAssured.get(roomsPath());

        //then
        response.then()
                .statusCode(200);

        var list = mapToPojo(response.body().asString());

        Assert.assertEquals(list.get(0), new RoomDTO(1, "room"));
    }

    private String roomsPath() {
        return "http://localhost:" + port + "/user_rooms?userId=1";
    }

    private List<RoomDTO> mapToPojo(String json) {
        List<RoomDTO> list = new ArrayList<>();
        try {
            list = Arrays.asList(mapper.readValue(json, RoomDTO[].class));
        } catch (Exception exc) {
            throw new RuntimeException();
        } finally {
            return list;
        }
    }
}
