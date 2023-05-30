package digtalfactory.irrigation.system.exception;

public class BadRequestException extends BaseException {
	private static final long serialVersionUID = 1L;
	
	public BadRequestException(String msg) {
		super(msg);
	}
	
	public BadRequestException(String msg, Exception e) {
		super(msg + " because of " + e.toString());
	}

}
