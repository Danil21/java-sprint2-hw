import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportManager {

    HashMap<String, MonthlyReport> MonthReports = new HashMap<>();

    private FileReader fileReader = new FileReader();

    MonthlyReport monthlyReport;
    YearlyReport yearlyReport;

    String[] manthName = {"Январь","Февраль","Март"};

    void readAllReportMonth(String purposeFile) {
            
        String fileName;

        for(int i=1; i<4; i++){

                fileName = purposeFile + ".20210" + i;
                List<String> lines = fileReader.readFileContents(fileName);

                ArrayList<MonthRecord> expenses = new ArrayList<>();
                ArrayList<MonthRecord> incomes = new ArrayList<>();
                  
        for (int j = 1; j < lines.size(); j++) {

            String lin = lines.get(j);
            MonthRecord record = makeRecordFromLine(lin);
            if(record.expense){expenses.add(record);}
            else {incomes.add(record);}

            monthlyReport = new MonthlyReport(expenses, incomes);
        }

        MonthReports.put(manthName[i-1], monthlyReport);
     }
        
       System.out.println("Список успешно загружен!");
    }


    MonthRecord makeRecordFromLine(String line) {
        String[] tokens = line.split(",");
        return new MonthRecord(
            tokens[0],
            Boolean.parseBoolean(tokens[1]),
            Integer.parseInt(tokens[2]),
            Integer.parseInt(tokens[3])
        );
    }

    void printReportAllMonth() {
        if (monthlyReport == null) {System.out.println("Отчет не считан"); return;}
        for (String key : MonthReports.keySet()) {
              for (MonthRecord record : monthlyReport.expenses){
                System.out.println("Самый прибыльный товар: " + record.name + record.quantity + " шт., "  + record.unit_price + " руб.");
            }
             System.out.println("\n");
             System.out.println("Отчет за: " + key);
        }
    }

    /*    название месяца;
    самый прибыльный товар, название товара и сумму;
    самую большую трату, название товара и сумму. */
    void printReportMonths() {
        if (monthlyReport == null) {System.out.println("Отчет не считан"); return;}

            for(MonthlyReport reports : MonthReports.values()){

             System.out.println(getKeyMap(MonthReports, reports));
             System.out.println("Самый прибыльный товар: " + "проданный на сумму " + getMostProfitable(reports) + " руб.");
            }     
    }

    String getNameBestProduct(HashMap<Integer, MonthlyReport> monthlyReports) {
        String nameProduct ="";
    for (MonthlyReport report : monthlyReports.values()) {
        for (int i = 0; i < report.incomes.size(); i++) {
            MonthRecord record = report.incomes.get(i);
            nameProduct = record.name;
        }
    }

    return nameProduct;
}

    HashMap<String,Integer> getMostProfitable(MonthlyReport monthlyReport){
        int bestPriceProduct = 0;
        String nameProduct = "";
        HashMap<String,Integer> productProfitable = new HashMap<>();

            for (int i = 0; i < monthlyReport.incomes.size(); i++) {

                MonthRecord record = monthlyReport.incomes.get(i);
                if(record.unit_price > bestPriceProduct){
                    bestPriceProduct = record.unit_price;
                    nameProduct = record.name;
                }
                productProfitable.put(nameProduct,bestPriceProduct);
        }

        return productProfitable;
    }

   public <K, V> String getKeyMap(HashMap <String, MonthlyReport> map, V value)
    {
        for (String key: map.keySet())
        {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
    }
 

  /*      ArrayList<Integer> getMostProfitableProduct(HashMap <String, MonthlyReport> monthlyReports){
        int bestPriceProduct = 0;
        ArrayList<Integer> listBestPriceProduct = new ArrayList<>();
 
         for (MonthlyReport report : monthlyReports.values()){

            for (int i = 0; i < report.incomes.size(); i++) {

                MonthRecord record = report.incomes.get(i);
                if(record.unit_price > bestPriceProduct){
                    bestPriceProduct = record.unit_price;
                }
                
            }
            
             listBestPriceProduct.add(bestPriceProduct);
             bestPriceProduct = 0; // обнуляем для нахождения самой большой цены в следуюущем месяце
        }

        return listBestPriceProduct;
    }
*/ 


    void printYear() {
        if (yearlyReport == null) {System.out.println("Отчет не считан"); return;} 

        System.out.println("Отчет:");
        for (YearRecord record : yearlyReport.is_expense) {
            System.out.println(record.nameMonth);
        } 
    }

    Integer calculateMonthlyExpenses(HashMap <Integer, MonthlyReport> monthlyReports) {
        int sum = 0;
        for (MonthlyReport report : monthlyReports.values()) {
        for (int i = 0; i < report.expenses.size(); i++) {
            MonthRecord record = report.expenses.get(i);
            sum += record.unit_price * record.quantity;
        }
    }

    return sum;
}
  
Integer calculateMonthlyIncomes(HashMap<Integer, MonthlyReport> monthlyReports) {
    int sum = 0;
    for (MonthlyReport report : monthlyReports.values()) {
        for (int i = 0; i < report.incomes.size(); i++) {
            MonthRecord record = report.incomes.get(i);
            sum += record.unit_price * record.quantity;
        }
    }

    return sum;
}


    Integer calculateYearlyExpenses(HashMap<Integer, YearlyReport> yearlyReport) {
        int sum = 0;
        for (YearlyReport report : yearlyReport.values()) {
            for (int i = 1; i < report.is_expense.size(); i++) {
                YearRecord record = report.is_expense.get(i);
                sum += record.getPrice();
            }
        }

        return sum;
    }

    Integer calculateYearlyIncomes(HashMap<Integer, YearlyReport> yearlyRep) {
        int sum = 0;
        for (YearlyReport report : yearlyRep.values()) {
            for (int i = 1; i < report.amount.size(); i++) {
                YearRecord record = report.amount.get(i);
                sum += record.getPrice();
            }
        }

        return sum;
    }

    Boolean verifyReports(HashMap<Integer, MonthlyReport> monthlyReports, HashMap<Integer, YearlyReport> yearlyReport) {
        if (yearlyReport.size() == 0 || monthlyReports.size() == 0) {
            System.out.println("Ошибка!");
            return false;
        }

        if (calculateYearlyIncomes(yearlyReport) != calculateMonthlyIncomes(monthlyReports)) {
            System.out.println("Выявлено несоответствие данных по доходам в месяце!");
            return false;
        }

        if (calculateYearlyExpenses(yearlyReport) != calculateMonthlyExpenses(monthlyReports)) {
            System.out.println("Выявлено несоответствие данных по расходам в месяце!");
            return false;
        }

        System.out.println("Данные успешно сверены. Несоответствий не выявлено!");
        return true;
    }



/*     
    void printReport() { распечатать один отчет
        if (monthlyReport == null) {System.out.println("Отчет не считан"); return;}
        
        System.out.println("Отчет:");
        for (MonthRecord record : monthlyReport.records) {
            System.out.println("Наименование: " + record.name + ", " + record.expense + " доход или трата, " + record.quantity + " шт., " + (record.quantity * record.unit_price) + " руб.");
        } 
    }




void readReportMonth(String purposeFile) {
        for(int i=1; i<4; i++){
            String fileName = purposeFile + ".20210" + i;
           ArrayList<String> lines = fileReader.readFileContents(fileName);
            if(lines.isEmpty()){ System.out.println("Файл пустой"); return;}
        }

        for (int j = 1; j < lines.length; j++) {
            String line = lines.get(j);
            Record record = makeRecordFromLine(lines[i]);
            records.add(record);
        }

        monthlyReports.put(i, new MonthlyReport(expenses, incomes));
        System.out.println("Список успешно загружен!");
    } */

     /* void readReportYearList(String purposeFile) {
                ArrayList<String> lines;
                String fileName = purposeFile + ".2021"; 
                lines = fileReader.readFileContents(fileName);
                if(lines.isEmpty()){return;}
            
           
        System.out.println("Список успешно загружен!");


            void readReportMonthList(String purposeFile){
        
            String fileName;
            String content;
            //String[] lines = null;

        
        for(int i=1; i<4; i++){
                fileName = purposeFile + ".20210" + i;
                content = fileReader.readFileContentsString(fileName);
               String[]  lines = content.split(System.lineSeparator());
                ArrayList<MonthRecord> records = new ArrayList<>();

        for (int j = 1; j < lines.length; j++) {
            MonthRecord record = makeRecordFromLine(lines[i]);
            records.add(record);
            
            
            }
            monthlyReport = new MonthlyReport(records);
                mapMonthReports.put(i, record);
                if(mapMonthReports.isEmpty()){return;}
                System.out.println(monthlyReport.records.get(i));
         }


        System.out.println("Список успешно загружен!");
        
    }

    } */ 


}