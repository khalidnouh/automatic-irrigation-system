package digtalfactory.irrigation.system.dto;

import digtalfactory.irrigation.system.model.IrrigationPeriod;
import digtalfactory.irrigation.system.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotDTO implements Serializable {
    private Long id;

    @NotNull
    private Double amount;

    @NotNull
    private Date irrigationDate;

    @NotNull
    private Status status;

    private IrrigationPeriod irrigationPeriod;
}
