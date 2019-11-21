package lt.swedbank.gyk;

public class TaxedInternationalPayment extends InternationalPayment implements Taxable {

    private Tax tax;

    @Override
    public Tax getTax() {
        return tax;
    }

    @Override
    public void setTax(Tax tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "TaxedInternationalPayment{" +
                "tax=" + tax +
                "} " + super.toString();
    }
}
