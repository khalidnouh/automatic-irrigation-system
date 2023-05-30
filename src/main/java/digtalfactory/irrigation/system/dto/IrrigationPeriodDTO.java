package digtalfactory.irrigation.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import digtalfactory.irrigation.system.model.Plot;
import digtalfactory.irrigation.system.model.Slot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IrrigationPeriodDTO {

    private Long id;
    @NotNull
    private Double amount;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date startDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date endDate;

    @NotNull
    //to indicate irrigate each configured no of days, to calc slots between start and end dates
    private Integer irrigateSchedulerInDays;

    @JsonIgnore
    private List<Slot> slots;

    private Plot plot;
}
