package digtalfactory.irrigation.system.service;

import digtalfactory.irrigation.system.client.sensor.IrrigateResponse;
import digtalfactory.irrigation.system.client.sensor.SensorResponse;
import digtalfactory.irrigation.system.contract.SlotContarct;
import digtalfactory.irrigation.system.dto.SlotDTO;
import digtalfactory.irrigation.system.exception.ObjectNotFoundException;
import digtalfactory.irrigation.system.model.Plot;
import digtalfactory.irrigation.system.model.Sensor;
import digtalfactory.irrigation.system.model.Slot;
import digtalfactory.irrigation.system.model.enums.Status;
import digtalfactory.irrigation.system.repository.SlotRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SlotService implements SlotContarct {
    private Logger logger = LoggerFactory.getLogger(PlotService.class);

    private SlotRepo slotRepo;

    public SlotService(SlotRepo slotRepo) {
        this.slotRepo = slotRepo;
    }

    @Override
    public SlotDTO save(SlotDTO slotDTO) {
        logger.debug("Request to save slot : {}", slotDTO);
        Slot slot = new Slot();
        BeanUtils.copyProperties(slotDTO, slot);
        slot = slotRepo.save(slot);
        BeanUtils.copyProperties(slot, slotDTO);
        return slotDTO;
    }

    @Override
    public List<SlotDTO> saveAll(List<SlotDTO> slotDTOS) {
        logger.debug("Request to save all slots : {}", slotDTOS);
        List<SlotDTO> savedSlotDTOS=new ArrayList<>();
        List<Slot> slotsToBeSaved=new ArrayList<>();
        for (SlotDTO slotDTO : slotDTOS) {
            Slot slot = new Slot();
            BeanUtils.copyProperties(slotDTO, slot);
            slotsToBeSaved.add(slot);
        }
        slotsToBeSaved=slotRepo.saveAll(slotsToBeSaved);
        slotsToBeSaved.forEach(slot -> {
            SlotDTO slotDTO=new SlotDTO();
            BeanUtils.copyProperties(slot,slotDTO);
            savedSlotDTOS.add(slotDTO);
        });
        return savedSlotDTOS;
    }

    @Override
    public SlotDTO update(SlotDTO slotDTO) {
        logger.debug("Request to update slot : {}", slotDTO);
        Optional<Slot> slotOptional = slotRepo.findById(slotDTO.getId());
        if (slotOptional.isEmpty())
            throw new ObjectNotFoundException("no Slot object found with passed criteria");
        Slot updatedSlot = slotOptional.get();
        BeanUtils.copyProperties(slotDTO, updatedSlot);
        return slotDTO;
    }

    @Override
    public SlotDTO updateSlotSatusToIrrigated(SlotDTO slotDTO) {
        logger.debug("Request to update slot status : {}", slotDTO);
        Optional<Slot> slotOptional = slotRepo.findById(slotDTO.getId());
        if (slotOptional.isEmpty())
            throw new ObjectNotFoundException("no Slot object found with passed criteria");
        Slot updatedSlot = slotOptional.get();
        updatedSlot.setStatus(Status.IRRIGATED);
        slotRepo.save(updatedSlot);
        BeanUtils.copyProperties(updatedSlot, slotDTO);
        return slotDTO;
    }

    @Override
    public SlotDTO get(Long slotId) {
        logger.debug("Request to get slot : {}", slotId);
        Optional<Slot> slotOptional = slotRepo.findById(slotId);
        if (slotOptional.isEmpty())
            throw new ObjectNotFoundException("no Slot object found with passed criteria");
        SlotDTO slotDTO = new SlotDTO();
        BeanUtils.copyProperties(slotOptional.get(), slotDTO);
        return slotDTO;
    }

    @Override
    public void delete(Long slotId) {
        logger.debug("Request to delete slot : {}", slotId);
        Optional<Slot> slotOptional = slotRepo.findById(slotId);
        if (slotOptional.isEmpty())
            throw new ObjectNotFoundException("no Slot object found with passed criteria");
        slotRepo.delete(slotOptional.get());
    }

    public List<Slot>obtainNeedToBeIrrigatedSlots(Date irrigationDate){
        //query slots
        List<Slot> slotList =slotRepo.findAllByIrrigationDateAndStatus(irrigationDate, Status.NOT_IRRIGATED);
        if (!CollectionUtils.isEmpty(slotList)){
            return slotList;
        }
            return null;
    }


}
