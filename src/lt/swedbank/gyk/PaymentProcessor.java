package lt.swedbank.gyk;

public interface PaymentProcessor {
    void process(Payment payment) throws NotEnoughFundsAvailableException;
}
