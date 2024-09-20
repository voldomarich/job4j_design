package ru.job4j.ood.lsp.badlsp;

import java.math.BigDecimal;

/* Не поддерживая метод withdraw, класс DepositAccount нарушает спецификацию этого метода.
Таким образом, мы не можем вместо DepositAccount подставить его родительский класс Account так,
чтобы все работало так как нужно.
Другими словами, DepositAccount нарушает принцип подстановки Лисков
*/

public class DepositAccount extends Account {

    @Override
    protected void deposit(BigDecimal amount) {
        /* Deposit into this account */
    }

    @Override
    protected void withdraw(BigDecimal amount) {
        throw new UnsupportedOperationException("Withdrawals are not supported by DepositAccount");
    }
}
