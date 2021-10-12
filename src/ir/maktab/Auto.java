package ir.maktab;

import ir.maktab.Person.One_Driver;
import ir.maktab.Person.Pasenger;
import ir.maktab.database.DriverDataBase;
import ir.maktab.database.PassengerDataBase;
import ir.maktab.database.TripDatabase;
import ir.maktab.enums.*;
import ir.maktab.move.Locations;
import ir.maktab.move.Trip;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Auto {
    //  DriverDataBase driverDataBase = new DriverDataBase();
    PassengerDataBase passengerDataBase;
    DriverDataBase driverDataBase;
    TripDatabase tripDatabase;


    //showing main menu after app run : called in MainClass (just this method call in main and there is not any method in main )
    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("---------- welcome to taxi online ----------");
            System.out.println("1.Add a group of drivers" +
                    "\n2.Add a group of passengers" +
                    "\n3.Driver signup or login" +
                    "\n4.Passenger signup or login" +
                    "\n5.show ongoing travels" +
                    "\n6.show a list of drivers" +
                    "\n7.show a list of passengers" +
                    "\n8.exit\n ------------------------------");
            exit = selectMenuItem();
        }
    }


    public Auto() throws SQLException, ClassNotFoundException {
        passengerDataBase = new PassengerDataBase();
        driverDataBase = new DriverDataBase();
        tripDatabase = new TripDatabase();
    }

    private boolean selectMenuItem() {
        Scanner scanner = new Scanner(System.in);
        String selectItem = scanner.next();
        try {
            switch (Integer.parseInt(selectItem)) {
                case 1:
                    if (addDrivers()) {
                        System.out.println("Add Driver was Successfully");
                    } else {
                        System.out.println("Add Driver was failed");
                    }
                    return false;
                case 2:
                    if (addPassengers()) {
                        System.out.println("Add Passenger was Successfully");
                    } else {
                        System.out.println("Add Passenger was failed");
                    }
                    return false;
                case 3:
                    driverSignUpOrLogIn();
                    return false;
                case 4:
                    passengerSignUpOrLogIn();
                    return false;
                case 5:
                    showOnGoingTravels();
                    return false;
                case 6:
                    showListOfDrivers();
                    return false;
                case 7:
                    showListOfPassengers();
                    return false;
                case 8:
                    return true;
                default:
                    System.out.println("enter number between 1 and 8 please !");
                    return false;
            }

        } catch (ClassCastException | ParseException e) {
            System.out.println("enter number please !");
            e.getStackTrace();
            return false;
        }
    }


    private void showListOfPassengers() {
        List<Pasenger> pasengers = passengerDataBase.showListPassengers();

        for (Pasenger pasenger : pasengers) {
            System.out.println(pasenger.toString());
        }
    }

    private void showListOfDrivers() {
        List<One_Driver> drivers = driverDataBase.showListDrivers();

        for (One_Driver driver : drivers) {
            System.out.println(driver.toString());
        }

    }

    private void showOnGoingTravels() {
        //todo
    }

    private void passengerSignUpOrLogIn() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("enter your user name (national code)");
            String username = input.next();
            if (passengerDataBase.findPassenger(username)) {
                System.out.println("you are logged in");
                if (passengerDataBase.getTravelStatusForPassenger(username).equals(PassengerStatus.NOTATTEND)) {
                    Pasenger passengerInfo = passengerDataBase.getPassengerInfo(username);
                    System.out.println(passengerInfo.toString());
                    String message = "enter 1.Travel request (pay by cash)\n" +
                            "2.Travel request (pay by account balance)\n" +
                            "3.Increase account balance\n" +
                            "4.Exit";
                    System.out.println(message);
                    String choiceRequest = input.next();
                    System.out.println(choiceRequest);
                    Locations locations = null;
                    switch (Integer.parseInt(choiceRequest.trim())) {
                        case 1:
                            System.out.println("Enter the origin and destination of your travel:");
                            System.out.println("enter your location:latitudeOrigin(x1):");
                            int x1 = input.nextInt();
                            System.out.println("enter your location:longitudeOrigin(y1):");
                            int y1 = input.nextInt();
                            System.out.println("enter your location:latitudeDestination(x2):");
                            int x2 = input.nextInt();
                            System.out.println("enter your location:longitudeDestination(y2):");
                            int y2 = input.nextInt();
                            locations = new Locations(x1, y1, x2, y2);
                            One_Driver driver = tripDatabase.getNearDriver(locations.getLatitudeOrigin(),
                                    locations.getLongitudeOrigin());
                            System.out.println("driver Info:" + driver.toString());
                            Trip trip = new Trip(locations, PaymentStatus.CASH, Traver_Status.DOING, passengerDataBase.getPassengerId(username),
                                    driverDataBase.getDriverId(driver.getNationalCode()));
                            tripDatabase.addTrip(trip);
                            passengerDataBase.changePassengerStatus(PassengerStatus.ATTEND, username);
                            driverDataBase.changeDriverStatus(DriverStatus.DOING,driver.getNationalCode());

                        case 2:
                            System.out.println("Enter the origin and destination of your travel:");
                            System.out.println("enter your location:latitudeOrigin(x1):");
                            int x11 = input.nextInt();
                            System.out.println("enter your location:longitudeOrigin(y1):");
                            int y11 = input.nextInt();
                            System.out.println("enter your location:latitudeDestination(x2):");
                            int x22 = input.nextInt();
                            System.out.println("enter your location:longitudeDestination(y2):");
                            int y22 = input.nextInt();
                            locations = new Locations(x11, y11, x22, y22);
                            //todo
                            if (passengerInfo.getBalance() < locations.priceOfTrip()) {

                            }
                    }
                }

            } else {
                registerPassenger(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void driverSignUpOrLogIn() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("enter your user name (national code)");
            String username = scanner.next();
            if (driverDataBase.findDriver(username)) {
                System.out.println("you are logged in");
            } else {
                int item = getNumber("1.register\n2)exit", "[12]*");
                if (item == 1)
                    registerDriver(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean addPassengers() throws ParseException {
        Scanner scanner = new Scanner(System.in);

        int numberOfPassengers = getNumber("enter number of passenger to add : ", "[0-9]*");
        for (int i = 0; i < numberOfPassengers; i++) {
            System.out.println("Enter passenger information passenger " + (i + 1) + ": (username, name, family, national code, ...)");

            System.out.println("enter your first name ");
            String firstName = scanner.next();

            System.out.println("enter your last name ");
            String lastName = scanner.next();

            System.out.println("enter your nationalCode");
            String nationalCode = scanner.next();

            Gender gender = getGender();

            System.out.println("enter your birthDate");
            Date date = getBirthDate();

            String phoneNumber = getPhoneNumber();

            double balance = getNumber_double("enter your balance", "[0-9.]*");

            try {
                if (!passengerDataBase.findPassenger(nationalCode))
                    passengerDataBase.savePassenger(new Pasenger(firstName, lastName, nationalCode, gender, date, phoneNumber, balance, PassengerStatus.NOTATTEND));
                else
                    System.out.println("there is driver with this national code ");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.toString());
                return false;
            }

        }

        return true;

    }

    private boolean registerPassenger(String nationalCode) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Your firstName:");
        String firstName = input.next();
        System.out.println("Your lastName:");
        String lastName = input.next();
        System.out.println("gender");
        Gender gender = getGender();
        System.out.println("birthDate");
        System.out.println("year");
        int year = input.nextInt();
        System.out.println("month");
        int month = input.nextInt();
        System.out.println("day");
        int day = input.nextInt();
        Date birthDate = new Date(year, month, day);
        System.out.println("phoneNumber");
        String phoneNumber = input.next();
        System.out.println("balance");
        double balance = input.nextDouble();
        Pasenger passenger = new Pasenger(firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance, PassengerStatus.NOTATTEND);

        return passengerDataBase.savePassenger(passenger);
    }

    private boolean registerDriver(String nationalCode) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Your firstName:");
        String firstName = input.next();
        System.out.println("Your lastName:");
        String lastName = input.next();
        System.out.println("gender");
        Gender gender = getGender();
        System.out.println("birthDate");
        System.out.println("year");
        int year = input.nextInt();
        System.out.println("month");
        int month = input.nextInt();
        System.out.println("day");
        int day = input.nextInt();
        Date birthDate = new Date(year, month, day);
        System.out.println("phoneNumber");
        String phoneNumber = input.next();
        System.out.println("balance");
        double balance = input.nextDouble();
        System.out.println("latitude:");
        int latitude = input.nextInt();
        System.out.println("longitude:");
        int longitude = input.nextInt();
        One_Driver driver = new One_Driver(firstName, lastName, nationalCode, gender,
                birthDate, phoneNumber, balance, DriverStatus.WAITING, latitude, longitude);

        return driverDataBase.save(driver);
    }

    private boolean addDrivers() throws ParseException {
        Scanner scanner = new Scanner(System.in);

        int numberOfDrivers = getNumber("Enter number of drivers: ", "[0-9]*");

        for (int i = 0; i < numberOfDrivers; i++) {
            System.out.println("Enter drivers information driver " + (i + 1) + ": (username, name, family, national code, ...)");

            System.out.println("enter your first name ");
            String firstName = scanner.next();

            System.out.println("enter your last name ");
            String lastName = scanner.next();

            System.out.println("enter your nationalCode");
            String nationalCode = scanner.next();

            Gender gender = getGender();

            System.out.println("enter your birthDate");
            Date date = getBirthDate();

            String phoneNumber = getPhoneNumber();

            double balance = getNumber_double("enter your balance", "[0-9.]*");
            System.out.println("latitude:");
            int latitude = scanner.nextInt();
            System.out.println("longitude:");
            int longitude = scanner.nextInt();

            try {
                if (!driverDataBase.findDriver(nationalCode))
                    driverDataBase.save(new One_Driver(firstName, lastName, nationalCode, gender, date, phoneNumber,
                            balance, DriverStatus.WAITING, latitude, longitude));
                else
                    System.out.println("there is driver with this national code ");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.toString());
                return false;
            }

        }

        return true;
    }


    private int getNumber(String message, String regex) {
        Scanner scanner = new Scanner(System.in);
        boolean isNumberOk;
        String number;

        do {
            //show message text for get number of user
            System.out.println(message);
            //getting number of user
            number = scanner.next();
            //check is number ok or not
            isNumberOk = number.matches(regex);

            if (!isNumberOk)
                System.out.println("your number is invalid ");

        } while (!isNumberOk);

        return Integer.parseInt(number.trim());
    }

    private double getNumber_double(String message, String regex) {
        Scanner scanner = new Scanner(System.in);
        boolean isNumberOk;
        String number;

        do {
            //show message text for get number of user
            System.out.println(message);
            //getting number of user
            number = scanner.next();
            //check is number ok or not
            isNumberOk = number.matches(regex);

            if (!isNumberOk)
                System.out.println("your number is invalid ");

        } while (!isNumberOk);

        return Double.parseDouble(number);
    }

    private Gender getGender() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter your gender : (male , female )");
        String gender = scanner.next();

        if (gender.trim().toLowerCase(Locale.ROOT).equals(Gender.male.toString()))
            return Gender.male;
        else if (gender.trim().toLowerCase(Locale.ROOT).equals(Gender.female.toString()))
            return Gender.female;
        else
            return getGender();
    }

    private Date getBirthDate() throws ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter your birth year");
        int birthYear = scanner.nextInt();

        System.out.println("enter your birth month");
        int birthMonth = scanner.nextInt();

        System.out.println("enter your birth day");
        int birthDay = scanner.nextInt();

        return new SimpleDateFormat("yyyy-MM-dd").parse(birthYear + "-" + birthMonth + "-" + birthDay);
    }

    private String getPhoneNumber() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter your phone number (09121234567) ");
        String phoneNumber = scanner.next();

        if (!phoneNumber.matches("[0-9]*")) {
            System.out.println("your number is inValid");
            return getPhoneNumber();
        }
        return phoneNumber;
    }


}

