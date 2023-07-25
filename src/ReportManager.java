import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportManager {

    //HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();

    HashMap<Integer,String> monthReports = new HashMap<>();

    FileReader fileReader = new FileReader();
    MonthlyReport monthlyReport = new MonthlyReport();

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

    public void monthLoader(String fileName, String monthName, List<MonthRecord> reports) {
        // принмает на вход файл и лист,
        // возвращает лист наполненный
        // экземпляярами класса SaverMonthly
        ArrayList<String> monthReports = fileReader.readFileContents(fileName);
        for (int i = 1; i < monthReports.size(); i++) {
            String[] partsOfContent = monthReports.get(i).split(","); // item_name,is_expense,quantity,unit_price
            String itemName = partsOfContent[0];
            boolean isExpense = Boolean.parseBoolean(partsOfContent[1]);
            int quantity = Integer.parseInt(partsOfContent[2]);
            int unitPrice = Integer.parseInt(partsOfContent[3]);
            // стоимость одной единицы товара
            MonthRecord saverMonthly = new MonthRecord(itemName, isExpense, quantity, unitPrice, monthName);
            reports.add(saverMonthly);
        }
    }

    public void readAndSaveReports() { // ложит месячнные отчеты в лист, содержащщий
        // листы с экземплярами класса SaverMonthly
        if (monthlyReports.isEmpty()) {
            String monthName;
            String fileName;
            for (int i = 1; i < 4; i++) {
                fileName = "m.20210" + i + ".csv";
                if (i == 1) {
                    monthName = "Январь";
                    List<SaverMonthly> month = new ArrayList<>();
                    monthLoader(fileName, monthName, month);
                    monthlyReports.put(monthName,month);
                    System.out.println("За январь считанно");
                } else if (i == 2) {
                    monthName = "Февраль";
                    List<SaverMonthly> month = new ArrayList<>();
                    monthLoader(fileName, monthName, month);
                    monthlyReports.put(monthName,month);
                    System.out.println("За февраль считанно");
                } else {
                    monthName = "Март";
                    List<SaverMonthly> month = new ArrayList<>();
                    monthLoader(fileName, monthName, month);
                    monthlyReports.put(monthName,month);
                    System.out.println("За март считанно");
                }
            }
        } else {
            System.out.println("Месячные отчеты уже были считаны!");
        }
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
        if (MonthlyReport) {
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