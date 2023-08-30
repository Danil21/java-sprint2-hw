import java.util.*;

public class ReportManager {

    HashMap<String, MonthlyReport> monthlyReports = new HashMap<>();
    HashMap<String, YearlyReport> yearlyReports = new HashMap<>();

    private final FileReader fileReader = new FileReader();
    static String pathDir = "./resources/";

    String[] monthName = {"Январь","Февраль","Март"};

    void readMonthlyReports() {
        for (int i = 1; i < availableMonths(); i++) {
            ArrayList<MonthRecord> monthExpenses = new ArrayList<>();
            ArrayList<MonthRecord> monthEarnings = new ArrayList<>();

            String fileName = "m.20210" + i;
            List<String> lines = fileReader.readFileContents(pathDir, fileName);
            if (lines.isEmpty()) {return;}
            
            for (int j = 1; j < lines.size(); j++) {
                String line = lines.get(j);
                MonthRecord record = makeRecordFromLine(line);
                if (record.expense) { monthExpenses.add(record); }
                else { monthEarnings.add(record); }
            }
            monthlyReports.put(monthName[i-1], new MonthlyReport(i, monthExpenses, monthEarnings)); //кажеться тут надо переделать на Int
        }
        System.out.println("\n Месячные отчеты успешно загружены! \n");
    }

    int availableMonths() {
        return 4;
    }

    void readYearlyReport() {

        String  fileName = "y" + ".2021";
        List<String> lines = fileReader.readFileContents(pathDir,fileName);

        ArrayList<YearRecord> expenses = new ArrayList<>();
        ArrayList<YearRecord> incomes = new ArrayList<>();

        for(int i=1; i<availableMonths(); i++){
            for (int j = 1; j < lines.size(); j++) {
                YearRecord record = makeRecordFromLineYear(lines.get(j));
                if(record.expense){expenses.add(record);}
                else {incomes.add(record);}
            }
            yearlyReports.put(monthName[i-1], new YearlyReport(expenses, incomes));
        }
        System.out.println("\n Годовой отчет успешно загружен! \n");
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
                Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]),
                Boolean.parseBoolean(tokens[2])
        );
    }

    /*    название месяца;
    самый прибыльный товар, название товара и сумму;
    самую большую трату, название товара и сумму. */
    void printReportMonths() {
        if (monthlyReports.size() == 0) {System.out.println("Отчет не считан"); return;}

        for(String keyMap : monthName){
            System.out.println("\n" + keyMap + ":");
            System.out.println("Самый прибыльный товар " +  getMostProfitable(monthlyReports.get(keyMap)) + " руб.");
            System.out.println("Самая большая трата " + getBigWaste(monthlyReports.get(keyMap)) + " руб.");
         } 
    }

    HashMap<String,Integer> getBigWaste(MonthlyReport monthlyReport){
        int bestPriceProduct = 0;
        String nameProduct = "";
        HashMap<String,Integer> productProfitable = new HashMap<>();

        for (int i = 0; i < monthlyReport.expenses.size(); i++) {

            MonthRecord record = monthlyReport.expenses.get(i);
            if(record.unitPrice > bestPriceProduct){
                bestPriceProduct = record.unitPrice;
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
            if(record.unitPrice > bestPriceProduct){
                bestPriceProduct = record.unitPrice;
                nameProduct = record.name;
            }
        }
        productProfitable.put(nameProduct,bestPriceProduct);
        return productProfitable;
    }

    String verifyInconsistenciesInMonth(HashMap <String, MonthlyReport> monthlyReports, HashMap<String, YearlyReport> yearlyRep) {
        int sum = 0;
        int sumY = 0;
        String nameInconsistencies = null;
        for (Map.Entry<String, MonthlyReport> report : monthlyReports.entrySet()) {
            for (int i= 0; i < report.getValue().incomes.size(); i++) {
                MonthRecord recordM = report.getValue().incomes.get(i);
                sum += recordM.unitPrice * recordM.quantity;
            }

            for (YearlyReport reportY : yearlyRep.values()) {
                YearRecord record = reportY.incomes.get(  );
                sumY = record.getPrice();
            }

            if(sum != sumY){
                nameInconsistencies = report.getKey();

            }

        }
        return nameInconsistencies;
    }


    String verifyInconsistenciesExpenses(HashMap <String, MonthlyReport> monthlyReports, HashMap<String, YearlyReport> yearlyRep){
        int sum = 0;
        String  nameInconsistencies = null;
        for (Map.Entry<String, MonthlyReport> report : monthlyReports.entrySet()) {
            for (int i = 0; i < report.getValue().expenses.size(); i++) {
                MonthRecord record = report.getValue().expenses.get(i);
                sum += record.unitPrice * record.quantity;
            }
            if(sum != calculateYearlyExpenses(yearlyRep)){
                nameInconsistencies = report.getKey();
            }
        }
        return nameInconsistencies;
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
        if (yearlyReports.size() == 0) {System.out.println("Отчет не считан"); return;}

        for(String keyMap : monthName){
            System.out.println("\n" + keyMap);
            System.out.println("Прибыль: " + getProfitForMonth(yearlyReports.get(keyMap)) + " руб.");
            System.out.println("Средний расход: " + getAverageExpenseForYear(yearlyReports) + " руб.");
            System.out.println("Средний доход: " + calculateYearlyIncomesAverage(yearlyReports) + " руб.");
        }
    }

    Integer getAverageExpenseForYear(HashMap<String, YearlyReport> yearlyReport) {
        int average = 0;
        for (YearlyReport report : yearlyReport.values()) {
            for (int i = 1; i < report.expenses.size(); i++) {
                YearRecord record = report.expenses.get(i);
                average += record.getPrice() / report.expenses.size();
            }
        }
        return average;
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
                sum = record.getPrice();
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

    void verifyReports(HashMap<String, MonthlyReport> monthlyReports, HashMap<String, YearlyReport> yearlyReport) {
        if (yearlyReport.size() == 0 || monthlyReports.size() == 0) {
            System.out.println("\n Ошибка, не считан один из отчетов.\n");
            return;
        }

        if(verifyInconsistenciesInMonth(monthlyReports, yearlyReport) != null){
            System.out.println("\n Выявлено несоответствие данных по доходам за " +  verifyInconsistenciesInMonth(monthlyReports, yearlyReport)  + "! \n");
        }
        if(verifyInconsistenciesExpenses(monthlyReports,yearlyReport) != null){
            System.out.println("\n Выявлено несоответствие данных по расходам за " +  verifyInconsistenciesInMonth(monthlyReports, yearlyReport)  + "! \n");
        }
        if(verifyInconsistenciesExpenses(monthlyReports,yearlyReport) == null | verifyInconsistenciesInMonth(monthlyReports, yearlyReport) == null){System.out.println("\n Расхождений не найденно \n");}
    }

}