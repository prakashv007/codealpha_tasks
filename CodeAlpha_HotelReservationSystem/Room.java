public class Room {

    public static final String STANDARD = "STANDARD";
    public static final String DELUXE   = "DELUXE";
    public static final String SUITE    = "SUITE";

    private int roomNumber;
    private String category;
    private double pricePerNight;
    private boolean isAvailable;

    public Room(int roomNumber, String category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Booked";
        return "Room " + roomNumber + " | " + category + " | $" + pricePerNight + "/night | " + status;
    }
}
