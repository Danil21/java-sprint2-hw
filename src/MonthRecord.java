public class MonthRecord {

    String name;
    boolean expense;
    int quantity;
    double unit_price;   

    MonthRecord(String Name, boolean Expense, int Quantity, double UnitPrice) {
        name = Name;
        expense = Expense; //является ли запись тратой
        quantity = Quantity; //количество закупленного или проданного товара;
        unit_price = UnitPrice; //стоимость одной единицы товара. Целое число.
    }

}
