package ir.maktab.Person;

import ir.maktab.enums.DriverStatus;
import ir.maktab.enums.Gender;

import java.util.Date;


public class One_Driver extends Person {
    private DriverStatus driverStatus;
    private int latitude;
    private int longitude;

    public One_Driver(int id ,String firstName, String lastName, String nationalCode,
                      Gender gender, Date birthDate, String phoneNumber, double balance,
                      DriverStatus driverStatus,int latitude,int longitude) {
        super(id,firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
        this.driverStatus=driverStatus;
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public One_Driver(String firstName, String lastName, String nationalCode,
                      Gender gender, Date birthDate, String phoneNumber,
                      double balance,DriverStatus driverStatus,int latitude,int longitude) {
        super(firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
        this.driverStatus=driverStatus;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "One_Driver{" +
                "driverStatus=" + driverStatus +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                super.toString()+
                '}';
    }
}
