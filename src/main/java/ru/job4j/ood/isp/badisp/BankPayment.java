package ru.job4j.ood.isp.badisp;

import java.util.ArrayList;
import java.util.List;

public class BankPayment implements Payment {

    @Override
    public void initiatePayments() {
        /* ... */
    }

    @Override
    public Object status() {
        return new Object();
    }

    @Override
    public List<Object> getPayments() {
        List<Object> list = new ArrayList<>();
        list.add(status());
        return list;
    }

    @Override
    public void initiateLoanSettlement() {
        throw new UnsupportedOperationException("This is not a loan payment");
    }

    @Override
    public void initiateRePayment() {
        throw new UnsupportedOperationException("This is not a loan payment");
    }
}
