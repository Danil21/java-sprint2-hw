import java.util.Scanner;

public class Main {

    /*Считать все месячные отчёты — прочитать данные из файлов месячных отчётов, сохранить их в память программы.
     * При выборе действия «считать все месячные отчёты» должно происходить считывание трёх файлов:
     * Считать годовой отчёт — прочитать данные из файла годового отчёта, сохранить их в память программы.
     */

    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(System.in)) {
            ReportManager reportManager = new ReportManager();

            while (true) {
                printMenu();
                int command = scanner.nextInt();

                if (command == 1) {
                    reportManager.readAllReportMonth("m");
                } else if (command == 2) {
                    reportManager.readYearlyReport("y");
                } else if (command == 3) {
                    reportManager.verifyReports(reportManager.MonthReports,reportManager.YearReports);
                } else if (command == 4) {
                    reportManager.printReportMonths();
                } else if (command == 5) {
                   reportManager.printYearReport();
                } else if (command == 6) {
                    System.out.println("Выход");
                    break;
                } else {
                    System.out.println("Извините, такой команды пока нет.");
                }
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты"); //Сверить отчёты — по сохранённым данным проверить, сходятся ли отчёты за месяцы и за год.
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Завершения программы");
    }
 }


