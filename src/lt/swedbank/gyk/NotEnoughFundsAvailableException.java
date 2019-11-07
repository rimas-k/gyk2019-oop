package lt.swedbank.gyk;

public class NotEnoughFundsAvailableException extends Exception {

    public NotEnoughFundsAvailableException(String message) {
        super(message);
    }

}
