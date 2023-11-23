package software.ulpgc.moneycalculator;

public class ExchangeRate {
    private final Currency from;
    private final Currency to;

    public ExchangeRate(Currency from, Currency to) {
        this.from = from;
        this.to = to;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }
}
