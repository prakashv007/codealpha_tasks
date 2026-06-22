import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {

    private static int idCounter = 100;

    private int bookingId;
    private String guestName;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalAmount;

    public Booking(String guestName, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingId = ++idCounter;
        this.guestName = guestName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = calculateTotal();
    }

    private double calculateTotal() {
        long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return nights * room.getPricePerNight();
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return "-------------------------------\n"
             + "Booking ID   : " + bookingId + "\n"
             + "Guest Name   : " + guestName + "\n"
             + "Room Number  : " + room.getRoomNumber() + "\n"
             + "Category     : " + room.getCategory() + "\n"
             + "Check-In     : " + checkInDate + "\n"
             + "Check-Out    : " + checkOutDate + "\n"
             + "Nights       : " + nights + "\n"
             + "Total Cost   : $" + totalAmount + "\n"
             + "-------------------------------";
    }
}
