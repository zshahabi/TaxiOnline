package ir.maktab.database;

import ir.maktab.Person.One_Driver;
import ir.maktab.move.Locations;
import ir.maktab.move.Trip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TripDatabase extends DataBaseAccess {

    public TripDatabase() throws ClassNotFoundException, SQLException {
        super();
    }

    public double minSpaceToDriver(int latitude, int longitude) {
        if (getConnection() != null) {
            Statement statement = null;
            try {
                statement = getConnection().createStatement();
                String query = String.format("select min(sqrt(pow(latitude-'%s',2)+pow(longitude-'%s',2)*1.0)) " +
                        "from taxi_online.driver where driver.driver_status='WAITING';\n", latitude, longitude);
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet.getDouble(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return -1;
    }

    public One_Driver getNearDriver(int latitude, int longitude) {
        double nearDriver = minSpaceToDriver(latitude, longitude);
        String query = String.format("select * from driver where sqrt((pow(latitude-'%s',2)" +
                "+pow(longitude-'%s',2)*1.0))='%s'", latitude, longitude, nearDriver);
        try {
            Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            String first_name=resultSet.getString(2);
            //todo

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void addTrip(Trip trip){
        //todo
    }
}
