import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {

    /* Информация из годового отчёта.
     При вызове этой функции программа должна выводить такие данные:
    рассматриваемый год;
    прибыль по каждому месяцу;
    средний расход за все имеющиеся операции в году;
    средний доход за все имеющиеся операции в году. */

    ArrayList<YearRecord> amount;
    ArrayList<YearRecord> is_expense;
    
    YearlyReport(ArrayList<YearRecord> is_expense, ArrayList<YearRecord> amount){
        this.amount = amount; //сумма
        this.is_expense = is_expense; // бозначает, является ли запись тратой (true) или доходом (false).
    }

}
