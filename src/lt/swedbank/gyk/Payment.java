package lt.swedbank.gyk;

import java.math.BigDecimal;

public class Payment {
    private Account sourceAccount;

    private Account destinationAccount;

    private BigDecimal amount;

    private String date;

    private BigDecimal creditedAmount;

    public BigDecimal getCreditedAmount() {
        return creditedAmount;
    }

    public void setCreditedAmount(BigDecimal creditedAmount) {
        this.creditedAmount = creditedAmount;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "sourceAccount=" + sourceAccount +
                ", destinationAccount=" + destinationAccount +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", creditedAmount=" + creditedAmount +
                '}';
    }
}
