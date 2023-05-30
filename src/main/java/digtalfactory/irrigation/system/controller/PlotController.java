package digtalfactory.irrigation.system.controller;//package digtalfactory.irrigation.system.controller;

import digtalfactory.irrigation.system.contract.AlertContract;
import digtalfactory.irrigation.system.dto.PlotDTO;
import digtalfactory.irrigation.system.model.AlertMessage;
import digtalfactory.irrigation.system.service.PlotService;
import digtalfactory.irrigation.system.service.SlotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/plot")
public class PlotController {
    Logger logger = LoggerFactory.getLogger(PlotController.class);

    private PlotService plotService;
    private SlotService slotService;
    private AlertContract alertContract;

    public PlotController(PlotService plotService, SlotService slotService, AlertContract alertContract) {
        this.plotService = plotService;
        this.slotService = slotService;
        this.alertContract = alertContract;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(HttpServletRequest request, @RequestBody @Valid PlotDTO plotDTO) {
        logger.debug("REST request to save plot : {}, URI: {}", plotDTO, request.getRequestURI());
        return new ResponseEntity<>(plotService.save(plotDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(HttpServletRequest request, @PathVariable Long id) {
        logger.debug("REST request to get plot : {}, URI: {}", id, request.getRequestURI());
        return new ResponseEntity<>(plotService.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Long id) {
        logger.debug("REST request to delete plot : {}, URI: {}", id, request.getRequestURI());
        plotService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        logger.debug("REST request to get all plots , URI: {}", request.getRequestURI());
        return new ResponseEntity<>(plotService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(HttpServletRequest request, @PathVariable Long id, @RequestBody @Valid PlotDTO plotDTO) {
        logger.debug("REST request to update update plot: {} , URI: {}", plotDTO, request.getRequestURI());
        return new ResponseEntity<>(plotService.update(id, plotDTO), HttpStatus.OK);
    }

}
