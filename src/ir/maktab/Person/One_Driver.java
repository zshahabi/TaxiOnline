package ir.maktab.Person;

import ir.maktab.enums.Gender;

import java.util.Date;


public class One_Driver extends Person {
    public One_Driver(int id ,String firstName, String lastName, String nationalCode, Gender gender, Date birthDate, String phoneNumber, double balance) {
        super(id,firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
    }
    public One_Driver(String firstName, String lastName, String nationalCode, Gender gender, Date birthDate, String phoneNumber, double balance) {
        super(firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
    }


    @Override
    public String toString() {
        return "Driver{" +
                super.toString()+
                "}";
    }
}
