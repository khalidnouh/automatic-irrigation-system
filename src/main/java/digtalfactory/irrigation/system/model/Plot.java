package digtalfactory.irrigation.system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "plot")
public class Plot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //can use sequence for enhacement
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "plot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<IrrigationPeriod> irrigationPeriodList;


    @OneToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
}
