package ru.job4j.ood.lsp.badlsp;

/* ToyCar всегда возвращает фиксированное значение для свойства restFuel.
Хардкод изменяемых значений состояния объекта является признаком того,
что подкласс не выполняет контракт своего супертипа и на самом деле не заменим на его.
*/

public class ToyCar extends Car {

    @Override
    protected int getRemainingFuel() {
        return 0;
    }
}
