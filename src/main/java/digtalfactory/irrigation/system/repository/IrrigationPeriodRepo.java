package digtalfactory.irrigation.system.repository;

import digtalfactory.irrigation.system.model.IrrigationPeriod;
import digtalfactory.irrigation.system.model.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrrigationPeriodRepo extends JpaRepository<IrrigationPeriod,Long> {


}
