package lt.swedbank.gyk;

public class InternationalPayment extends Payment {
    private Currency currency;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "InternationalPayment{" +
                "currency=" + currency +
                "} " + super.toString();
    }
}
