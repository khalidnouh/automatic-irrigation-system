package digtalfactory.irrigation.system.contract;

import digtalfactory.irrigation.system.client.sensor.SensorResponse;
import digtalfactory.irrigation.system.dto.SlotDTO;
import digtalfactory.irrigation.system.model.Plot;
import digtalfactory.irrigation.system.model.Slot;

import java.util.Date;
import java.util.List;

public interface SchedluerContarct extends TransactionalContract {

   SensorResponse irrigateFunction(List<Slot> slotListToBeIrrigated);
    void alert(String message,String userPhoneNumber,String twillioPhoneNumber) ;

    }
