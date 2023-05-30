package digtalfactory.irrigation.system.service;

import digtalfactory.irrigation.system.contract.SensorContarct;
import digtalfactory.irrigation.system.dto.SensorDTO;
import digtalfactory.irrigation.system.exception.ObjectNotFoundException;
import digtalfactory.irrigation.system.model.Sensor;
import digtalfactory.irrigation.system.model.Slot;
import digtalfactory.irrigation.system.repository.SensorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService implements SensorContarct {
    private Logger logger = LoggerFactory.getLogger(SensorService.class);

    private SensorRepo sensorRepo;

    public SensorService(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;
    }

    @Override
    public SensorDTO save(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        BeanUtils.copyProperties(sensorDTO, sensor);
        sensor = sensorRepo.save(sensor);
        sensorDTO.setId(sensor.getId());
        return sensorDTO;
    }

    @Override
    public SensorDTO update(Long id, SensorDTO sensorDTO) {
        logger.debug("Request to update sensor : {}", sensorDTO);
        Optional<Sensor> sensor = sensorRepo.findById(id);
        if (sensor.isEmpty())
            throw new ObjectNotFoundException("no Sensor object found with passed criteria");
        BeanUtils.copyProperties(sensorDTO, sensor, "id");
        sensorRepo.save(sensor.get());
        return sensorDTO;
    }

    @Override
    public SensorDTO get(Long sensorId) {
        logger.debug("Request to get sensor : {}", sensorId);
        Optional<Sensor> sensor = sensorRepo.findById(sensorId);
        if (sensor.isEmpty())
            throw new ObjectNotFoundException("no Sensor object found with passed criteria");
        SensorDTO sensorDTO = new SensorDTO();
        BeanUtils.copyProperties(sensor.get(), sensorDTO);
        return sensorDTO;
    }

    @Override
    public Sensor getAsObject(Long sensorId) {
        logger.debug("Request to get sensor : {}", sensorId);
        Optional<Sensor> sensor = sensorRepo.findById(sensorId);
        if (sensor.isEmpty())
            throw new ObjectNotFoundException("no Sensor object found with passed criteria");
        return sensor.get();
    }

    @Override
    public void delete(Long sensorId) {
        logger.debug("Request to delete sensor : {}", sensorId);
        Optional<Sensor> sensor = sensorRepo.findById(sensorId);
        if (sensor.isEmpty())
            throw new ObjectNotFoundException("no Sensor object found with passed criteria");
        sensorRepo.delete(sensor.get());
    }
}
