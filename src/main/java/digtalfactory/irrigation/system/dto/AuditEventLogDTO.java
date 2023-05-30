package digtalfactory.irrigation.system.dto;

import digtalfactory.irrigation.system.model.enums.ApiType;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Date;
@Data
public class AuditEventLogDTO {

    private Long id;

    private Date time;

    private String request;

    private String response;

    private HttpMethod httpMethod;

//    private ApiType apiType;

    private String path;

    private String queryParams;

    private HttpStatus httpStatus;

}
