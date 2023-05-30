package digtalfactory.irrigation.system.exception;

import org.springframework.http.HttpStatus;

public class UnreachableServiceException extends BaseException {
    public UnreachableServiceException(String message) {
        super(message);
        this.status = HttpStatus.SERVICE_UNAVAILABLE;
    }
}
