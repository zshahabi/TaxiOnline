package ir.maktab.Person;

import ir.maktab.enums.Gender;
import ir.maktab.enums.PassengerStatus;
import ir.maktab.enums.Traver_Status;

import java.util.Date;

public class Pasenger extends Person{
    private PassengerStatus passengerStatus;

    public Pasenger(String firstName, String lastName, String nationalCode,
                    Gender gender, Date birthDate, String phoneNumber, double balance,PassengerStatus passengerStatus) {
        super(firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
        this.passengerStatus = passengerStatus;
    }

    public Pasenger(int id,String firstName, String lastName, String nationalCode,
                    Gender gender, Date birthDate, String phoneNumber, double balance, PassengerStatus passengerStatus) {
        super(id,firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
        this.passengerStatus = passengerStatus;
    }

    public PassengerStatus getPassengerStatus() {
        return passengerStatus;
    }

    public void setPassengerStatus(PassengerStatus passengerStatus) {
        this.passengerStatus =passengerStatus;
    }

    @Override
    public String toString() {
        return "Pasenger{" +
                super.toString()+
                "passengerStatus=" + passengerStatus +
                '}';
    }
}
