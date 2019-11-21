package lt.swedbank.gyk;

import java.math.BigDecimal;

public class PaymentUtility {

    public static final BigDecimal TAX_RATE = new BigDecimal("0.01");

    public static void printTransactionHistory(Account account) {
        for (Transaction payment : account.getTransactionHistory()) {
            System.out.println(payment.toString());
        }
    }

}
