package digtalfactory.irrigation.system.scheduler;

import digtalfactory.irrigation.system.client.sensor.IrrigateResponse;
import digtalfactory.irrigation.system.client.sensor.SensorClient;
import digtalfactory.irrigation.system.client.sensor.SensorResponse;
import digtalfactory.irrigation.system.contract.AlertContract;
import digtalfactory.irrigation.system.contract.SchedluerContarct;
import digtalfactory.irrigation.system.contract.SlotContarct;
import digtalfactory.irrigation.system.dto.SlotDTO;
import digtalfactory.irrigation.system.model.AlertMessage;
import digtalfactory.irrigation.system.model.Plot;
import digtalfactory.irrigation.system.model.Sensor;
import digtalfactory.irrigation.system.model.Slot;
import digtalfactory.irrigation.system.service.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class TaskSchedulConfig {
    private Logger logger = LoggerFactory.getLogger(TaskSchedulConfig.class);

    private SchedluerContarct schedluerContarct;

    public TaskSchedulConfig(SchedluerContarct schedluerContarct) {
        this.schedluerContarct = schedluerContarct;
    }

    //will run every 1 hour, intial delay is 30 minutes
    @Scheduled(fixedDelay = 3600000 ,initialDelay = 1800000 )
    public SensorResponse irrigateScheduler() {
        logger.debug("irrigate scheduler is running: {}",new Date());
        return schedluerContarct.irrigateFunction(null);
    }

}
