import java.util.ArrayList;

public class MonthlyReport {
    MonthRecord record;
    ArrayList<MonthRecord> expenses = new ArrayList<>();;
    ArrayList<MonthRecord> incomes = new ArrayList<>();;
    //String nameMonth;
    private FileReader fileReader = new FileReader();

    MonthlyReport(ArrayList<MonthRecord> expenses, ArrayList<MonthRecord> incomes){
        this.expenses = expenses;
        this.incomes = incomes;
    }

  /* String setMonthName(){
        return record.name;
    } */ 

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

}
