package digtalfactory.irrigation.system.contract;

import digtalfactory.irrigation.system.dto.AuditEventLogDTO;

import java.util.List;

public interface AuditEventLogContract extends TransactionalContract {
    AuditEventLogDTO save(AuditEventLogDTO auditEventLogDTO);

    AuditEventLogDTO update(Long id,AuditEventLogDTO auditEventLogDTO) throws Exception;

    AuditEventLogDTO get(Long id);

    List<AuditEventLogDTO> getAll(Long id);
}
