public class MonthRecord {

    String name;
    boolean expense;
    int quantity;
    int unit_price;   

    MonthRecord(String name, boolean expense, int quantity, int unitPrice) {
        this.name = name;
        this.expense = expense; //является ли запись тратой
        this.quantity = quantity; //количество закупленного или проданного товара;
        this.unit_price = unitPrice; //стоимость одной единицы товара. Целое число.
    }

}
