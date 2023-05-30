package digtalfactory.irrigation.system.client.sensor;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import digtalfactory.irrigation.system.exception.BadRequestException;
import digtalfactory.irrigation.system.exception.SensoreRequestException;
import digtalfactory.irrigation.system.exception.UnreachableServiceException;
import digtalfactory.irrigation.system.model.Slot;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class SensorBaseClient {


    protected static final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<IrrigateResponse> makeSensorCall(String path, String secretKey, Slot slot) {
        ResponseEntity<IrrigateResponse> response=null;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("api-key",secretKey);

        HttpEntity<Slot> requestEntity =
                new HttpEntity<>(slot, headers);
        try {
            response = restTemplate.exchange(path, HttpMethod.POST, requestEntity,
                    IrrigateResponse.class);
            return response;
        }catch (HttpClientErrorException exception){
            IrrigateResponse res=new IrrigateResponse();
            res.setStatus(exception.getStatusText());
            res.setSuccess(false);
            res.setMessage(exception.getMessage());
            return  ResponseEntity.badRequest().body(res);
        }
    }

    protected <T extends BaseResponse> T post(String path,String secretKey, Slot request, Class<T> type) {
        try {
            HttpResponse<T> response =  Unirest
		            .post(path)
		            .header("api-key", secretKey)
		            .header("content-type", "application/json")
		            .body(request)
		            .asObject(type);
            
            if (response.getBody().getError() != null||response.getBody().getStatus() != null && response.getBody().getStatus().equalsIgnoreCase("fail")) {
                throw new SensoreRequestException("failed request",Boolean.FALSE);
            }
            return response.getBody();
        } catch (UnirestException e) {
            throw new UnreachableServiceException("failed to reach " + path + " in sensor interface : " + e.getMessage());
        }
    }

    protected <T extends BaseResponse> T get(String path,String secretKey, Class<T> type) {
        try {
            HttpResponse<T> response = Unirest
                    .get(path + "/")
                    .header("api-key", secretKey)
                    .header("content-type", "application/json")
                    .asObject(type);
            if (response.getBody().getStatus() != null && response.getBody().getStatus().equalsIgnoreCase("fail")) {
                throw new BadRequestException(response.getBody().getMessage());
            }
            if (response.getBody().getError() != null) {
                throw new BadRequestException(response.getBody().getError().getDescription());
            }
            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            throw new UnreachableServiceException("failed to reach " + path + " in sensor interface : " + e.getMessage());
        }
    }

}
