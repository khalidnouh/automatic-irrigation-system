package digtalfactory.irrigation.system.repository;

import digtalfactory.irrigation.system.model.AuditEventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditEventLogRepo extends JpaRepository<AuditEventLog,Long> {


}
