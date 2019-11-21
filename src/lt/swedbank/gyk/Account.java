package lt.swedbank.gyk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String number;

    private List<Transaction> transactionHistory;

    private BigDecimal balance;

    private Currency currency;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void addToTransactionHistory(Transaction transaction) {
        if (transactionHistory == null) {
            transactionHistory = new ArrayList<>();
        }

        transactionHistory.add(transaction);
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
                ", currency=" + currency +
                '}';
    }
}
