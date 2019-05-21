package olaf.cafe.omami.interfaces;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import olaf.cafe.omami.ITConfiguration;
import olaf.cafe.omami.domain.Room;
import olaf.cafe.omami.domain.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ITConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    RoomRepository roomRepositoryMock;

    @Test
    public void shouldReturnCreatedStatusWhenNewUserIsCreated() {
        //when
        Response response = RestAssured.post(roomsPath());

        //then
        response.then()
                .statusCode(201);

        verify(roomRepositoryMock).save(any(Room.class));
    }

    private String roomsPath() {
        return "http://localhost:" + port + "/rooms?name=\"mam\"";
    }
}
