package digtalfactory.irrigation.system.contract;

import digtalfactory.irrigation.system.dto.PlotDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlotContarct extends TransactionalContract {
    PlotDTO save(PlotDTO plotDTO);

    PlotDTO update(Long id,PlotDTO plotDTO);

    PlotDTO get(Long plotId);

    List<PlotDTO> getAll();

    void delete(Long plotId);

}
