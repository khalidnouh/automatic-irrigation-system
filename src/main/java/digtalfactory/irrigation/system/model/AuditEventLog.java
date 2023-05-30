package digtalfactory.irrigation.system.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import digtalfactory.irrigation.system.model.enums.ApiType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "audit_log")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)

public class AuditEventLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time")
    private Date time;

    @Column(name = "request", columnDefinition = "json")
    @Type(type = "jsonb")
    private String request;

    @Type(type = "jsonb")
    @Column(name = "response",columnDefinition = "json")
    private String response;

    @Column(name = "path")
    private String path;

    @Column(name = "query_params")
    private String queryParams;

    //to indicate httpMethod (GET,POST,...)
    @Column(name = "http_method")
    @Enumerated(EnumType.STRING)
    private HttpMethod httpMethod;

//    //to indicate if it's internally or externally
//    @Column(name = "api_type")
//    @Enumerated(EnumType.STRING)
//    private ApiType apiType;

    //to indicate if it's internally or externally
    @Column(name = "http_status")
    @Enumerated(EnumType.STRING)
    private HttpStatus httpStatus;


}