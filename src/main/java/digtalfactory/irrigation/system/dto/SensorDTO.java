package digtalfactory.irrigation.system.dto;

import digtalfactory.irrigation.system.model.Plot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensorDTO implements Serializable {

    private Long id;

    @NotNull
    private String sensorCode;

    @NotNull
    private String sensorApi;

    @NotNull
    private String secretKey;

    @NotNull
    private Integer maxFailedAttempt;

    @NotNull
    private Long awaitFailedAttempt;

    private Plot plot;
}
