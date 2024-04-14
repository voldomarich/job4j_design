package ru.job4j.ood.lsp.badlsp;

public class Chair extends Furniture {

    /* Подтип может ослабить (но не усилить) предварительное условие для переопределяемого им метода.
    Когда подтип усиливает предварительное условие (например, c 0 < num <= 10 до 0 < num <= 8 в нашем примере),
    он применяет более строгие ограничения, чем супертип.
    Например, значения 9 и 10 для num допустимы для Furniture.doStuff, но недействительны для Chair.doStuff.
    Это нарушит код клиента, который не ожидает этого нового более жёсткого ограничения.
     */
    @Override
    /* precondition: 0 < num <= 8 */
    public void doStuff(int num) {
        if (num <= 0 || num > 8) {
            throw new IllegalArgumentException("Input out of range 1-8");
        }
        /* some logic here... */
    }
}
