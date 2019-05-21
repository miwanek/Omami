package olaf.cafe.omami.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import olaf.cafe.omami.ITConfiguration;
import olaf.cafe.omami.domain.User;
import olaf.cafe.omami.domain.UserRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ITConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    UserRepository userRepositoryMock;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void loggingToApplicationIsSuccessful() {
        //given
        LoginForm loginForm = new LoginForm("om", "nom");
        User user = new User(1, "om", "nom");

        //and
        Mockito.when(userRepositoryMock.findByLoginAndPassword(anyString(), anyString()))
                .thenReturn(Optional.of(user));

        RequestSpecification request = RestAssured.given()
                .body(loginForm)
                .contentType(ContentType.JSON);

        //when
        Response response = request.post(loginPath());

        //then
        response.then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("username", Matchers.equalTo("om"));
    }

    @Test
    public void loggingToApplicationIsNotSuccessful() {
        //given
        LoginForm loginForm = new LoginForm("om", "nom");

        //and
        Mockito.when(userRepositoryMock.findByLoginAndPassword(anyString(), anyString()))
                .thenReturn(Optional.empty());

        RequestSpecification request = RestAssured.given()
                .body(loginForm)
                .contentType(ContentType.JSON);

        //when
        Response response = request.post(loginPath());

        //then
        response.then()
                .statusCode(404);
    }

    @Test
    public void shouldReturnAllUsers() {
        //given
        Mockito.when(userRepositoryMock.findAll())
                .thenReturn(Arrays.asList(new User(1, "omami", "omami")));

        //when
        Response response = RestAssured.get(userPath());

        //then
        response.then()
                .statusCode(200);

        var list = map(response.body().asString());

        Assert.assertEquals(list.get(0), new UserDTO(1, "omami"));
    }

    @Test
    public void shouldReturnCreatedStatusWhenNewUserIsCreated() {
        //given
        RegisterForm registerForm = new RegisterForm("om", "nom");

        RequestSpecification request = RestAssured.given()
                .body(registerForm)
                .contentType(ContentType.JSON);

        //when
        Response response = request.post(userPath());

        //then
        response.then()
                .statusCode(201);
    }

    private String userPath() {
        return "http://localhost:" + port + "/users";
    }

    private String loginPath() {
        return "http://localhost:" + port + "/users/login";
    }

    private List<UserDTO> map(String json) {
            List<UserDTO> list = new ArrayList<>();
        try {
             list = Arrays.asList(mapper.readValue(json, UserDTO[].class));
        } catch (Exception exc) {
            throw new RuntimeException();
        }
        finally {
            return list;
        }
    }
}
