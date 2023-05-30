package digtalfactory.irrigation.system.contract;

import digtalfactory.irrigation.system.dto.IrrigationPeriodDTO;
import digtalfactory.irrigation.system.dto.SlotDTO;
import digtalfactory.irrigation.system.model.Slot;
import org.springframework.http.ResponseEntity;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface SlotContarct extends TransactionalContract {

    SlotDTO save(SlotDTO slotDTO);

    List<SlotDTO> saveAll(List<SlotDTO> slotDTOS);

    SlotDTO update(SlotDTO slotDTO);

    SlotDTO updateSlotSatusToIrrigated(SlotDTO slotDTO);

    SlotDTO get(Long slotId);

    void delete(Long slotId);

    List<Slot>obtainNeedToBeIrrigatedSlots(Date irrigationDate);
}
