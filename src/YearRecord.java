
public class YearRecord {

    String nameMonth;
    double amount;  
    boolean expense; 

    YearRecord(String nameMonth, double amount, boolean expense) {
        this.nameMonth = nameMonth;
        this.amount = amount;
        this.expense = expense; 
    }

    public String getNameMonth(){
        return nameMonth;
    }

    public double getPrice() {
        return amount;
    }
}
