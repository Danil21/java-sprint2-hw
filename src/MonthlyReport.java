import java.util.ArrayList;

public class MonthlyReport {
    /* Информация из месячных отчётов:
    название месяца;
    самый прибыльный товар, название товара и сумму;
    самую большую трату, название товара и сумму. */
    
    ArrayList<MonthRecord> expenses;
    ArrayList<MonthRecord> incomes;
    ArrayList<MonthRecord> records;

    MonthlyReport(ArrayList<MonthRecord> expenses, ArrayList<MonthRecord> incomes){
        this.expenses = expenses;
        this.incomes = incomes;
    }

    MonthlyReport(ArrayList<MonthRecord> records){
        this.records = records;
    }







    /*
    public MonthlyReport(String path) {
        String content = fileReader.readFileContentsString(path);
        String[] lines = content.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i]; //item_name,is_expense,quantity,sum_of_one
            String[] parts = line.split(",");
            String title = parts[0]; //item_name
            boolean isExpense = Boolean.parseBoolean(parts[1]); //это расходы. True or false
            int quantity = Integer.parseInt(parts[2]); //количество
            double sumOfOne = Double.parseDouble(parts[3]); //сумма одного

            MonthRecord sale = new MonthRecord(title, isExpense, quantity, sumOfOne);
            expenses.add(sale);
        }
    }

         Integer calculateMonthlyExpenses(HashMap<Integer, MonthlyReport> monthlyReports) {
            int sum = 0;
            for (MonthlyReport report : monthlyReports.values()) {
            for (int i = 0; i < report.expenses.size(); i++) {
                ReportManager.Record record = report.expenses.get(i);
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
