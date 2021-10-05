package ir.maktab.database;

import ir.maktab.Person.One_Driver;
import ir.maktab.enums.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriverDataBase extends DataBaseAccess{
    public DriverDataBase() throws ClassNotFoundException, SQLException {
        super();
    }

    public boolean save(One_Driver driver) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = "INSERT INTO driver ( first_name,last_name  , national_code , driver_gender ,driver_birthdate  ,phone_number  ,driver_balance  ) " +
                            "VALUES ('%s','%s','%s','%s',%s,'%s','%s')";

            String date  = new SimpleDateFormat("yyyy-MM-dd").format(driver.getBirthDate());

            sqlQuery = String.format(sqlQuery,driver.getFirstName(),driver.getLastName(),driver.getNationalCode(),driver.getGender()+"",date,
                    driver.getPhoneNumber(),driver.getBalance()+"");


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

            while(resultSet.next()) {
                int id = resultSet.getInt(resultSet.findColumn("driver_id"));
                String firstName    = resultSet.getString(resultSet.findColumn("first_name"));
                String lastName     = resultSet.getString(resultSet.findColumn("last_name"));
                String nationalCode = resultSet.getInt(resultSet.findColumn("national_code"))+"";
                Gender gender       = resultSet.getString(resultSet.findColumn("driver_gender")).equals(Gender.male.toString()) ? Gender.male:Gender.female;
                String date         = resultSet.getString(resultSet.findColumn("driver_birthdate"));
                Date birthDate      = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                String phoneNumber  = resultSet.getInt(resultSet.findColumn("phone_number"))+"";
                double balance      = resultSet.getDouble(resultSet.findColumn("driver_balance"));

                drivers.add(new One_Driver(id,firstName,lastName,nationalCode,gender,birthDate,phoneNumber,balance));

            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }


        return drivers;
    }

    public boolean findDriver(String nationalCode ) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery= String.format("SELECT national_code from driver WHERE national_code='%s'",nationalCode);
            ResultSet resultSet=statement.executeQuery(sqlQuery);
            if(resultSet.getRow() != 0){
                return  true;
            }else {
                return false;
            }
        }else {
            return  false;
        }

    }
}
