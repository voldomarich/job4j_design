package ru.job4j.ood.srp.currency;

import java.util.Currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
