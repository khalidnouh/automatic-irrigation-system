package digtalfactory.irrigation.system.repository;

import digtalfactory.irrigation.system.model.Plot;
import digtalfactory.irrigation.system.model.Slot;
import digtalfactory.irrigation.system.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public interface SlotRepo extends JpaRepository<Slot,Long> {
//    @Query(value ="select * from slot  where (date_trunc('day',irrigation_dte)) =(date_trunc('day',?1)) ",nativeQuery = true)
    List<Slot> findAllByIrrigationDateAndStatus( @Temporal(TemporalType.DATE) Date date, Status status);
}
