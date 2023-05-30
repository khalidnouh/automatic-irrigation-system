package digtalfactory.irrigation.system.service;

import com.twilio.exception.ApiException;
import com.twilio.http.Response;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import digtalfactory.irrigation.system.contract.AlertContract;
import digtalfactory.irrigation.system.model.AlertMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AlertService implements AlertContract {
    private Logger logger = LoggerFactory.getLogger(AlertService.class);



    /**
     * This method sends SMS using the static creator() method
     * Message.creator() has multiple implementations
     * For our scenario, it takes 3 parameters:
     * phoneNumber of the receiver as the first param
     * phoneNumber of the sender as the second param (Twilio number)
     * message (or alertMessage) as the third param
     */
    @Override
    public Response sendSMS(String userPhoneNumber, String twillioPhoneNumber, AlertMessage alertMessage) {
        logger.debug("Request to sendSMS : {},{}", userPhoneNumber,alertMessage);
        Response response = null;
        try {
            Message message = Message.creator(new PhoneNumber(userPhoneNumber),
                    new PhoneNumber(twillioPhoneNumber),
                    alertMessage.getMessage()).create();
            response = new Response(message.getStatus().toString(), HttpStatus.CREATED.value());
        } catch (ApiException apiException) {
            response = new Response(apiException.getMoreInfo(), apiException.getStatusCode());
        }
        return response;
    }
}
