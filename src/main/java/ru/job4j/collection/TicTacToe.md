### Классы в игре "Крестики-нолики"

Game — управляет основной логикой игры (процесс игры, проверка победителя, смена хода)
Board — отвечает за хранение состояния игрового поля и отображение его
Player — отвечает за представление игрока и его действия (например, выбор клетки)
MoveValidator — проверяет, допустим ли ход игрока
GameUI — отвечает за взаимодействие с пользователем (например, вывод игрового поля, запрос хода у игрока)

### Инициализация игры:

Создаются объекты Board, Player и Game.
Игра начинается, вызывается метод play() класса Game.

### Ход игрока:

Текущий игрок выбирает ход через метод make_move() класса Player.
Ход проверяется в классе MoveValidator.
Если ход допустим, он регистрируется на доске через метод make_move() класса Board.

### Проверка состояния:

Класс Board проверяет, есть ли победитель или заполнена ли вся доска.
Если есть победитель, вызывается метод display_winner() класса GameUI.
Если ничья, вызывается метод display_draw().

### Смена игрока:

После каждого успешного хода вызывается метод switch_player() класса Game.
