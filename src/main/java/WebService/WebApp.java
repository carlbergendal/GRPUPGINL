package WebService;

import org.springframework.web.client.RestTemplate;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@ServerEndpoint("/ws")
public class WebApp {

    @OnOpen
    public void open(Session session) throws IOException, EncodeException {
        System.out.println("inne i open");
        String Report = "";
        String url = "http://localhost:8080/getMeasurementReport";
        RestTemplate restTemplate = new RestTemplate();
        Measurement[] resultArray = restTemplate.getForObject(url, Measurement[].class);
        List<Measurement> result = Arrays.asList(resultArray);//  asList returnear 10 st ish
        for(Measurement m : result)
        {
            Report += "ID: " + m.getId() + " temperatur: " + m.getTemperature() + " TimeStamp: " + m.getTimeStamp() + "\n";

        }
        session.getBasicRemote().sendText(Report);
    }


    /*@OnMessage
    public void handleMessage(Session session) throws IOException, EncodeException
    {
        String message = "";
        String url = "http://localhost:8080/getLastMeasurement";
        RestTemplate restTemplate = new RestTemplate();
        Measurement result = restTemplate.getForObject(url, Measurement.class);
        message = "Temperatur jut nu: " + result.getTemperature();
        for (Session peer : session.getOpenSessions())
            peer.getBasicRemote().sendText(message);
    }

     */


}