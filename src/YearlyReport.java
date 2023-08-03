import java.util.ArrayList;

public class YearlyReport {

    ArrayList<YearRecord> expenses;
    ArrayList<YearRecord> incomes;
    
    YearlyReport(ArrayList<YearRecord> expenses, ArrayList<YearRecord> incomes){
        this.expenses = expenses; // траты
        this.incomes = incomes; // доходы
    }

}
