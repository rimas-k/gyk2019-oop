package lt.swedbank.gyk;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    private final Map<Currency, Map<Currency, BigDecimal>> currencyRateMap;

    public CurrencyConverter() {
        currencyRateMap = new HashMap<>();
        Map<Currency, BigDecimal> euroRates = new HashMap<>();
        Map<Currency, BigDecimal> sekRates = new HashMap<>();

        currencyRateMap.put(Currency.EUR, euroRates);

        euroRates.put(Currency.EUR, new BigDecimal("1.0"));
        euroRates.put(Currency.SEK, new BigDecimal("10.0"));

        currencyRateMap.put(Currency.SEK, sekRates);

        sekRates.put(Currency.SEK, new BigDecimal("1.0"));
        sekRates.put(Currency.EUR, new BigDecimal("0.1"));
    }

    public BigDecimal convertAmount(Currency sourceCurrency, Currency destinationCurrency, BigDecimal amount) {
        BigDecimal rate = getCurrencyRate(sourceCurrency, destinationCurrency);

        return amount.multiply(rate);
    }

    private BigDecimal getCurrencyRate(Currency sourceCurrency, Currency destinationCurrency) {
        return currencyRateMap.get(sourceCurrency).get(destinationCurrency);
    }

}
