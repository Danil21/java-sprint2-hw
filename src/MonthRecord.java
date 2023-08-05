public class MonthRecord {

    String name;
    boolean expense;
    int quantity;
    int unitPrice;   

    MonthRecord(String name, boolean expense, int quantity, int unitPrice) {
        this.name = name;
        this.expense = expense; //является ли запись тратой
        this.quantity = quantity; //количество закупленного или проданного товара;
        this.unitPrice = unitPrice; //стоимость одной единицы товара. Целое число.
    }

}
