package ir.maktab.move;

import ir.maktab.enums.PaymentStatus;
import ir.maktab.enums.Traver_Status;

public class Trip {
    private Locations locations;
    private PaymentStatus paymentStatus;
    private Traver_Status traver_status;
    private int passengerId;
    private  int driverId;

    public Trip(Locations locations, PaymentStatus paymentStatus, Traver_Status traver_status, int passengerId, int driverId) {
        this.locations = locations;
        this.paymentStatus = paymentStatus;
        this.traver_status = traver_status;
        this.passengerId = passengerId;
        this.driverId = driverId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public Locations getLocations() {
        return locations;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Traver_Status getTraver_status() {
        return traver_status;
    }

    public void setTraver_status(Traver_Status traver_status) {
        this.traver_status = traver_status;
    }
}
