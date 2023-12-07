package software.ulpgc.moneycalculator.api;

import software.ulpgc.moneycalculator.ExchangeRate;
import software.ulpgc.moneycalculator.Money;

public class ApiResult {
    public static Money getMoney(String json, ExchangeRate exchangeRate) {
        int initialS = json.indexOf(exchangeRate.getTo().getCode(), 364);
        int finalS = json.indexOf(",", initialS);
        String subcadena = json.substring(initialS,finalS);
        initialS = subcadena.indexOf(":")+1;
        subcadena = subcadena.substring(initialS);
        return new Money(exchangeRate.getTo(),Float.parseFloat(subcadena));
    }
}
