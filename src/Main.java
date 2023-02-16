import java.util.Scanner;

public class Main {
    public static final int SIZE = 3; //размер поля
    public static final char EMPTY = '-'; //чем заполняются клетки пока пустые
    public static final char CROSS = 'X'; //чем заполняется ход первого игрока
    public static final char ZERO = '0'; // чем заполняется ход второго игрока
    public static void main(String[] args) {

        //создание поля
        char[][] field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for ( int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY;
            }
        }
        Scanner scanner = new Scanner(System.in);
        boolean isCrossTurn = true; // если true-крестики, если false-нолики ходят

        //общение с пользователем и сканирование
        while (true) {
            System.out.println("Ходят " + (isCrossTurn ? "крестики" : "нолики") + "!" + " Введите два числа через пробел"); //тернарным оператором определяем кто ходит в зависимости от boolean
            printField(field);
            String input = scanner.nextLine(); //так как 2 координаты то надо их поделить
            String[] parts = input.split(" "); //делим по пробелу и присваиваем им значения
            int x = Integer.parseInt(parts[0]) - 1;// -1 так как пользователи считают не с нуля
            int y = Integer.parseInt(parts[1]) - 1;

            if (field[x][y] != EMPTY) {
                continue; // если ячейка уже чем то заполнена завершает данную итерацию и начинает цикл заного прося ввод от того же самого игрока
            }
            field[x][y] = isCrossTurn ? CROSS : ZERO; //заполняет значением(X или 0) поле если оно было пустым

            if (isWin(field, isCrossTurn ? CROSS : ZERO)) { //передает поле и игрока нынешнего в метод на победу
                System.out.println("Победили " + (isCrossTurn ? "крестики" : "нолики") + "!");
                printField(field);
                break; //если кто то победил то завершает игру
            } else {
                isCrossTurn = !isCrossTurn; // меняет пользователя true->false/ false->true
            }
        }
    }

    //ВНИМАНИЕ РАБОТАЕТ ТОЛЬКО ДЛЯ ПОЛЯ 3x3!!!!
    public static boolean isWin(char[][] field, char player) { //метод по проверке выйграл ли игрок, передаем сюда наше поле и игрока
        if (field[0][0] == player & field[0][1] == player & field[0][2] == player)
            return true;
        if (field[1][0] == player & field[1][1] == player & field[1][2] == player) //проверка не выйграла ли строка
            return true;
        if (field[2][0] == player & field[2][1] == player & field[2][2] == player)
            return true;

        if (field[0][0] == player & field[1][0] == player & field[2][0] == player)
            return true;
        if (field[0][1] == player & field[1][1] == player & field[2][1] == player) //проверка не выграл ли столбик
            return true;
        if (field[0][2] == player & field[1][2] == player & field[2][2] == player)
            return true;

        if (field[0][0] == player & field[1][1] == player & field[2][2] == player) // проверка не выйграла ли диагональ
            return true;
        if (field[0][2] == player & field[1][1] == player & field[2][0] == player)
            return true;
        return false; //если ни один не подошел( нет победы)
    }


    public static void printField(char[][] field) { //метод вывода нашего поля на экран в одну команду
        for (char[] row : field) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}