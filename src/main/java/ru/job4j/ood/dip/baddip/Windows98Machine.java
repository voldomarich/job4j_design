package ru.job4j.ood.dip.baddip;

/* Есть класс компьютеров старого поколения Windows98Machine,
В полях прописаны клавиатура и монитор для данного компьютера.
Объявив StandardKeyboard и Monitor ключевым словом new,
мы тесно связали эти три класса вместе. Это не только затрудняет тестирование нашего компьютера windows98,
но и лишает нас возможности заменять наш класс StandardKeyboard другим, если возникнет необходимость.
Необходимо отделить нашу машину от стандартной клавиатуры,
добавив более общий интерфейс клавиатуры и используем его в нашем классе:
private final Keyboard keyboard;
Давайте также модифицируем наш класс StandardKeyboard для реализации интерфейса Keyboard,
чтобы он подходил для внедрения в класс Windows98Machine:
public class StandardKeyboard implements Keyboard { };
Мы можем следовать тому же принципу для класса Monitor
*/

public class Windows98Machine {

    private final StandardKeyboard keyboard;
    private final Monitor monitor;

    public Windows98Machine() {
        monitor = new Monitor();
        keyboard = new StandardKeyboard();
    }
}
