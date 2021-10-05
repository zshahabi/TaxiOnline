package ir.maktab.Person;

import ir.maktab.enums.Gender;
import ir.maktab.enums.Traver_Status;

import java.util.Date;

public class Pasenger extends Person{
    private Traver_Status travelStatus;
        public Pasenger(String firstName, String lastName, String nationalCode, Gender gender, Date birthDate, String phoneNumber, double balance,Traver_Status travelStatus) {
        super(firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
        this.travelStatus= travelStatus;
    }

    public Traver_Status getTravelStatus() {
        return travelStatus;
    }

    public void setTravelStatus(Traver_Status travelStatus) {
        this.travelStatus = travelStatus;
    }

    @Override
    public String toString() {
        return "Pasenger{" +
                super.toString()+
                "travelStatus=" + travelStatus +
                '}';
    }
}
