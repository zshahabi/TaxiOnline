package ir.maktab.database;


import ir.maktab.Person.Pasenger;
import ir.maktab.enums.Gender;
import ir.maktab.enums.Traver_Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PassengerDataBase extends DataBaseAccess {
    public PassengerDataBase() throws ClassNotFoundException, SQLException {
        super();
    }

    public List<Pasenger> showListPassengers() {
        List<Pasenger> pasengers = new ArrayList<>();

        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM taxi_online.passenger;");

            while(resultSet.next()) {
                int id = resultSet.getInt(resultSet.findColumn("passenger_id"));
                String firstName    = resultSet.getString(resultSet.findColumn("first_name"));
                String lastName     = resultSet.getString(resultSet.findColumn("last_name"));
                String nationalCode = resultSet.getString(resultSet.findColumn("national_code"))+"";
                Gender gender       = resultSet.getString(resultSet.findColumn("gender")).equals(Gender.male.toString()) ? Gender.male:Gender.female;
                String date         = resultSet.getString(resultSet.findColumn("birth_date"));
                Date birthDate      = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                String phoneNumber  = resultSet.getString(resultSet.findColumn("phone_number"))+"";
                double balance      = resultSet.getDouble(resultSet.findColumn("balance"));
                String traverStatus = resultSet.getString(resultSet.findColumn("travel_status"));
                Traver_Status finalTS  = null;

                if (traverStatus.equals(Traver_Status.not_start.toString()))
                    finalTS = Traver_Status.not_start;
                else if (traverStatus.equals(Traver_Status.end.toString()))
                    finalTS = Traver_Status.end;
                else if (traverStatus.equals(Traver_Status.doing.toString()))
                    finalTS = Traver_Status.doing;


                pasengers.add(new Pasenger(id,firstName,lastName,nationalCode,gender,birthDate,phoneNumber,balance,finalTS));

            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return pasengers;
    }

    public boolean savePassenger(Pasenger passenger) throws SQLException {

        try {
            if (getConnection() != null) {
                Statement statement = getConnection().createStatement();

                String date  = new SimpleDateFormat("yyyy-MM-dd").format(passenger.getBirthDate());

                String sqlQuery = String.format("INSERT INTO passenger (first_name , last_name , national_code , gender , birth_date ,  balance ,phone_number, travel_status)"
                                + "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')", passenger.getFirstName(), passenger.getLastName(), passenger.getNationalCode(),
                        passenger.getGender().toString(), date, passenger.getBalance(), passenger.getPhoneNumber(), passenger.getTravelStatus());

                int i = statement.executeUpdate(sqlQuery);
                return i == -1 ? false : true;
            }
        } catch(Exception exception) {
            System.out.println("error..."+exception.toString());
            exception.printStackTrace();
        }

        return false;
    }

    public boolean findPassenger(String nationalCode ) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery= String.format("SELECT national_code from passenger WHERE national_code='%s'",nationalCode);
            ResultSet resultSet=statement.executeQuery(sqlQuery);
            if(resultSet.next()){
                return true;
            }else {
                return false;
            }
        }else {
            return  false;
        }

    }

}
