package digtalfactory.irrigation.system.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseException extends RuntimeException {
    protected HttpStatus status;

    public BaseException(String message) {
        super(message);
    }
}
