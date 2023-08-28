
public class YearRecord {

    String nam;
    Integer nameMonth;
    Integer amount;  
    boolean expense; 

    YearRecord(String nam, Integer nameMonth, Integer amount, boolean expense) {
        this.nam = nam;
        this.nameMonth = nameMonth; // месяц, целое число, обозначается строго двумя цифрами, начиная с единицы, то есть 01 — «январь», а 11 — «ноябрь»;
        this.amount = amount; //сумма
        this.expense = expense;  // одно из двух значений: true или false. Обозначает, является ли запись тратой (true) или доходом (false)
    }

    public Integer getNameMonth(){
        return nameMonth;
    }

    public int getPrice() {
        return amount;
    }
}
