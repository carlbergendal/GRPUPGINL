package WebService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeasurementRepository
{
    protected String url;

    public MeasurementRepository()
    {
        this.url = "jdbc:sqlserver://elviras-sql-server.database.windows.net:1433;database=elviras-sql-db;user=dreamteam@elviras-sql-server;password=S0ckerlönn!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    }

    public Measurement getLastMeasurementFromDB()
    {
        ResultSet rs = null;
        Measurement m = new Measurement();
        String query = "select top 1 id, temp, timestamp from measurements order by id desc";

        try
        {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement(query);

            rs = stmt.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String temperature = rs.getString("temp");
                Date timeStamp = rs.getDate("timestamp");
                m = new Measurement(temperature);
                m.setId(id);
                m.setTimeStamp(timeStamp);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return m;
    }

    public void insertLastMeasurementToDB(Measurement lastMeasurement)
    {
        String query = "insert into measurements (temp) values (?)";

        try
        {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, lastMeasurement.getTemperature());
            int numberOfUpdates = stmt.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<Measurement> getMeasurementReport()
    {
        ResultSet rs = null;
        Measurement m = new Measurement();
        List<Measurement> measurementReport = new ArrayList<Measurement>();
        String query = "select top 10 id, temp, timestamp from measurements order by id desc";
        try
        {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement(query);

            rs = stmt.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String temperature = rs.getString("temp");
                Date timeStamp = rs.getDate("timestamp");
                m = new Measurement(temperature);
                m.setId(id);
                m.setTimeStamp(timeStamp);

                measurementReport.add(m);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return measurementReport;
    }
}