import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportManager {

    HashMap<String, MonthlyReport> allMonthReports = new HashMap<>();

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
                ArrayList<MonthRecord> listRecords = new ArrayList<>();
                  
        for (int j = 1; j < lines.size(); j++) {

            String lin = lines.get(j);
            MonthRecord record = makeRecordFromLine(lin);
            if(record.expense == true){expenses.add(record);}
            else { incomes.add(record);}

            //listRecords.add(listRecords);
            monthlyReport = new MonthlyReport(expenses, incomes);
        }

        allMonthReports.put(manthName[i-1], monthlyReport);
     }
        
       System.out.println("Список успешно загружен!");
    }


    MonthRecord makeRecordFromLine(String line) {
        String[] tokens = line.split(",");
        return new MonthRecord(
            tokens[0],
            Boolean.parseBoolean(tokens[1]),
            Integer.parseInt(tokens[2]),
            Double.parseDouble(tokens[3])
        );
    }

    void printReportAllMonth() {
        if (monthlyReport == null) {System.out.println("Отчет не считан"); return;}
        for (String key : allMonthReports.keySet()) {
              for (MonthRecord record : monthlyReport.expenses){
                System.out.println("Наименование: " + record.name + ", " + record.expense + " доход или трата, " 
                 + record.quantity + " шт., "  + record.unit_price + " руб.");
            }
             System.out.println("\n");
             System.out.println("Отчет за: " + key);
        }
    }

    void printReport() {
        if (monthlyReport == null) {System.out.println("Отчет не считан"); return;}
        
        System.out.println("Отчет:");
        for (MonthRecord record : monthlyReport.records) {
            System.out.println("Наименование: " + record.name + ", " + record.expense + " доход или трата, " + record.quantity + " шт., " + (record.quantity * record.unit_price) + " руб.");
        } 
    }

    void printYear() {
        if (yearlyReport == null) {System.out.println("Отчет не считан"); return;} 

        System.out.println("Отчет:");
        for (YearRecord record : yearlyReport.yearExpense) {
            System.out.println(record.nameMonth);
        } 
    }






/*     void readReportMonth(String purposeFile) {
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