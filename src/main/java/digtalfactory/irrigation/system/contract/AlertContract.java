package digtalfactory.irrigation.system.contract;

import com.twilio.http.Response;
import com.twilio.rest.api.v2010.account.Message;
import digtalfactory.irrigation.system.model.AlertMessage;

public interface AlertContract {
    Response sendSMS(String userPhoneNumber, String twillioPhoneNumber, AlertMessage alertMessage);
}