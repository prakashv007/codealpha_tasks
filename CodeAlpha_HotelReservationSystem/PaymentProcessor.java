public class PaymentProcessor {

    public static final String CREDIT_CARD = "CREDIT_CARD";
    public static final String DEBIT_CARD  = "DEBIT_CARD";
    public static final String CASH        = "CASH";

    public static boolean processPayment(String guestName, double amount,
                                         String method, String cardNumber) {

        System.out.println("\nProcessing payment...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!method.equals(CASH)) {
            if (!isValidCard(cardNumber)) {
                System.out.println("Payment FAILED! Card number must be 16 digits.");
                return false;
            }
        }

        System.out.println("Payment Successful!");
        System.out.println("Guest  : " + guestName);
        System.out.println("Amount : $" + amount);

        if (method.equals(CASH)) {
            System.out.println("Method : Cash");
        } else if (method.equals(CREDIT_CARD)) {
            System.out.println("Method : Credit Card (**** **** **** " + cardNumber.substring(12) + ")");
        } else {
            System.out.println("Method : Debit Card  (**** **** **** " + cardNumber.substring(12) + ")");
        }

        return true;
    }

    private static boolean isValidCard(String cardNumber) {
        String stripped = cardNumber.replaceAll(" ", "");
        return stripped.matches("\\d{16}");
    }
}
