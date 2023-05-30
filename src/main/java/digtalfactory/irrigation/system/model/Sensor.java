package digtalfactory.irrigation.system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sensor")
public class Sensor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //can use sequence for enhacement
    @Column(name = "id")
    private Long id;

    @Column(name = "sensor_code")
    private String sensorCode;//sensor per each plot

    @Column(name = "sensor_api")
    private String sensorApi;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "max_failed_attempt")
    private Integer maxFailedAttempt;

    @Column(name = "await_failed_attempt")
    private Long awaitFailedAttempt;

    @OneToOne(mappedBy = "sensor", fetch = FetchType.LAZY)
    private Plot plot;
}
