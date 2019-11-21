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

}
