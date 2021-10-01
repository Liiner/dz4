import java.util.Random;
import java.util.Scanner;

public class dz4 {
    //переменные поля
    public int counter;
    public static char[][] map;
    public static final int size = 5;
    public static final int game_win = 4;
    public static final char move_empty = '.';

    //переменные игроков
    public static final char move_x = 'X';
    public static final char move_o = 'O';

    public static void main(String[] args) {
        openMap(); //размечаем поле
        printMap(); //выводим поле
        while (true) {
            moveUser();
            printMap();
            if (checkWin(move_x)) {
                System.out.println("Победил игрок!");
                break;
            }
            if (mapFull()) {
                System.out.println("Ничья");
                break;
            }
            movePC();
            printMap();
            if (checkWin(move_o)) {
                System.out.println("Победил компьютер!");
                break;
            }
            if (mapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("GAME OVER");
    }

    //инициализируем карту
    public static void openMap() {
        map = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = move_empty;
            }
        }
    }

    //вывод поля
    public static void printMap() {

        for (int i = 0; i <= size; i++) { //разметка поля "горизонталь"
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {  //разметка поля "вертикаль"
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " "); //разметка пустых полей
            }
            System.out.println();
        }
    }

    //ход игрока
    public static Scanner cs = new Scanner(System.in);

    public static void moveUser() {
        int x, y;
        do {
            System.out.println("Введите координаты хода (X) и (Y)");
            x = cs.nextInt() - 1;
            y = cs.nextInt() - 1;
        } while (!isCellValid(x, y)); //ячейка свободна
        map[x][y] = move_x;           //ставим Х
    }

    //проверка заполнености карты
    public static boolean mapFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == move_empty) return false;
            }
        }
        return true;
    }

    //проверка статуса ячейки
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) return false;
        if (map[x][y] == move_empty) return true;
        return false;
    }

    //ход компьютера
    public static Random rand = new Random();

    public static void movePC() {
        int x, y;
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер сходил в " + (x + 1) + (y + 1));
        map[x][y] = move_o;
    }

    //проверка победы
    public static boolean checkWin(char symbol) {
        int counter = 0;
                      for (int i = 0; i < size; i++) {
           counter = 0;
            for (int j = 0; j < size; j++) {
                if (map[i][j] == symbol) counter++;
                if (counter == game_win) return true;
            }
        }

        for (int i = 0; i < size; i++) {
           counter = 0;
            for (int j = 0; j < size; j++) {
                if (map[j][i] == symbol) counter++;
                if (counter == game_win) return true;
            }
        }

        for (int i = 0; i < size; i++) {
            if (map[i][i] == symbol) counter++;
            if (counter == game_win) return true;
        }

        for (int i = size - 1; i > 0; i--) {
            counter = 0;
            for (int j = size - 1; j > 0; j--) {
                if (map[i][j] == symbol) counter++;
                if (counter == game_win) return true;
            }
        }
        return false;
    }
}













