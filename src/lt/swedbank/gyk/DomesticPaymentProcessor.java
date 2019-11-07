package lt.swedbank.gyk;

import java.math.BigDecimal;

public class DomesticPaymentProcessor implements PaymentProcessor {

    private static final String NOT_ENOUGH_FUNDS_AVAILABLE = "Not enough funds available to register a payment!";

    @Override
    public final void process(Payment payment) throws NotEnoughFundsAvailableException {
        preProcessPayment(payment);

        if (hasAccountEnoughFunds(payment)) {
            transferFunds(payment);
            registerToTransactionHistories(payment);
        } else {
            throw new NotEnoughFundsAvailableException(NOT_ENOUGH_FUNDS_AVAILABLE);
        }
    }

    protected void preProcessPayment(Payment payment) {
        BigDecimal creditedAmount = calculateCreditedAmount(payment);
        payment.setCreditedAmount(creditedAmount);
    }

    protected BigDecimal calculateCreditedAmount(Payment payment) {
        return payment.getAmount();
    }

    protected void transferFunds(Payment payment) {
        Account sourceAccount = payment.getSourceAccount();
        Account destinationAccount = payment.getDestinationAccount();

        BigDecimal creditedAmount = payment.getCreditedAmount();
        BigDecimal amount = payment.getAmount();

        sourceAccount.deductAmount(amount);
        destinationAccount.addAmount(creditedAmount);
    }

    protected void registerToTransactionHistories(Payment payment) {
        Account sourceAccount = payment.getSourceAccount();
        Account destinationAccount = payment.getDestinationAccount();

        sourceAccount.addToTransactionHistory(payment);
        destinationAccount.addToTransactionHistory(payment);
    }

    protected boolean hasAccountEnoughFunds(Payment payment) {
        Account account = payment.getSourceAccount();
        BigDecimal amountToBeDeducted = payment.getAmount();

        BigDecimal accountBalance = account.getBalance();
        boolean hasEnoughFunds = accountBalance.compareTo(amountToBeDeducted) >= 0;

        return hasEnoughFunds;
    }

}