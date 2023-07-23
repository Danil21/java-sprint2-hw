import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class MonthlyReport_for_Test {

    ArrayList<Sale> sales = new ArrayList<>();

        public MonthlyReport(String path) {
            String content = readFileContentsOrNull(path);
            String[] lines = content.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i]; //item_name,is_expense,quantity,sum_of_one
                String[] parts = line.split(",");
                String title = parts[0]; //item_name
                boolean isExpense = Boolean.parseBoolean(parts[1]); //это расходы. True or false
                int quantity = Integer.parseInt(parts[2]); //количество
                int sumOfOne = Integer.parseInt(parts[3]); //сумма одного
    
    
                Sale sale = new Sale(title, isExpense,quantity, sumOfOne);
                sales.add(sale);
    
    
            }
        }
        
        FileReader fileReader = new FileReader();

        HashMap<Integer,String> MonthlyReport = new HashMap<>();

    
         ArrayList<String> linesAll = fileReader.readFileContents("m.202101.csv");


         String getTopProduct() {
            HashMap<String, Integer> freqs = new HashMap<>();
            for (Sale sale : sales) {
                if (sale.isExpense = false) {
                    freqs.put(sale.title,freqs.getOrDefault(sale.title,0) + sale.quantity * sale.sumOfOne );
                }
            }

            String maxTitle = null;
            for (String title : freqs.keySet()) {
                if (maxTitle == null) {
                    maxTitle = title;
                    continue;
                }
                if (freqs.get(maxTitle) < freqs.get(title)) {
                    maxTitle = title;
                }
            }
            return maxTitle;
        }
    
    
         Integer getTopProductChost() {
            HashMap<String, Integer> freqs = new HashMap<>();
            for (Sale sale : sales) {   
                    freqs.put(sale.title, freqs.getOrDefault(sale.title, 0) + sale.quantity * sale.sumOfOne);
            }
    
    
            String maxTitle = null;
            for (String title : freqs.keySet()) {
                if (maxTitle == null) {
                    maxTitle = title;
                    continue;
                }
                if (freqs.get(maxTitle) < freqs.get(title)) {
                    maxTitle = title;
                }
            }
            int maxChost = freqs.get(maxTitle);
            return maxChost;
        }
    
        String readFileContentsOrNull (String path) {
            try {
                return Files.readString(Path.of(path));
            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
                return null;
            }
    
    
        }
    }