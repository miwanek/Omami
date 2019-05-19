package olaf.cafe.omami.interfaces;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    ResponseEntity<Void> handleConstraintViolationException(ConstraintViolationException exception) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
