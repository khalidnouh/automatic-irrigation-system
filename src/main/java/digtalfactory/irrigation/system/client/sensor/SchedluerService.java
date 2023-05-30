package digtalfactory.irrigation.system.client.sensor;

import digtalfactory.irrigation.system.contract.AlertContract;
import digtalfactory.irrigation.system.contract.SchedluerContarct;
import digtalfactory.irrigation.system.contract.SensorContarct;
import digtalfactory.irrigation.system.contract.SlotContarct;
import digtalfactory.irrigation.system.dto.SlotDTO;
import digtalfactory.irrigation.system.model.AlertMessage;
import digtalfactory.irrigation.system.model.Plot;
import digtalfactory.irrigation.system.model.Sensor;
import digtalfactory.irrigation.system.model.Slot;
import digtalfactory.irrigation.system.scheduler.TaskSchedulConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class SchedluerService implements SchedluerContarct {
    private Logger logger = LoggerFactory.getLogger(SchedluerService.class);

    @Value("${alert.fixed.message}")
    private String fixedMessage;

    @Value("${alert.phone.number}")
    private String alertPhoneNumber;

    @Value("${twilio.phone.number}")
    private String twillioPhoneNumber;

    private SensorContarct sensorContarct;
    private SlotContarct slotContarct;
    private SensorClient sensorClient;
    private AlertContract alertContract;

    public SchedluerService() {
    }

    public SchedluerService(SensorContarct sensorContarct, SlotContarct slotContarct, SensorClient sensorClient, AlertContract alertContract) {
        this.sensorContarct = sensorContarct;
        this.slotContarct = slotContarct;
        this.sensorClient = sensorClient;
        this.alertContract = alertContract;
    }

    @Override
    public SensorResponse irrigateFunction(List<Slot> slotListToBeIrrigated) {
        logger.debug("irrigateFunction is fired: {}",slotListToBeIrrigated);

        SensorResponse sensorResponse = new SensorResponse();
        int noOfSuccefullSensors = 0;
        if (CollectionUtils.isEmpty(slotListToBeIrrigated))
            slotListToBeIrrigated = slotContarct.obtainNeedToBeIrrigatedSlots(new Date());
        if (!CollectionUtils.isEmpty(slotListToBeIrrigated)) {
            for (Slot slot : slotListToBeIrrigated) {
                Plot plot = slot.getIrrigationPeriod().getPlot();
                Sensor sensor = plot.getSensor();
                IrrigateResponse irrigateResponse = sensorClient.irrigate(sensor, slot);
                if (irrigateResponse.getSuccess()) {
                    noOfSuccefullSensors++;
                    SlotDTO slotDTO = new SlotDTO();
                    BeanUtils.copyProperties(slot, slotDTO);
                    slotContarct.updateSlotSatusToIrrigated(slotDTO);
                } else {
                    alert(fixedMessage,alertPhoneNumber,twillioPhoneNumber);
                }
            }
            sensorResponse.setNoOfSuccefullSensors(noOfSuccefullSensors);
        }
        return sensorResponse;
    }

    public void alert(String message,String userPhoneNumber,String twillioPhoneNumber) {
        logger.debug("alert : {},{},{}",message,userPhoneNumber,twillioPhoneNumber);
        AlertMessage alertMessage = new AlertMessage(message);
        alertContract.sendSMS(userPhoneNumber,twillioPhoneNumber, alertMessage);
    }
}
