package lt.swedbank.gyk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class PaymentsApp {

    public static void main(String[] args) {
        Account sourceAccount = new Account();
        sourceAccount.setBalance(new BigDecimal("2000"));
        sourceAccount.setCurrency(Currency.EUR);
        sourceAccount.setNumber("1");

        Account destinationAccount = new Account();
        destinationAccount.setBalance(new BigDecimal("500"));
        destinationAccount.setCurrency(Currency.SEK);
        destinationAccount.setNumber("2");

        try {
//            processDomesticPayments(sourceAccount, destinationAccount);
            processInternationalPayments(sourceAccount, destinationAccount);
        } catch (NotEnoughFundsAvailableException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println(sourceAccount.toString());
        PaymentUtility.printTransactionHistory(sourceAccount);

        System.out.println("================");

        System.out.println(destinationAccount.toString());
        PaymentUtility.printTransactionHistory(destinationAccount);
    }

    private static void processDomesticPayments(Account sourceAccount, Account destinationAccount) throws NotEnoughFundsAvailableException {
        Collection<Payment> payments = createDomesticPayments(sourceAccount, destinationAccount);

        PaymentProcessor processor = new DomesticPaymentProcessor();

        for(Payment payment : payments) {
            processor.process(payment);
        }
    }

    private static void processInternationalPayments(Account sourceAccount, Account destinationAccount) throws NotEnoughFundsAvailableException {
        Collection<Payment> payments = createInternationalPayments(sourceAccount, destinationAccount);

        PaymentProcessor processor = new InternationalPaymentProcessor();

        for(Payment payment : payments) {
            processor.process(payment);
        }
    }

    private static Collection<Payment> createDomesticPayments(Account sourceAccount, Account destinationAccount) {
        Collection<Payment> payments = new ArrayList<Payment>();

        Payment firstPayment = new Payment();

        firstPayment.setSourceAccount(sourceAccount);
        firstPayment.setDestinationAccount(destinationAccount);
        firstPayment.setDate("2019-11-08");
        firstPayment.setAmount(new BigDecimal("100.00"));

        payments.add(firstPayment);

        Payment secondPayment = new Payment();

        secondPayment.setSourceAccount(sourceAccount);
        secondPayment.setDestinationAccount(destinationAccount);
        secondPayment.setDate("2019-11-09");
        secondPayment.setAmount(new BigDecimal("50.00"));

        payments.add(secondPayment);

        return payments;
    }

    private static Collection<Payment> createInternationalPayments(Account sourceAccount, Account destinationAccount) {
        Collection<Payment> payments = new ArrayList<Payment>();

        InternationalPayment firstPayment = new InternationalPayment();

        firstPayment.setSourceAccount(sourceAccount);
        firstPayment.setDestinationAccount(destinationAccount);
        firstPayment.setDate("2019-11-08");
        firstPayment.setAmount(new BigDecimal("100.00"));
        firstPayment.setCurrency(Currency.EUR);

        payments.add(firstPayment);

        InternationalPayment secondPayment = new InternationalPayment();

        secondPayment.setSourceAccount(sourceAccount);
        secondPayment.setDestinationAccount(destinationAccount);
        secondPayment.setDate("2019-11-09");
        secondPayment.setAmount(new BigDecimal("50.00"));
        secondPayment.setCurrency(Currency.EUR);

        payments.add(secondPayment);

        return payments;
    }

}
