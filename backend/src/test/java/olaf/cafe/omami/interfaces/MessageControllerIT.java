package olaf.cafe.omami.interfaces;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import olaf.cafe.omami.ITConfiguration;
import olaf.cafe.omami.domain.Message;
import olaf.cafe.omami.domain.MessageRepository;
import olaf.cafe.omami.domain.Room;
import olaf.cafe.omami.domain.RoomRepository;
import olaf.cafe.omami.domain.User;
import olaf.cafe.omami.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = ITConfiguration.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	MessageRepository messageRepositoryMock;

	@Autowired
	UserRepository userRepositoryMock;

	@Autowired
	RoomRepository roomRepositoryMock;

	@Test
	public void shouldReturnCreatedStatusWhenNewUserIsCreated() {
		//given
		MessageForm form = new MessageForm(1, 1, "String");

		Mockito.when(userRepositoryMock.findById(Mockito.anyInt()))
				.thenReturn(Optional.of(new User()));

		Mockito.when(roomRepositoryMock.findById(Mockito.anyInt()))
				.thenReturn(Optional.of(new Room()));

		RequestSpecification request = given()
				.body(form)
				.contentType(ContentType.JSON);

		//when
		Response response = request.post(messagePath());

		//then
		response.then()
				.statusCode(201);

		verify(messageRepositoryMock).save(any(Message.class));
	}

	private String messagePath() {
		return "http://localhost:" + port + "/messages";
	}
}