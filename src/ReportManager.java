import java.util.*;

public class ReportManager {

    HashMap<String, MonthlyReport> monthlyReports = new HashMap<>();
    YearlyReport yearlyReports;

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

        for (int j = 1; j < lines.size(); j++) {
                YearRecord record = makeRecordFromLineYear(lines.get(j));
                if(record.expense){expenses.add(record);}
                else {incomes.add(record);}
            }
        yearlyReports = new YearlyReport(expenses, incomes);

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
        if (monthlyReports.isEmpty()) {System.out.println("Отчет не считан"); return;}

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
                if (record.unitPrice > bestPriceProduct) {
                    bestPriceProduct = record.unitPrice;
                    nameProduct = record.name;
                }
            }
            productProfitable.put(nameProduct, bestPriceProduct);

        return productProfitable;
    }

    // костыль
     Integer getNameMothByNumber(String number){
        int num;
        if(number.equals(monthName[0])){num=0;}
        else if(number.equals(monthName[1])){num=1;}
        else {num=2;}
        return num;
     }
    String verifyInconsistenciesInMonth(HashMap <String, MonthlyReport> monthlyReports, YearlyReport yearlyRep) {
        int sumMonth = 0;
        int sumYear;
        String nameInconsistencies = null;

        for (Map.Entry<String, MonthlyReport> report : monthlyReports.entrySet()) {
            for (int i= 0; i < report.getValue().incomes.size(); i++) {
                MonthRecord recordM = report.getValue().incomes.get(i);
                sumMonth += recordM.unitPrice * recordM.quantity;
            }

            YearRecord record = yearlyRep.incomes.get(getNameMothByNumber(report.getKey()));
            sumYear = record.getPrice();

            if(sumMonth != sumYear){
                nameInconsistencies = report.getKey();
            }
            sumMonth=0;
        }
        return nameInconsistencies;
    }


    String verifyExpensesInMonth(HashMap <String, MonthlyReport> monthlyReports, YearlyReport yearlyRep) {
        int sumMonth = 0;
        int sumYear;
        String nameInconsistencies = null;

        for (Map.Entry<String, MonthlyReport> report : monthlyReports.entrySet()) {
            for (int i= 0; i < report.getValue().expenses.size(); i++) {
                MonthRecord recordM = report.getValue().expenses.get(i);
                sumMonth += recordM.unitPrice * recordM.quantity;
            }

            YearRecord record = yearlyRep.expenses.get(getNameMothByNumber(report.getKey()));
            sumYear = record.getPrice();

            if(sumMonth != sumYear){
                nameInconsistencies = report.getKey();
            }
            sumMonth=0;
        }
        return nameInconsistencies;
    }

        /* Информация из годового отчёта.
     При вызове этой функции программа должна выводить такие данные:
    рассматриваемый год;
    прибыль по каждому месяцу;
    средний расход за все имеющиеся операции в году;
    средний доход за все имеющиеся операции в году. */

    void printYearReport() {
        if (yearlyReports == null) {System.out.println("Отчет не считан"); return;}

        for(String keyMap : monthName){
            System.out.println("\n" + keyMap);
            System.out.println("Прибыль: " + getProfitForMonth(yearlyReports) + " руб.");
            System.out.println("Средний расход: " + getAverageExpenseForYear(yearlyReports) + " руб.");
            System.out.println("Средний доход: " + calculateYearlyIncomesAverage(yearlyReports) + " руб.");
        }
    }

    Integer getAverageExpenseForYear(YearlyReport yearlyReport) {
        int average = 0;

        for (int i = 1; i < yearlyReport.expenses.size(); i++) {
                YearRecord record = yearlyReport.expenses.get(i);
                average += record.getPrice() / yearlyReport.expenses.size();
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

    Integer calculateYearlyIncomesAverage(YearlyReport yearlyRep) {
        int sum = 0;
            for (int i = 1; i < yearlyRep.incomes.size(); i++) {
                YearRecord record = yearlyRep.incomes.get(i);
                sum += record.getPrice() / yearlyRep.incomes.size();
            }
        return sum;
    }

    void verifyReports(HashMap<String, MonthlyReport> monthlyReports, YearlyReport yearlyReport) {
        if (yearlyReport == null || monthlyReports.size() == 0) {
            System.out.println("\n Ошибка, не считан один из отчетов.\n");
            return;
        }

        if(verifyInconsistenciesInMonth(monthlyReports, yearlyReport) != null){
            System.out.println("\n Выявлено несоответствие данных по доходам за " +  verifyInconsistenciesInMonth(monthlyReports, yearlyReport)  + "! \n");
        }
        if(verifyExpensesInMonth(monthlyReports,yearlyReport) != null){
            System.out.println("\n Выявлено несоответствие данных по расходам за " +  verifyInconsistenciesInMonth(monthlyReports, yearlyReport)  + "! \n");
        }
        if(verifyExpensesInMonth(monthlyReports,yearlyReport) == null | verifyInconsistenciesInMonth(monthlyReports, yearlyReport) == null){System.out.println("\n Расхождений не найденно \n");}
    }

}