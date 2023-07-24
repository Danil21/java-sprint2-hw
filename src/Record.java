public class Record {

    String name;
    boolean expense;
    int quantity;
    double unit_price;   

    Record(String newName, boolean newExpense, int newQuantity, double newUnitPrice) {
        name = newName;
        expense = newExpense; //является ли запись тратой
        quantity = newQuantity; //количество закупленного или проданного товара;
        unit_price = newUnitPrice; //стоимость одной единицы товара. Целое число.
        
    }

}
