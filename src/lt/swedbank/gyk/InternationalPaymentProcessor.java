package lt.swedbank.gyk;

import java.math.BigDecimal;

public class InternationalPaymentProcessor extends DomesticPaymentProcessor {

    @Override
    protected BigDecimal calculateCreditedAmount(Payment payment) {
        BigDecimal originalAmount = payment.getAmount();
        Currency sourceCurrency = payment.getSourceAccount().getCurrency();
        Currency destinationCurrency = payment.getDestinationAccount().getCurrency();

        CurrencyConverter converter = new CurrencyConverter();

        BigDecimal convertedAmount = converter.convertAmount(sourceCurrency, destinationCurrency, originalAmount);

        return convertedAmount;
    }

    @Override
    protected void preProcessPayment(Payment payment) {
        super.preProcessPayment(payment);

        if (payment instanceof Taxable) {
            Taxable taxablePayment = (Taxable) payment;
            Tax tax = new Tax();

            BigDecimal amount = payment.getAmount();
            BigDecimal taxAmount = amount.multiply(PaymentUtility.TAX_RATE);
            tax.setTaxAmount(taxAmount);

            String taxDate = payment.getDate();
            tax.setDate(taxDate);

            Account taxedAccount = payment.getSourceAccount();
            tax.setTaxedAccount(taxedAccount);

            taxablePayment.setTax(tax);
        }
    }

    @Override
    protected boolean hasAccountEnoughFunds(Payment payment) {
        Account account = payment.getSourceAccount();
        BigDecimal amountToBeDeducted = payment.getAmount();

        if (payment instanceof Taxable) {
            Taxable taxablePayment = (Taxable) payment;
            Tax tax = taxablePayment.getTax();
            BigDecimal taxAmount = tax.getTaxAmount();

            amountToBeDeducted = amountToBeDeducted.add(taxAmount);
        }

        BigDecimal accountBalance = account.getBalance();
        boolean hasEnoughFunds = accountBalance.compareTo(amountToBeDeducted) >= 0;

        return hasEnoughFunds;
    }

    @Override
    protected void registerToTransactionHistories(Payment payment) {
        Account sourceAccount = payment.getSourceAccount();
        Account destinationAccount = payment.getDestinationAccount();

        if (payment instanceof Taxable) {
            Taxable taxablePayment = (Taxable) payment;
            Tax tax = taxablePayment.getTax();

            sourceAccount.addToTransactionHistory(tax);
        }

        sourceAccount.addToTransactionHistory(payment);
        destinationAccount.addToTransactionHistory(payment);
    }

    @Override
    protected void transferFunds(Payment payment) {
        super.transferFunds(payment);

        if (payment instanceof Taxable) {
            Taxable taxedPayment = (Taxable) payment;
            Tax tax = taxedPayment.getTax();
            BigDecimal taxAmount = tax.getTaxAmount();

            Account sourceAccount = payment.getSourceAccount();
            sourceAccount.deductAmount(taxAmount);
        }
    }

}
