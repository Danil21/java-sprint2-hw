import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    /* Информация из месячных отчётов:
    название месяца;
    самый прибыльный товар, название товара и сумму;
    самую большую трату, название товара и сумму. */
    
    ArrayList<MonthRecord> expenses;
    ArrayList<MonthRecord> incomes;
   // ArrayList<MonthRecord> records;

    MonthlyReport(ArrayList<MonthRecord> expenses, ArrayList<MonthRecord> incomes){
        this.expenses = expenses;
        this.incomes = incomes;
    }

   /* MonthlyReport(ArrayList<MonthRecord> records){
        this.records = records;
    }
 */
    


}
