package lt.swedbank.gyk;

public class PaymentUtility {

    public static void printTransactionHistory(Account account) {
        for (Payment payment : account.getTransactionHistory()) {
            System.out.println(payment.toString());
        }
    }

}
