import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {

    static Hotel hotel = new Hotel("Hotel");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   Welcome to " + hotel.getHotelName());
        System.out.println("   Hotel Reservation System");
        System.out.println("========================================");

        int choice = 0;

        while (choice != 6) {
            printMenu();
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.\n");
                continue;
            }

            if (choice == 1) {
                viewAllRooms();
            } else if (choice == 2) {
                searchRooms();
            } else if (choice == 3) {
                bookRoom();
            } else if (choice == 4) {
                cancelBooking();
            } else if (choice == 5) {
                viewAllBookings();
            } else if (choice == 6) {
                System.out.println("\nThank you for using " + hotel.getHotelName() + ". Goodbye!");
            } else {
                System.out.println("Invalid choice! Please enter 1 to 6.\n");
            }
        }
    }

    static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. View All Rooms");
        System.out.println("2. Search Available Rooms");
        System.out.println("3. Book a Room");
        System.out.println("4. Cancel Reservation");
        System.out.println("5. View All Bookings");
        System.out.println("6. Exit");
        System.out.println("-----------------");
    }

    static void viewAllRooms() {
        System.out.println("\n--- All Rooms ---");
        ArrayList<Room> allRooms = hotel.getAllRooms();

        if (allRooms.isEmpty()) {
            System.out.println("No rooms found.");
            return;
        }

        for (Room r : allRooms) {
            System.out.println(r);
        }
    }
    static void searchRooms() {
        System.out.println("\n--- Search Available Rooms ---");
        String cat = getCategory();

        ArrayList<Room> available = hotel.searchAvailableRooms(cat);

        if (available.isEmpty()) {
            System.out.println("No available " + cat + " rooms right now.");
        } else {
            System.out.println("Available " + cat + " rooms:");
            for (Room r : available) {
                System.out.println("  " + r);
            }
        }
    }

    static void bookRoom() {
        System.out.println("\n--- Book a Room ---");

        String cat = getCategory();
        ArrayList<Room> available = hotel.searchAvailableRooms(cat);

        if (available.isEmpty()) {
            System.out.println("No " + cat + " rooms available. Try another category.");
            return;
        }

        System.out.println("Available " + cat + " rooms:");
        for (Room r : available) {
            System.out.println("  " + r);
        }

        System.out.print("Enter room number: ");
        int roomNo;
        try {
            roomNo = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid room number.");
            return;
        }

        Room selectedRoom = hotel.findRoom(roomNo);

        if (selectedRoom == null || !selectedRoom.isAvailable() || !selectedRoom.getCategory().equals(cat)) {
            System.out.println("Invalid or unavailable room.");
            return;
        }

        System.out.print("Enter guest name: ");
        String guestName = sc.nextLine().trim();

        if (guestName.isEmpty()) {
            System.out.println("Guest name cannot be empty.");
            return;
        }

        LocalDate checkIn = getDate("Enter check-in date (yyyy-MM-dd): ");
        if (checkIn == null) return;

        LocalDate checkOut = getDate("Enter check-out date (yyyy-MM-dd): ");
        if (checkOut == null) return;

        if (!checkOut.isAfter(checkIn)) {
            System.out.println("Check-out date must be after check-in date.");
            return;
        }

        Booking booking = new Booking(guestName, selectedRoom, checkIn, checkOut);
        System.out.println("Total cost will be: $" + booking.getTotalAmount());

        System.out.println("\n--- Payment ---");
        System.out.println("Select payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. Cash");
        System.out.print("Enter choice: ");

        int payChoice;
        try {
            payChoice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        String method;
        String cardNumber = "";

        if (payChoice == 1) {
            method = PaymentProcessor.CREDIT_CARD;
            System.out.print("Enter 16-digit card number: ");
            cardNumber = sc.nextLine().trim();
        } else if (payChoice == 2) {
            method = PaymentProcessor.DEBIT_CARD;
            System.out.print("Enter 16-digit card number: ");
            cardNumber = sc.nextLine().trim();
        } else if (payChoice == 3) {
            method = PaymentProcessor.CASH;
        } else {
            System.out.println("Invalid payment choice.");
            return;
        }

        boolean paid = PaymentProcessor.processPayment(guestName, booking.getTotalAmount(), method, cardNumber);

        if (!paid) {
            System.out.println("Booking cancelled due to payment failure.");
            return;
        }

        hotel.addBooking(booking);
        System.out.println("\nBooking Confirmed!");
        System.out.println(booking);
    }

    static void cancelBooking() {
        System.out.println("\n--- Cancel Reservation ---");

        ArrayList<Booking> bookings = hotel.getAllBookings();

        if (bookings.isEmpty()) {
            System.out.println("No active bookings to cancel.");
            return;
        }

        System.out.println("Current Bookings:");
        for (Booking b : bookings) {
            System.out.println("  ID: " + b.getBookingId() + " | " + b.getGuestName()
                    + " | Room " + b.getRoom().getRoomNumber()
                    + " | " + b.getCheckInDate() + " to " + b.getCheckOutDate());
        }

        System.out.print("\nEnter Booking ID to cancel: ");
        int bookingId;
        try {
            bookingId = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid booking ID.");
            return;
        }

        boolean cancelled = hotel.cancelBooking(bookingId);
        if (cancelled) {
            System.out.println("Booking cancelled successfully! Room is now available.");
        }
    }

    static void viewAllBookings() {
        System.out.println("\n--- All Bookings ---");

        ArrayList<Booking> bookings = hotel.getAllBookings();

        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }

        System.out.println("Total bookings: " + bookings.size());
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    static String getCategory() {
        System.out.println("Select category:");
        System.out.println("1. Standard ($80/night)");
        System.out.println("2. Deluxe   ($150/night)");
        System.out.println("3. Suite    ($300/night)");

        while (true) {
            System.out.print("Enter choice (1-3): ");
            try {
                int c = Integer.parseInt(sc.nextLine().trim());
                if (c == 1) return Room.STANDARD;
                else if (c == 2) return Room.DELUXE;
                else if (c == 3) return Room.SUITE;
                else System.out.println("Enter 1, 2 or 3 only.");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    static LocalDate getDate(String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        try {
            return LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use yyyy-MM-dd.");
            return null;
        }
    }
}
