import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import com.twilio.Twilio;
import com.twilio.http.Response;
import digtalfactory.irrigation.system.client.sensor.IrrigateResponse;
import digtalfactory.irrigation.system.client.sensor.SchedluerService;
import digtalfactory.irrigation.system.client.sensor.SensorClient;
import digtalfactory.irrigation.system.dto.IrrigationPeriodDTO;
import digtalfactory.irrigation.system.model.*;
import digtalfactory.irrigation.system.model.enums.Status;
import digtalfactory.irrigation.system.service.AlertService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//to enable orderring of units test
@AutoConfigureMockMvc
@TestPropertySource("classpath:application.properties")
public class IrrigationSystemUnitTests {

    @Value("${twilio.account.sid}")
    private String twilioAccountSid;
    @Value("${twilio.phone.number}")
    private String twillioPhoneNumber;
    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${alert.phone.number}")
    private String userPhoneNumber;
    @InjectMocks
    private AlertService alertService;
    String irrigateResponseSuccessed = "{\"success\":true}";
    String irrigateResponseFailed = "{\"success\":false}";
    private WireMockServer wireMockServer =null;
    //make a mocking server to simulate sensor gateway
    private void intializeMockingServer(String successResponse,String failedResponse) {
//        if (wireMockServer==null) {

            wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8383).notifier(new ConsoleNotifier(true)));
            wireMockServer.start();
//        }
        StringValuePattern stringValuePattern = new EqualToPattern("0023113");
        //for successResponse
        wireMockServer.stubFor(post(WireMock.urlEqualTo("/api/sensor"))
                .withHeader("api-key", stringValuePattern)
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(successResponse)));
        //for failedResponse
        StringValuePattern secretKeyNotValidPattern = new EqualToPattern ("0023114");
        wireMockServer.stubFor(post(WireMock.urlEqualTo("/api/sensor"))
                .withHeader("api-key", secretKeyNotValidPattern)
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(failedResponse)));
    }

    @BeforeEach
    public void initTest() {
//        if (this.wireMockServer!=null) {
//            this.wireMockServer.shutdown();
//            this.wireMockServer=null;
//        }
        intializeMockingServer(irrigateResponseSuccessed,irrigateResponseFailed);
    }
    @AfterEach
    public void clos(){
        wireMockServer.stop();
    }

    private Sensor getSensorAsObject() {
        Sensor sensor = new Sensor(1l, "001", "http://localhost:8383/api/sensor", "0023113", 3, 30l, null);
        return sensor;
    }

    private Sensor getSensorAsObjectToBeFaild() {
        Sensor sensor = new Sensor(1l, "001", "http://localhost:8383/api/sensor", "0023114", 3, 30l, null);
        return sensor;
    }

    private List<IrrigationPeriodDTO> getIrrigationPeriodList() {
        List<IrrigationPeriodDTO> irrigationPeriodDTOS = new ArrayList<>();
        IrrigationPeriodDTO irrigationPeriodDTO = new IrrigationPeriodDTO(1l, Double.valueOf(10000), new Date(), new Date(), 1, null, new Plot(1l, null, null));
        irrigationPeriodDTOS.add(irrigationPeriodDTO);
        return irrigationPeriodDTOS;
    }

    private IrrigationPeriod getIrrigationPeriod() {
        IrrigationPeriod irrigationPeriod = new IrrigationPeriod(1l, Double.valueOf(10000), new Date(), new Date(), 1, null, new Plot(1l, null, null));
        return irrigationPeriod;
    }

    private Slot getslotAsObject() {
        Slot slot = new Slot(1l, Double.valueOf(10000), new Date(), Status.NOT_IRRIGATED, getIrrigationPeriod());
        return slot;
    }
    private Slot getslotAsObject2() {
        Slot slot = new Slot(2l, Double.valueOf(20000), new Date(), Status.NOT_IRRIGATED, getIrrigationPeriod());
        return slot;
    }


    @Test
    @DisplayName("test case for calling irrigate gateway to do some action,happy scenario")
    public void testIrrigate_Happy_Scenario() {
        getIrrigationPeriodList();
        SensorClient sensorClient = new SensorClient();
        IrrigateResponse response = sensorClient.irrigate(getSensorAsObject(), getslotAsObject());
        Assertions.assertEquals(response.getSuccess(), Boolean.TRUE);
    }

    @Test
    @DisplayName("test case for calling irrigate gateway to do some action,bad scenario")
    public void testIrrigate_Bad_Scenario() {
        getIrrigationPeriodList();
        SensorClient sensorClient = new SensorClient();
        IrrigateResponse response = sensorClient.irrigate(getSensorAsObjectToBeFaild(), getslotAsObject2());
        Assertions.assertEquals(response.getSuccess(), Boolean.FALSE);
    }

    @Test
    @DisplayName("test case for testing alerting system with twillio gateway,happy scenario")
    public void testAlert_HappyScenario() {
        initTWillio();
        Response response = alertService.sendSMS(userPhoneNumber, twillioPhoneNumber, new AlertMessage("sensor not responding ,kindly check "));
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("test case for testing alerting system with twillio gateway,bad scenario")
    public void testAlert_BadScenario() {
        initTWillio();
        //make phone not invalid
        StringBuilder notValidPhone=new StringBuilder(userPhoneNumber).append("456644664");
        Response response = alertService.sendSMS(notValidPhone.toString(), twillioPhoneNumber, new AlertMessage("sensor not responding ,kindly check "));
        Assertions.assertNotEquals(response.getStatusCode(), HttpStatus.CREATED.value());
    }
    void initTWillio(){
        Twilio.init(
                twilioAccountSid,
                twilioAuthToken
        );
    }
}
