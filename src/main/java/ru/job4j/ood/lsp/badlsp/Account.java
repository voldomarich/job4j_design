package ru.job4j.ood.lsp.badlsp;

import java.math.BigDecimal;

public abstract class Account {

    protected abstract void deposit(BigDecimal amount);
    protected abstract void withdraw(BigDecimal amount);
}
