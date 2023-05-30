package digtalfactory.irrigation.system.repository;

import digtalfactory.irrigation.system.model.Plot;
import digtalfactory.irrigation.system.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepo extends JpaRepository<Sensor,Long> {


}
