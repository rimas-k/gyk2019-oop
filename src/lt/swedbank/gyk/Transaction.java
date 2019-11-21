package lt.swedbank.gyk;

import java.math.BigDecimal;

public interface Transaction {

    BigDecimal getAmount();

    String getDate();

}
