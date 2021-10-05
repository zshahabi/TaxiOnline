package ir.maktab.database;


import ir.maktab.Person.Pasenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

public class PassengerDataBase extends DataBaseAccess {
    public PassengerDataBase() throws ClassNotFoundException, SQLException {
        super();
    }

    public List<Pasenger> showListPassengers() {
        //todo
        return null;
    }

    public boolean savePassenger(Pasenger passenger) throws SQLException {

        try {
            if (getConnection() != null) {
                Statement statement = getConnection().createStatement();

                String date  = new SimpleDateFormat("yyyy-MM-dd").format(passenger.getBirthDate());

                String sqlQuery = String.format("INSERT INTO passenger (first_name , last_name , national_code , gender , birth_date ,  balance ,phone_number, travel_status)"
                                + "VALUES ('%s','%s','%s',%s,'%s',%s,%s,'%s')", passenger.getFirstName(), passenger.getLastName(), passenger.getNationalCode(),
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
            if(resultSet != null){
                return true;
            }else {
                return false;
            }
        }else {
            return  false;
        }

    }

}
