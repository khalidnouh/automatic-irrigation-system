package digtalfactory.irrigation.system.dto.response;

import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Date;

@EqualsAndHashCode
public class ApiResponse<T> {

	private HttpStatus statusCode;
	private int status;
    private String message;
    private Object result;
    private Date responseTime;

    public ApiResponse(int status, String message, Object result) {
        this.status = status;
        this.statusCode = HttpStatus.valueOf(status);
        
        this.message = message;
        this.result = result;
        this.responseTime = new Date();
    }
	
    public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
}

