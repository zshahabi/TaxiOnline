package ir.maktab.move;

public class Locations {
    private int latitudeOrigin;//x1
    private int longitudeOrigin;//y1
    private int latitudeDestination;//x2
    private int longitudeDestination;//y2

    public Locations(int latitudeOrigin, int longitudeOrigin, int latitudeDestination, int longitudeDestination) {
        this.latitudeOrigin = latitudeOrigin;
        this.longitudeOrigin = longitudeOrigin;
        this.latitudeDestination = latitudeDestination;
        this.longitudeDestination = longitudeDestination;
    }

    public int getLatitudeOrigin() {
        return latitudeOrigin;
    }

    public void setLatitudeOrigin(int latitudeOrigin) {
        this.latitudeOrigin = latitudeOrigin;
    }

    public int getLongitudeOrigin() {
        return longitudeOrigin;
    }

    public void setLongitudeOrigin(int longitudeOrigin) {
        this.longitudeOrigin = longitudeOrigin;
    }

    public int getLatitudeDestination() {
        return latitudeDestination;
    }

    public void setLatitudeDestination(int latitudeDestination) {
        this.latitudeDestination = latitudeDestination;
    }

    public int getLongitudeDestination() {
        return longitudeDestination;
    }

    public void setLongitudeDestination(int longitudeDestination) {
        this.longitudeDestination = longitudeDestination;
    }

    // Function to calculate distance
    public double distance() {
        // Calculating distance
        return Math.sqrt(Math.pow(latitudeDestination - latitudeOrigin, 2) +
                Math.pow(longitudeDestination - longitudeOrigin, 2) * 1.0);
    }

    public double priceOfTrip() {
        double amount = 1000 * distance();
        return amount;
    }
}
