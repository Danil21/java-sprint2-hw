import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.File;

public class ReportManager {


    FileReader fileReader = new FileReader();
    ArrayList<Record> records = new ArrayList<>();
    void readShoppingList(String fileName) {
        String content = readFileContentsOrNull(fileName);
        String[] lines = content.split(System.lineSeparator());


        for (int i = 1; i < lines.length; i++) {
            Record record = makeRecordFromLine(lines[i]);
            records.add(record);
        }

        shoppingList = new ShoppingList(records);
        System.out.println("Список успешно загружен!");
    }


    String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }


    Integer calculateMonthlyExpenses(HashMap<Integer, MonthlyReport> monthlyReports) {
        int sum = 0;
        for (MonthlyReport report : monthlyReports.values()) {
            for (int i = 0; i < report.expenses.size(); i++) {
                ReportEngine.Record record = report.expenses.get(i);
                sum += record.price * record.quantity;
            }
        }

        return sum;
    }
    

    Integer calculateMonthlyIncomes(HashMap<Integer, MonthlyReport> monthlyReports) {
        int sum = 0;
        for (MonthlyReport report : monthlyReports.values()) {
            for (int i = 0; i < report.incomes.size(); i++) {
                ReportEngine.Record record = report.incomes.get(i);
                sum += record.price * record.quantity;
            }
        }

        return sum;
    }

    Integer calculateYearlyExpenses(HashMap<Integer, YearlyReport> yearlyReport) {
        int sum = 0;
        for (YearlyReport report : yearlyReport.values()) {
            for (int i = 1; i < report.expenses.size(); i++) {
                ReportEngine.Record record = report.expenses.get(i);
                sum += record.getPrice();
            }
        }

        return sum;
    }

    Integer calculateYearlyIncomes(HashMap<Integer, YearlyReport> yearlyRep) {
        int sum = 0;
        for (YearlyReport report : yearlyRep.values()) {
            for (int i = 1; i < report.incomes.size(); i++) {
                ReportEngine.Record record = report.incomes.get(i);
                sum += record.getPrice();
            }
        }

        return sum;
    }

    Boolean verifyReports(HashMap<Integer, MonthlyReport> monthlyReports, HashMap<Integer, YearlyReport> yearlyReport) {
        if (yearlyReport.size() == 0 || monthlyReports.size() == 0) {
            System.out.println("Ошибка!");
            return false;
        }

        if (calculateYearlyIncomes(yearlyReport) != calculateMonthlyIncomes(monthlyReports)) {
            System.out.println("Выявлено несоответствие данных по доходам в месяце!");
            return false;
        }

        if (calculateYearlyExpenses(yearlyReport) != calculateMonthlyExpenses(monthlyReports)) {
            System.out.println("Выявлено несоответствие данных по расходам в месяце!");
            return false;
        }

        System.out.println("Данные успешно сверены. Несоответствий не выявлено!");
        return true;
    }
}