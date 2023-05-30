package digtalfactory.irrigation.system.contract;

import digtalfactory.irrigation.system.dto.IrrigationPeriodDTO;


public interface IrrigationPeriodContarct extends TransactionalContract {
    IrrigationPeriodDTO save(IrrigationPeriodDTO irrigationPeriodDTO);

    IrrigationPeriodDTO update(IrrigationPeriodDTO irrigationPeriodDTO) throws Exception;

    IrrigationPeriodDTO get(Long irrigationPeriodId);

    void delete(Long irrigationPeriodId);
}
