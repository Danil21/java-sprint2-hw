import java.util.Scanner;

public class Main {

    /*Считать все месячные отчёты — прочитать данные из файлов месячных отчётов, сохранить их в память программы.
     * При выборе действия «считать все месячные отчёты» должно происходить считывание трёх файлов:
     * Считать годовой отчёт — прочитать данные из файла годового отчёта, сохранить их в память программы.
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ReportManager reportManager = new ReportManager();

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            String commandExt = scanner.nextLine();

            if (command == 1) {
                int currency = scanner.nextInt();

            } else if (command == 2) {
              
            } else if (command == 3) {

            } else if (command == 4) {
              
            } else if (command == 5) {

            } else if (commandExt == "пока") {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("Введите 'пока' для завершения программы");
    }
 }


