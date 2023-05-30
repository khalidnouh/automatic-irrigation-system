package digtalfactory.irrigation.system.contract;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = {Exception.class})
public interface TransactionalContract {
}
