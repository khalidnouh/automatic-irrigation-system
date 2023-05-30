package digtalfactory.irrigation.system.client.sensor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BaseResponse {
    private String status;
    private String message;
    private Error error;
    
    //New fields
    private List<Error> errors;
    @JsonProperty("http_code")
    private String httpCode;
}
