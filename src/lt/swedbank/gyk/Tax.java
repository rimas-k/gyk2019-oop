package lt.swedbank.gyk;

import java.math.BigDecimal;

public class Tax implements Transaction {

    private Account taxedAccount;

    private BigDecimal taxAmount;
    private String date;

    @Override
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Account getTaxedAccount() {
        return taxedAccount;
    }

    public void setTaxedAccount(Account taxedAccount) {
        this.taxedAccount = taxedAccount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    @Override
    public BigDecimal getAmount() {
        return getTaxAmount();
    }

    @Override
    public String toString() {
        return "Tax{" +
                "taxedAccount=" + taxedAccount +
                ", taxAmount=" + taxAmount +
                ", date='" + date + '\'' +
                '}';
    }
}
