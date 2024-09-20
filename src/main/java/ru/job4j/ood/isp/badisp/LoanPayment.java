package ru.job4j.ood.isp.badisp;

import java.util.ArrayList;
import java.util.List;

public class LoanPayment implements Payment {

    @Override
    public void initiatePayments() {
        throw new UnsupportedOperationException("This is not a bank payment");
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
        /* ... */
    }

    @Override
    public void initiateRePayment() {
        /* ... */
    }
}
