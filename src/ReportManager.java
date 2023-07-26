import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportManager {

    HashMap<Integer,String> mapMonthReports = new HashMap<>();

    private FileReader fileReader = new FileReader();

    MonthlyReport monthlyReport;

    void readReportMonthList(String purposeFile) {
            
            String fileName;
            String content;
            String[] lines;

        for(int i=1; i<4; i++){
                fileName = purposeFile + ".20210" + i;
                content = fileReader.readFileContentsString(fileName);
                lines = content.split(System.lineSeparator());
                mapMonthReports.put(i, content);
                if(mapMonthReports.isEmpty()){return;}
             

        ArrayList<String> MonthRecords = new ArrayList<>();
        for (int j = 1; j < lines.length; j++) {
            MonthRecord record = makeRecordFromLine(lines[j]);
            MonthRecords.add(record);
            }
        }  
       // monthlyReport = new MonthlyReport(records);
        System.out.println("Список успешно загружен!");
    }

    void pr(){System.out.println(mapMonthReports.get(1));}

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

    } */ 

    MonthRecord makeRecordFromLine(String line) {
        String[] tokens = line.split(",");
        return new MonthRecord(
            tokens[0],
            Boolean.parseBoolean(tokens[1]),
            Integer.parseInt(tokens[2]),
            Double.parseDouble(tokens[3])
        );
    }
    /*    void printReport() {
        if (monthlyReport == null) {
            System.out.println("Отчет не считан");
        }

        System.out.println("Отчет");
        for (MonthRecord record : reportList.records) {
            System.out.println(record.name + ", " + record.expense + " доход или трата, " + record.quantity + " шт., " + (record.quantity * record.unit_price) + " руб.");
        }
        System.out.println("Итого: " + reportList.calcTotalPrice() + " руб.");
    }
 */



}