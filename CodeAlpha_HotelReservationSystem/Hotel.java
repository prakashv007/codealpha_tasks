import java.util.ArrayList;

public class Hotel {

    private String hotelName;
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        addRooms(); 
    }

    private void addRooms() {
        for (int i = 1; i <= 5; i++) {
            rooms.add(new Room(100 + i, Room.STANDARD, 80.00));
        }

        for (int i = 1; i <= 4; i++) {
            rooms.add(new Room(200 + i, Room.DELUXE, 150.00));
        }

        for (int i = 1; i <= 3; i++) {
            rooms.add(new Room(300 + i, Room.SUITE, 300.00));
        }
    }

    public ArrayList<Room> getAllRooms() {
        return rooms;
    }

    public ArrayList<Room> searchAvailableRooms(String category) {
        ArrayList<Room> result = new ArrayList<>();
        for (Room r : rooms) {
            if (r.getCategory().equals(category) && r.isAvailable()) {
                result.add(r);
            }
        }
        return result;
    }

    public Room findRoom(int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                return r;
            }
        }
        return null;
    }

    public boolean addBooking(Booking booking) {
        Room room = booking.getRoom();
        if (!room.isAvailable()) {
            System.out.println("Sorry, room " + room.getRoomNumber() + " is not available.");
            return false;
        }
        room.setAvailable(false); 
        bookings.add(booking);
        return true;
    }

    public boolean cancelBooking(int bookingId) {
        Booking toRemove = null;

        for (Booking b : bookings) {
            if (b.getBookingId() == bookingId) {
                toRemove = b;
                break;
            }
        }

        if (toRemove == null) {
            System.out.println("Booking ID " + bookingId + " not found.");
            return false;
        }

        toRemove.getRoom().setAvailable(true); 
        bookings.remove(toRemove);
        return true;
    }

    public ArrayList<Booking> getAllBookings() {
        return bookings;
    }
    public Booking findBooking(int bookingId) {
        for (Booking b : bookings) {
            if (b.getBookingId() == bookingId) {
                return b;
            }
        }
        return null;
    }

    public String getHotelName() {
        return hotelName;
    }
}
