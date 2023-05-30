package digtalfactory.irrigation.system.client.sensor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IrrigateResponse extends BaseResponse{
    private Boolean success;

    @Override
    public String toString() {
        return "{" +
                "success=" + success +
                '}';
    }
}
