package digtalfactory.irrigation.system.service;

import digtalfactory.irrigation.system.contract.IrrigationPeriodContarct;
import digtalfactory.irrigation.system.contract.PlotContarct;
import digtalfactory.irrigation.system.contract.SensorContarct;
import digtalfactory.irrigation.system.dto.IrrigationPeriodDTO;
import digtalfactory.irrigation.system.dto.PlotDTO;
import digtalfactory.irrigation.system.exception.ObjectNotFoundException;
import digtalfactory.irrigation.system.model.Plot;
import digtalfactory.irrigation.system.model.Sensor;
import digtalfactory.irrigation.system.repository.PlotRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlotService implements PlotContarct {
    Logger logger = LoggerFactory.getLogger(PlotService.class);

    private PlotRepo plotRepo;
    private SensorContarct sensorContarct;
    private IrrigationPeriodContarct irrigationPeriodContarct;

    public PlotService(PlotRepo plotRepo, SensorContarct sensorContarct, IrrigationPeriodContarct irrigationPeriodContarct) {
        this.plotRepo = plotRepo;
        this.sensorContarct = sensorContarct;
        this.irrigationPeriodContarct = irrigationPeriodContarct;
    }

    @Override
    public PlotDTO save(PlotDTO plotDTO) {
        logger.debug("Request to save plot : {}", plotDTO);
        //save plot
        Plot plot = new Plot();
        BeanUtils.copyProperties(plotDTO, plot);

        //setting sensor
        Sensor sensor=sensorContarct.getAsObject(plot.getSensor().getId());
        plot.setSensor(sensor);
        plot = plotRepo.save(plot);

        //loop on irrigation period of dto
        for (IrrigationPeriodDTO irrigationPeriodDTO : plotDTO.getIrrigationPeriodDTOS()) {
            irrigationPeriodDTO.setPlot(plot);
            irrigationPeriodContarct.save(irrigationPeriodDTO);
        }
        BeanUtils.copyProperties(plot, plotDTO);
        return plotDTO;
    }

    @Override
    public PlotDTO update(Long id, PlotDTO plotDTO) {
        logger.debug("Request to update plot : {}", plotDTO);
        //find object
        Optional<Plot> plotOptional = plotRepo.findById(id);
        if (plotOptional.isEmpty())
            throw new ObjectNotFoundException("no plot object found with passed criteria");

        Plot updatedPlot = plotOptional.get();
        //delete oll related objects
        plotOptional.get().getIrrigationPeriodList().forEach(irrigationPeriod -> {
            irrigationPeriodContarct.delete(irrigationPeriod.getId());
        });

        //update plot object
        BeanUtils.copyProperties(plotDTO, updatedPlot);
        updatedPlot = plotRepo.save(updatedPlot);

        //save related objects
        for (IrrigationPeriodDTO irrigationPeriodDTO : plotDTO.getIrrigationPeriodDTOS()) {
            irrigationPeriodDTO.setPlot(updatedPlot);
            irrigationPeriodContarct.save(irrigationPeriodDTO);
        }
        BeanUtils.copyProperties(updatedPlot, plotDTO);
        return plotDTO;
    }

    @Override
    public PlotDTO get(Long plotId) {
        logger.debug("Request to get plot by id : {}", plotId);

        Optional<Plot> plotOptional = plotRepo.findById(plotId);
        if (plotOptional.isEmpty())
            throw new ObjectNotFoundException("no plot object found with passed criteria");
        PlotDTO plotDTO = new PlotDTO();
        BeanUtils.copyProperties(plotOptional.get(), plotDTO);
        return plotDTO;
    }

    @Override
    public List<PlotDTO> getAll() {
        logger.debug("Request to get all plots ");
        List<Plot> plotList = plotRepo.findAll();
        List<PlotDTO> plotDTOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(plotList)) {
            for (Plot plot : plotList) {

                PlotDTO plotDTO = new PlotDTO();
                BeanUtils.copyProperties(plot, plotDTO);
                plotDTOList.add(plotDTO);
            }
        }
        return plotDTOList;
    }

    @Override
    public void delete(Long plotId) {
        logger.debug("Request to delete plot by id : {}", plotId);
        plotRepo.deleteById(plotId);

    }
}
