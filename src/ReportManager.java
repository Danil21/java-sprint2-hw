import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportManager {

    FileReader fileReader = new FileReader();
   
    ReportList reportList;
    
    

        void readReportMonthList(String purposeFile) {
            ArrayList<String> lines = new ArrayList<>();

            for(int i=1; i<4; i++){
                String fileName = purposeFile + ".20210" + i;
                lines = fileReader.readFileContents(fileName);
                if(lines.isEmpty()){return;}
            }

          ArrayList<Record> records = new ArrayList<>();
          for (int i = 1; i < lines.length; i++) {
            Record record = makeRecordFromLine(lines[i]);
            records.add(record);
        }

        reportList = new ReportList(records);
        System.out.println("Список успешно загружен!");
    }

      void readReportYearList(String purposeFile) {
                ArrayList<String> lines;
                String fileName = purposeFile + ".2021";
                lines = fileReader.readFileContents(fileName);
                if(lines.isEmpty()){return;}
            
           
        System.out.println("Список успешно загружен!");

        }

    Record makeRecordFromLine(String line) {
        String[] tokens = line.split(",");
        return new Record(
            tokens[0],
            Boolean.parseBoolean(tokens[1]),
            Integer.parseInt(tokens[2]),
            Double.parseDouble(tokens[3])
        );
    }

    void printReport() {
        if (reportList == null) {
            System.out.println("Отчет не считан");
        }

        System.out.println("Отчет");
        for (Record record : reportList.records) {
            System.out.println(record.name + ", " + record.expense + " доход или трата, " + record.quantity + " шт., " + (record.quantity * record.unit_price) + " руб.");
        }
        System.out.println("Итого: " + reportList.calcTotalPrice() + " руб.");
    }

  /*   Integer calculateMonthlyExpenses(HashMap<Integer, MonthlyReport> monthlyReports) {
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
    } */
}