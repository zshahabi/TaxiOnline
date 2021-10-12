package ir.maktab.database;

import ir.maktab.Person.One_Driver;
import ir.maktab.enums.DriverStatus;
import ir.maktab.enums.Gender;
import ir.maktab.enums.PassengerStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriverDataBase extends DataBaseAccess {
    public DriverDataBase() throws ClassNotFoundException, SQLException {
        super();
    }

    public boolean save(One_Driver driver) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = "INSERT INTO driver ( first_name,last_name  , national_code , driver_gender ,driver_birthdate  ,phone_number  ,driver_balance  ) " +
                    "VALUES ('%s','%s','%s','%s','%s','%s','%s')";

            String date = new SimpleDateFormat("yyyy-MM-dd").format(driver.getBirthDate());

            sqlQuery = String.format(sqlQuery, driver.getFirstName(), driver.getLastName(), driver.getNationalCode(), driver.getGender() + "", date,
                    driver.getPhoneNumber(), driver.getBalance() + "");


            int i = statement.executeUpdate(sqlQuery);
            return i == -1 ? false : true;
        } else {
            return false;
        }
    }

    public List<One_Driver> showListDrivers() {
        List<One_Driver> drivers = new ArrayList<>();

        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM taxi_online.driver");

            while (resultSet.next()) {
                int id = resultSet.getInt(resultSet.findColumn("driver_id"));
                String firstName = resultSet.getString(resultSet.findColumn("first_name"));
                String lastName = resultSet.getString(resultSet.findColumn("last_name"));
                String nationalCode = resultSet.getString(resultSet.findColumn("national_code")) + "";
                Gender gender = resultSet.getString(resultSet.findColumn("driver_gender")).equals(Gender.male.toString()) ? Gender.male : Gender.female;
                String date = resultSet.getString(resultSet.findColumn("driver_birthdate"));
                Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                String phoneNumber = resultSet.getString(resultSet.findColumn("phone_number")) + "";
                double balance = resultSet.getDouble(resultSet.findColumn("driver_balance"));
                String driverStatus = resultSet.getString(resultSet.findColumn("driver_status"));
                int latitude = resultSet.getInt(resultSet.findColumn("latitude"));
                int longitude = resultSet.getInt(resultSet.findColumn("longitude"));


                DriverStatus finalTS = null;

                if (driverStatus.equals(DriverStatus.WAITING.toString()))
                    finalTS = DriverStatus.WAITING;

                else if (driverStatus.equals(DriverStatus.DOING.toString()))
                    finalTS = DriverStatus.DOING;
                drivers.add(new One_Driver(id, firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance, finalTS, latitude, longitude));
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }


        return drivers;
    }

    public boolean findDriver(String nationalCode) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = "SELECT national_code from taxi_online.driver WHERE national_code=" + nationalCode.trim();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public int getDriverId(String nationalCode)  {
        if (getConnection() != null) {
            Statement statement = null;
            try {
                statement = getConnection().createStatement();
                String sqlQuery = String.format("SELECT driver_id from driver WHERE national_code='%s'", nationalCode);
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                if (resultSet.next()) {
                    return resultSet.getInt("passenger_id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return -1;
    }
    public void changeDriverStatus(DriverStatus driverStatus,String nationalCode){
        if (getConnection() != null) {
            try {
                Statement statement = getConnection().createStatement();
                String query = "UPDATE  taxi_online.driver set driver_status='%s' where national_code='%s'";
                String d = String.format(query, driverStatus.toString(), nationalCode.toString());
                statement.executeUpdate(d);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



