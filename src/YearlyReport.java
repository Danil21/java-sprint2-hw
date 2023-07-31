import java.util.ArrayList;

public class YearlyReport {

    /* Информация из годового отчёта.
     При вызове этой функции программа должна выводить такие данные:
    рассматриваемый год;
    прибыль по каждому месяцу;
    средний расход за все имеющиеся операции в году;
    средний доход за все имеющиеся операции в году. */

    ArrayList<YearRecord> yearExpense;
    ArrayList<YearRecord> amount;


    YearlyReport(ArrayList<YearRecord> yearExpense, ArrayList<YearRecord> amount){
        this.yearExpense = yearExpense;
        this.amount = amount;
    }

}
