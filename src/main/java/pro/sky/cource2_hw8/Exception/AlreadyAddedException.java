package pro.sky.cource2_hw8.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyAddedException extends RuntimeException {
}

