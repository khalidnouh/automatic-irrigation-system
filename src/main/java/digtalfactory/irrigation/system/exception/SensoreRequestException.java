package digtalfactory.irrigation.system.exception;

public class SensoreRequestException extends BaseException {
	private static final long serialVersionUID = 1L;
	private Boolean success;

	public SensoreRequestException(String message, Boolean success) {
		super(message);
		this.success = success;
	}

	public SensoreRequestException(String msg) {
		super(msg);
	}

	public SensoreRequestException(String msg, Exception e) {
		super(msg + " because of " + e.toString());
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
