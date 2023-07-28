package ru.job4j.ood.srp.currency;

public class InMemoryCurrencyConverter implements CurrencyConverter {

    private final int CURRENCIES_COUNT = Currency.values().length;
    private final double[][] conversationTable = new double[CURRENCIES_COUNT][CURRENCIES_COUNT];

    public InMemoryCurrencyConverter() {
        this.conversationTable[Currency.RUB.ordinal()][Currency.USD.ordinal()] = 0.011;
        this.conversationTable[Currency.RUB.ordinal()][Currency.EUR.ordinal()] = 0.01;
        this.conversationTable[Currency.USD.ordinal()][Currency.EUR.ordinal()] = 1.11;
        this.conversationTable[Currency.USD.ordinal()][Currency.RUB.ordinal()] = 90D;
        this.conversationTable[Currency.EUR.ordinal()][Currency.USD.ordinal()] = 0.9;
        this.conversationTable[Currency.EUR.ordinal()][Currency.RUB.ordinal()] = 100D;
    }

    public double convert(java.util.Currency source, double sourceValue, java.util.Currency target) {
        return sourceValue * conversationTable[source.getNumericCode()][target.getNumericCode()];
    }
}
