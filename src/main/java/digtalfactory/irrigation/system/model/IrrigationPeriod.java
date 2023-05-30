package digtalfactory.irrigation.system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Entity(name = "irrigation_period")
public class IrrigationPeriod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//can use sequence for enhacement
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    //to indicate irrigate each configured no of days, to calc slots between start and end dates
    @Column(name = "irrigate_scheduler_in_Days")
    private Integer irrigateSchedulerInDays;

    @OneToMany(mappedBy = "irrigationPeriod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Slot>slots;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plot_id")
    private Plot plot;
}
