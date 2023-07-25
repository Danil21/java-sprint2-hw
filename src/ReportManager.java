import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportManager {

    HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();
    private FileReader fileReader = new FileReader();
    MonthlyReport monthlyReport = new MonthlyReport();

    HashMap<Integer,String> monthReports = new HashMap<>();

  //  ReportList reportList;

        void readReportMonthList(String purposeFile) {
            
            String fileName;

            for(int i=1; i<4; i++){
                fileName = purposeFile + ".20210" + i;
                monthReports.put(i, fileReader.readFileContentsString(fileName));
                if(monthReports.isEmpty()){return;}
            }

            ArrayList<MonthRecord> records = new ArrayList<>();
            for (Integer key : monthReports.keySet()){ 
              MonthRecord record = makeRecordFromLine(monthReports.get(key));
              records.add(record);
            }

        /* ArrayList<Record> records = new ArrayList<>();
          for (int i = 1; i < lines.length(); i++) {
            Record record = 
            records.add(record);  
        }*/

      //  reportList = new ReportList(records);
        System.out.println("Список успешно загружен!");
    }

/*     void readReportMonth(String purposeFile) {
        for(int i=1; i<4; i++){
            String fileName = purposeFile + ".20210" + i;
           ArrayList<String> lines = fileReader.readFileContents(fileName);
            if(lines.isEmpty()){ System.out.println("Файл пустой"); return;}
        }

        for (int j = 1; j < lines.length; j++) {
            String line = lines.get(j);
            Record record = makeRecordFromLine(lines[i]);
            records.add(record);
        }

        monthlyReports.put(i, new MonthlyReport(expenses, incomes));
        System.out.println("Список успешно загружен!");
    } */

      void readReportYearList(String purposeFile) {
                ArrayList<String> lines;
                String fileName = purposeFile + ".2021";
                lines = fileReader.readFileContents(fileName);
                if(lines.isEmpty()){return;}
            
           
        System.out.println("Список успешно загружен!");

        }

    MonthRecord makeRecordFromLine(String line) {
        String[] tokens = line.split(",");
        return new MonthRecord(
            tokens[0],
            Boolean.parseBoolean(tokens[1]),
            Integer.parseInt(tokens[2]),
            Double.parseDouble(tokens[3])
        );
    }

    void printReport() {
        if (MonthlyReport == null) {
            System.out.println("Отчет не считан");
        }

        System.out.println("Отчет");
        for (MonthRecord record : reportList.records) {
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