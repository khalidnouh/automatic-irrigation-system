package digtalfactory.irrigation.system.service;

import digtalfactory.irrigation.system.contract.IrrigationPeriodContarct;
import digtalfactory.irrigation.system.contract.SlotContarct;
import digtalfactory.irrigation.system.dto.IrrigationPeriodDTO;
import digtalfactory.irrigation.system.dto.SlotDTO;
import digtalfactory.irrigation.system.exception.ObjectNotFoundException;
import digtalfactory.irrigation.system.model.IrrigationPeriod;
import digtalfactory.irrigation.system.model.enums.Status;
import digtalfactory.irrigation.system.repository.IrrigationPeriodRepo;
import digtalfactory.irrigation.system.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IrrigationPeriodService implements IrrigationPeriodContarct {
    private Logger logger = LoggerFactory.getLogger(PlotService.class);

    private IrrigationPeriodRepo irrigationPeriodRepo;
    @Autowired
    private SlotContarct slotContarct;
    @Autowired
    private DateUtils dateUtils;

    public IrrigationPeriodService(IrrigationPeriodRepo irrigationPeriodRepo) {
        this.irrigationPeriodRepo = irrigationPeriodRepo;
    }

    @Override
    public IrrigationPeriodDTO save(IrrigationPeriodDTO irrigationPeriodDTO) {
        logger.debug("Request to save irrigation Period : {}", irrigationPeriodDTO);
        IrrigationPeriod irrigationPeriod = new IrrigationPeriod();
        BeanUtils.copyProperties(irrigationPeriodDTO, irrigationPeriod);

        irrigationPeriod = irrigationPeriodRepo.save(irrigationPeriod);

        //some action to calc slots and save
        List<SlotDTO>slotList=new ArrayList<>();
        List<Date> dateList= dateUtils.getDaysBetweenTwoDates(irrigationPeriod.getStartDate(),irrigationPeriod.getEndDate(), irrigationPeriod.getIrrigateSchedulerInDays());
        final Double amountPerSlot=irrigationPeriod.getAmount()/ dateList.size();
        for (Date date : dateList) {
            SlotDTO slot=new SlotDTO();
            slot.setIrrigationDate(date);
            slot.setAmount(amountPerSlot);
            slot.setStatus(Status.NOT_IRRIGATED);
            slot.setIrrigationPeriod(irrigationPeriod);
            slotList.add(slot);
        }
        slotContarct.saveAll(slotList);

        //copy saved values on dto to returned
        BeanUtils.copyProperties(irrigationPeriod, irrigationPeriodDTO);
        return irrigationPeriodDTO;
    }

    @Override
    public IrrigationPeriodDTO update(IrrigationPeriodDTO irrigationPeriodDTO){
        logger.debug("Request to update irrigation Period : {}", irrigationPeriodDTO);

        //find object
        Optional<IrrigationPeriod> irrigationPeriodOptional = irrigationPeriodRepo.findById(irrigationPeriodDTO.getId());
        if (irrigationPeriodOptional.isEmpty())
            throw new ObjectNotFoundException("no IrrigationPeriod object found with passed criteria");

        BeanUtils.copyProperties(irrigationPeriodDTO, irrigationPeriodOptional.get());
        IrrigationPeriod updatedIrrigationPeriod=irrigationPeriodRepo.save(irrigationPeriodOptional.get());

        //update dto after saving
        BeanUtils.copyProperties(updatedIrrigationPeriod,irrigationPeriodDTO);
        return irrigationPeriodDTO;
    }

    @Override
    public IrrigationPeriodDTO get(Long irrigationPeriodId) {
        logger.debug("Request to get irrigation Period : {}", irrigationPeriodId);
        Optional<IrrigationPeriod>irrigationPeriodOptional=irrigationPeriodRepo.findById(irrigationPeriodId);
        if (irrigationPeriodOptional.isEmpty())
            throw new ObjectNotFoundException("no IrrigationPeriod object found with passed criteria");
        IrrigationPeriodDTO periodDTO=new IrrigationPeriodDTO();
        BeanUtils.copyProperties(irrigationPeriodOptional.get(),periodDTO);
        return periodDTO;
    }

    @Override
    public void delete(Long irrigationPeriodId) {
        logger.debug("Request to delete irrigation Period : {}", irrigationPeriodId);
        irrigationPeriodRepo.deleteById(irrigationPeriodId);
    }
}
