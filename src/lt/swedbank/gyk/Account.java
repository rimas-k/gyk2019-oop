package lt.swedbank.gyk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String number;

    private List<Payment> transactionHistory;

    private BigDecimal balance;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Payment> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Payment> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void addToTransactionHistory(Payment payment) {
        if (transactionHistory == null) {
            transactionHistory = new ArrayList<>();
        }

        transactionHistory.add(payment);
    }

    public void addAmount(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void deductAmount(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }
}
