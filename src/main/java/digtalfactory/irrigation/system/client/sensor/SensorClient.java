package digtalfactory.irrigation.system.client.sensor;

import digtalfactory.irrigation.system.dto.SlotDTO;
import digtalfactory.irrigation.system.exception.SensoreRequestException;
import digtalfactory.irrigation.system.model.Sensor;
import digtalfactory.irrigation.system.model.Slot;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SensorClient extends SensorBaseClient {

    public IrrigateResponse irrigate(Sensor sensor, Slot request) {
        int faildTrial = 0;
        IrrigateResponse irrigateResponse=new IrrigateResponse();
        irrigateResponse.setSuccess(false);
        while (faildTrial <= sensor.getMaxFailedAttempt()) {
            try {
                request.setIrrigationPeriod(null);
                 irrigateResponse = makeSensorCall(sensor.getSensorApi(), sensor.getSecretKey(), request).getBody();
                return irrigateResponse;
            } catch (SensoreRequestException exception) {
                faildTrial++;
                try {
                    Thread.sleep(sensor.getAwaitFailedAttempt());
                } catch (InterruptedException e) {
                    irrigateResponse.setSuccess(exception.getSuccess());
                }
            }
        }
        return irrigateResponse;
    }

}
