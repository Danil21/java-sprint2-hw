import java.util.ArrayList;

public class MonthlyReport {
    /* Информация из месячных отчётов:
    название месяца;
    самый прибыльный товар, название товара и сумму;
    самую большую трату, название товара и сумму. */
    int month;
    ArrayList<MonthRecord> expenses;
    ArrayList<MonthRecord> incomes;

    MonthlyReport(int month, ArrayList<MonthRecord> expenses, ArrayList<MonthRecord> incomes){
        this.expenses = expenses;
        this.incomes = incomes;
        this.month = month;
    }

}
