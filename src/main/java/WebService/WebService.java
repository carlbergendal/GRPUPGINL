package WebService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class WebService
{
    MeasurementRepository r = new MeasurementRepository();

    public WebService()
    {
    }

    @RequestMapping(value = "/getLastMeasurement", produces = "application/json")
    public Measurement getLastMeasurementFromDB()
    {
        r = new MeasurementRepository();
        return r.getLastMeasurementFromDB();
    }

    @RequestMapping(value = "/getMeasurementReport", produces = "application/json")
    public List<Measurement> getMeasurementReport()
    {
        r = new MeasurementRepository();
        return r.getMeasurementReport();
    }
}
