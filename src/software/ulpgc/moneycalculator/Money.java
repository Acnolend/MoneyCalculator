package software.ulpgc.moneycalculator;

public class Money {
    private final Currency currency;
    private final float amount;

    public Currency getCurrency() {
        return currency;
    }

    public float getAmount() {
        return amount;
    }

    public Money(Currency currency, float amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Money{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}
