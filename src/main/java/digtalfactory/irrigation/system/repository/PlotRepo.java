package digtalfactory.irrigation.system.repository;

import digtalfactory.irrigation.system.model.AuditEventLog;
import digtalfactory.irrigation.system.model.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotRepo extends JpaRepository<Plot,Long> {


}
