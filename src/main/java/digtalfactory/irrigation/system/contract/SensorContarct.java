package digtalfactory.irrigation.system.contract;

import digtalfactory.irrigation.system.dto.SensorDTO;
import digtalfactory.irrigation.system.dto.SlotDTO;
import digtalfactory.irrigation.system.model.Sensor;
import digtalfactory.irrigation.system.model.Slot;

import java.util.Date;
import java.util.List;

public interface SensorContarct extends TransactionalContract {

    SensorDTO save(SensorDTO sensorDTO);

    SensorDTO update(Long id,SensorDTO sensorDTO);

    SensorDTO get(Long sensorId);

    Sensor getAsObject(Long sensorId);

    void delete(Long sensorId);

}
