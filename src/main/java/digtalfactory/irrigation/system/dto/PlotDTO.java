package digtalfactory.irrigation.system.dto;

import digtalfactory.irrigation.system.model.Sensor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlotDTO {
    private Long id;
    @NotNull
    private SensorDTO sensorDTO;
    @NotNull
    @Valid
    private List<IrrigationPeriodDTO>irrigationPeriodDTOS;
}
