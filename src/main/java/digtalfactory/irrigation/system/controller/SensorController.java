package digtalfactory.irrigation.system.controller;//package digtalfactory.irrigation.system.controller;

import digtalfactory.irrigation.system.contract.SensorContarct;
import digtalfactory.irrigation.system.dto.SensorDTO;
import digtalfactory.irrigation.system.service.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {
    Logger logger = LoggerFactory.getLogger(SensorController.class);
    private SensorContarct sensorContarct;

    public SensorController(SensorContarct sensorContarct) {
        this.sensorContarct = sensorContarct;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<SensorDTO> save(HttpServletRequest request, @Valid @RequestBody SensorDTO sensorDTO) {
        logger.debug("REST request to save sensor : {}, URI: {}", sensorDTO, request.getRequestURI());
        return new ResponseEntity<>(sensorContarct.save(sensorDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<SensorDTO> get(HttpServletRequest request, @PathVariable Long id) {
        logger.debug("REST request to get sensor : {}, URI: {}", id, request.getRequestURI());
        return new ResponseEntity<>(sensorContarct.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SensorDTO> update(HttpServletRequest request, @PathVariable Long id, @Valid @RequestBody SensorDTO sensorDTO) {
        logger.debug("REST request to update sensor : {}, URI: {}", sensorDTO, request.getRequestURI());
        return new ResponseEntity<>(sensorContarct.update(id, sensorDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SensorDTO> delete(HttpServletRequest request, @PathVariable Long id) {
        logger.debug("REST request to delete sensor : {}, URI: {}", id, request.getRequestURI());
        sensorContarct.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
