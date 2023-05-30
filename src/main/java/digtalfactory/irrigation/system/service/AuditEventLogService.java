package digtalfactory.irrigation.system.service;

import digtalfactory.irrigation.system.contract.AuditEventLogContract;
import digtalfactory.irrigation.system.dto.AuditEventLogDTO;
import digtalfactory.irrigation.system.exception.ObjectNotFoundException;
import digtalfactory.irrigation.system.model.AuditEventLog;
import digtalfactory.irrigation.system.model.Plot;
import digtalfactory.irrigation.system.repository.AuditEventLogRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuditEventLogService implements AuditEventLogContract {
    Logger logger = LoggerFactory.getLogger(PlotService.class);

    private AuditEventLogRepo auditEventLogRepo;

    public AuditEventLogService(AuditEventLogRepo auditEventLogRepo) {
        this.auditEventLogRepo = auditEventLogRepo;
    }

    @Override
    public AuditEventLogDTO save(AuditEventLogDTO auditEventLogDTO) {
        logger.debug("Request to save auditEventLog : {}", auditEventLogDTO);
        AuditEventLog auditEventLog = new AuditEventLog();
        BeanUtils.copyProperties(auditEventLogDTO, auditEventLog);
        auditEventLog = auditEventLogRepo.save(auditEventLog);
        auditEventLogDTO.setId(auditEventLog.getId());
        return auditEventLogDTO;
    }

    @Override
    public AuditEventLogDTO update(Long id, AuditEventLogDTO auditEventLogDTO) throws Exception {
        logger.debug("Request to update auditEventLog : {}", auditEventLogDTO);
        //find object
        Optional<AuditEventLog> auditEventLog = auditEventLogRepo.findById(id);
        if (auditEventLog.isEmpty())
            throw new ObjectNotFoundException("no auditEventLog object found with passed criteria");
        AuditEventLog updated = auditEventLog.get();
        BeanUtils.copyProperties(auditEventLogDTO, updated, "id");
        updated = auditEventLogRepo.save(updated);
        BeanUtils.copyProperties(updated, auditEventLogDTO);
        return auditEventLogDTO;
    }

    @Override
    public AuditEventLogDTO get(Long id) {
        logger.debug("Request to get auditEventLog : {}", id);
        //find object
        Optional<AuditEventLog> auditEventLog = auditEventLogRepo.findById(id);
        if (auditEventLog.isEmpty())
            throw new ObjectNotFoundException("no auditEventLog object found with passed criteria");
        AuditEventLogDTO auditEventLogDTO = new AuditEventLogDTO();
        BeanUtils.copyProperties(auditEventLog.get(), auditEventLogDTO);
        return auditEventLogDTO;
    }

    @Override
    public List<AuditEventLogDTO> getAll(Long id) {
        List<AuditEventLogDTO> auditEventLogDTOS = new ArrayList<>();
        auditEventLogRepo.findAll().forEach(auditEventLog -> {
            AuditEventLogDTO temp = new AuditEventLogDTO();
            BeanUtils.copyProperties(auditEventLog, temp);
            auditEventLogDTOS.add(temp);
        });
        return auditEventLogDTOS;
    }
}
