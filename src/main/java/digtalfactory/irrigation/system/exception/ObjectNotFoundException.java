package digtalfactory.irrigation.system.exception;

public class ObjectNotFoundException extends BaseException {
    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String msg, Exception e) {
        super(msg + " because of " + e.toString());
    }

}
