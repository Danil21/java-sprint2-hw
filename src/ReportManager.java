import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportManager {

    MonthlyReport monthlyReport;
    YearlyReport yearlyReport;
    HashMap<String, MonthlyReport> MonthReports = new HashMap<>();
    HashMap<String, YearlyReport> YearReports = new HashMap<>();

    private final FileReader fileReader = new FileReader();

    String[] manthName = {"Январь","Февраль","Март"};

    void readAllReportMonth(String purposeFile) {

        String fileName;

        for(int i=1; i<4; i++){

            fileName = purposeFile + ".20210" + i;
            List<String> lines = fileReader.readFileContents(fileName);

            ArrayList<MonthRecord> expenses = new ArrayList<>();
            ArrayList<MonthRecord> incomes = new ArrayList<>();

            for (int j = 1; j < lines.size(); j++) {

                String lin = lines.get(j);
                MonthRecord record = makeRecordFromLine(lin);
                if(record.expense){expenses.add(record);}
                else {incomes.add(record);}

                monthlyReport = new MonthlyReport(expenses, incomes);
            }

            MonthReports.put(manthName[i-1], monthlyReport);
        }

        System.out.println("\n Месячные отчеты успешно загружены! \n");
    }


    void readYearlyReport(String purposeFile) {

        String  fileName = purposeFile + ".2021";
        List<String> lines = fileReader.readFileContents(fileName);

        ArrayList<YearRecord> expenses = new ArrayList<>();
        ArrayList<YearRecord> incomes = new ArrayList<>();

        for(int i=1; i<4; i++){
            for (int j = 1; j < lines.size(); j++) {
                YearRecord record = makeRecordFromLineYear(lines.get(j));

                if(record.expense){expenses.add(record);}
                else {incomes.add(record);}
                yearlyReport = new YearlyReport(expenses,incomes);
            }
            YearReports.put(manthName[i-1], yearlyReport);
        }
        System.out.println("\n Готовой отчет успешно загружен! \n");
    }


    MonthRecord makeRecordFromLine(String line) {
        String[] tokens = line.split(",");
        return new MonthRecord(
                tokens[0],
                Boolean.parseBoolean(tokens[1]),
                Integer.parseInt(tokens[2]),
                Integer.parseInt(tokens[3])
        );
    }

    YearRecord makeRecordFromLineYear(String line) {
        String[] tokens = line.split(",");
        return new YearRecord(
                tokens[0],
                Integer.parseInt(tokens[1]),
                Boolean.parseBoolean(tokens[2])
        );
    }


    /*    название месяца;
    самый прибыльный товар, название товара и сумму;
    самую большую трату, название товара и сумму. */
    void printReportMonths() {
        if (monthlyReport == null) {System.out.println("Отчет не считан"); return;}

        for(String keyMap : manthName){
            System.out.println("\n" + keyMap + ":");
            System.out.println("Самый прибыльный товар " +  getMostProfitable(MonthReports.get(keyMap)) + " руб.");
            System.out.println("Самая большая трата " + getBigWaste(MonthReports.get(keyMap)) + " руб.");
         } 
    }

    HashMap<String,Integer> getBigWaste(MonthlyReport monthlyReport){
        int bestPriceProduct = 0;
        String nameProduct = "";
        HashMap<String,Integer> productProfitable = new HashMap<>();

        for (int i = 0; i < monthlyReport.expenses.size(); i++) {

            MonthRecord record = monthlyReport.expenses.get(i);
            if(record.unit_price > bestPriceProduct){
                bestPriceProduct = record.unit_price;
                nameProduct = record.name;
            }

        }
        productProfitable.put(nameProduct,bestPriceProduct);
        return productProfitable;
    }

    HashMap<String,Integer> getMostProfitable(MonthlyReport monthlyReport){
        int bestPriceProduct = 0;
        String nameProduct = "";
        HashMap<String,Integer> productProfitable = new HashMap<>();

        for (int i = 0; i < monthlyReport.incomes.size(); i++) {

            MonthRecord record = monthlyReport.incomes.get(i);
            if(record.unit_price > bestPriceProduct){
                bestPriceProduct = record.unit_price;
                nameProduct = record.name;
            }
        }
        productProfitable.put(nameProduct,bestPriceProduct);
        return productProfitable;
    }

    Integer calculateMonthlyExpenses(HashMap <String, MonthlyReport> monthlyReports) {
        int sum = 0;
        for (MonthlyReport report : monthlyReports.values()) {
            for (int i = 0; i < report.expenses.size(); i++) {
                MonthRecord record = report.expenses.get(i);
                sum += record.unit_price * record.quantity;
            }
        }

        return sum;
    }

    Integer calculateMonthlyIncomes(HashMap<String, MonthlyReport> monthlyReports) {
        int sum = 0;
        for (MonthlyReport report : monthlyReports.values()) {
            for (int i = 0; i < report.incomes.size(); i++) {
                MonthRecord record = report.incomes.get(i);
                sum += record.unit_price * record.quantity;
            }
        }

        return sum;
    }

        Integer bestProductMonthly(HashMap<String, MonthlyReport> monthlyReports) {
        int sum = 0;
        for (MonthlyReport report : monthlyReports.values()) {
            for (int i = 0; i < report.incomes.size(); i++) {
                MonthRecord record = report.incomes.get(i);
                sum += record.unit_price * record.quantity;
            }
        }

        return sum;
    }

    Integer calculateYearlyExpenses(HashMap<String, YearlyReport> yearlyReport) {
        int sum = 0;
        for (YearlyReport report : yearlyReport.values()) {
            for (int i = 1; i < report.expenses.size(); i++) {
                YearRecord record = report.expenses.get(i);
                sum += record.getPrice();
            }
        }

        return sum;
    }

        /* Информация из годового отчёта.
     При вызове этой функции программа должна выводить такие данные:
    рассматриваемый год;
    прибыль по каждому месяцу;
    средний расход за все имеющиеся операции в году;
    средний доход за все имеющиеся операции в году. */

    void printYearReport() {
        if (yearlyReport == null) {System.out.println("Отчет не считан"); return;}

        for(String keyMap : manthName){
            System.out.println("\n" + keyMap);
            System.out.println("Прибыль: " + getProfitForMonth(YearReports.get(keyMap)) + " руб.");
            System.out.println("Средний расход: " + getAverageExpenseForYear(YearReports) + " руб.");
            System.out.println("Средний доход: " + calculateYearlyIncomesAverage(YearReports) + " руб.");
        }

    }


    Integer getAverageExpenseForYear(HashMap<String, YearlyReport> yearlyReport) {
        int sum = 0;
        for (YearlyReport report : yearlyReport.values()) {
            for (int i = 1; i < report.expenses.size(); i++) {
                YearRecord record = report.expenses.get(i);
                sum += record.getPrice() / report.expenses.size();
            }
        }
        return sum;
    }

    //прибыль по месяцу;
    int getProfitForMonth(YearlyReport yearlyReport){

        int expAmount=0;
        int incAmount=0;

        for (int i = 0; i < yearlyReport.expenses.size(); i++) {
            YearRecord recordExpenses = yearlyReport.expenses.get(i);
            expAmount += recordExpenses.amount;
        }

        for (int i = 0; i < yearlyReport.incomes.size(); i++) {
            YearRecord recordIncomes = yearlyReport.incomes.get(i);
            incAmount += recordIncomes.amount;
        }

        return incAmount - expAmount;
    }


    Integer calculateYearlyIncomes(HashMap<String, YearlyReport> yearlyRep) {
        int sum = 0;
        for (YearlyReport report : yearlyRep.values()) {
            for (int i = 1; i < report.incomes.size(); i++) {
                YearRecord record = report.incomes.get(i);
                sum += record.getPrice();
            }
        }

        return sum;
    }

    Integer calculateYearlyIncomesAverage(HashMap<String, YearlyReport> yearlyRep) {
        int sum = 0;
        for (YearlyReport report : yearlyRep.values()) {
            for (int i = 1; i < report.incomes.size(); i++) {
                YearRecord record = report.incomes.get(i);
                sum += record.getPrice() / report.incomes.size();
            }
        }

        return sum;
    }

    Boolean verifyReports(HashMap<String, MonthlyReport> monthlyReports, HashMap<String, YearlyReport> yearlyReport) {
        if (yearlyReport.size() == 0 || monthlyReports.size() == 0) {
            System.out.println("\n Ошибка, не считан один из отчетов. \n");
            return false;
        }

        if (calculateYearlyIncomes(yearlyReport) != calculateMonthlyIncomes(monthlyReports)) {
            System.out.println("\n Выявлено несоответствие данных по доходам в месяце! \n");
            return false;
        }

        if (calculateYearlyExpenses(yearlyReport) != calculateMonthlyExpenses(monthlyReports)) {
            System.out.println("\n Выявлено несоответствие данных по расходам в месяце! \n");
            return false;
        }

        System.out.println("\n Данные успешно сверены. Несоответствий не выявлено! \n");
        return true;
    }

}