package ir.maktab.database;

import ir.maktab.Person.Pasenger;
import ir.maktab.move.Locations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationDatabase extends DataBaseAccess {

    public LocationDatabase() throws ClassNotFoundException, SQLException {
        super();
    }

    public List<Locations> showListLocation() throws SQLException {
        List<Locations> locationsList = new ArrayList<>();

        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT latitude_origin,longitude_origin," +
                    "latitude_destination,longitude_destination" +
                    " FROM taxi_online.passenger;");
            while (resultSet.next()) {
                int latitudeOrigin = resultSet.getInt(resultSet.findColumn("latitude_origin"));
                int longitudeOrigin = resultSet.getInt(resultSet.findColumn("longitude_origin"));
                int latitudeDestination = resultSet.getInt(resultSet.findColumn("latitude_destination"));
                int longitudeDestination = resultSet.getInt(resultSet.findColumn("latitude_destination"));
                locationsList.add(new Locations(latitudeOrigin, longitudeOrigin, latitudeDestination,
                        longitudeDestination));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return locationsList;
    }

    public boolean saveLocation(Locations locations) throws SQLException {

        try {
            if (getConnection() != null) {
                Statement statement = getConnection().createStatement();
                String sqlQuery = String.format("INSERT INTO trip(latitude_origin," +
                                "longitude_origin," +
                                "latitude_destination," +
                                "longitude_destination) VALUES('%s','%s','%s','%s')", locations.getLatitudeOrigin(), locations.getLongitudeOrigin(),
                        locations.getLatitudeDestination(), locations.getLongitudeDestination());
                int i = statement.executeUpdate(sqlQuery);
                return i == -1 ? false : true;
            }

        }  catch(Exception exception) {
            System.out.println("error..."+exception.toString());
            exception.printStackTrace();
        }
        return false;
    }
}


