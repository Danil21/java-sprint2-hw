import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.File;

public class FileReader {

   static final String pathDir = "./resources/";

    ArrayList<String> readFileContents(String purposeFile) {
        try {
            String monthFile = null;
            for(int i=1; i<4; i++){ monthFile = "20210" + i;}
            return new ArrayList<>(Files.readAllLines(Path.of(pathDir + purposeFile + monthFile + ".csv")));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return new ArrayList<>();
        }
    }

    // {"m.202101.csv","m.202102.csv","m.202103.csv"}

    String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    String getPurposeFileName(){ // доп метод который определяет пренадлежньсть отчета, если типов отчетов будет больше чем годовой и месячный
        String typeReport = "month";
        if(getNameListFileReport().toString().charAt(0) == 'y'){ typeReport = "year";}
        return typeReport;
    }

    ArrayList<Character> getNameListFileReport(){
        File[] files = new File(pathDir).listFiles();
        ArrayList<String> listFileName = new ArrayList<>();

        if (files != null) {
            for(File file : files) {
                listFileName.add(file.toString());
            }
        } else { System.out.println("Невзможно прочитать файлы"); return null;}

        ArrayList<Character> charsListFile = new ArrayList<>();

        for (int i = 0; i < listFileName.toString().length(); i++) {
            charsListFile.add(listFileName.toString().charAt(i));
        }
        
        return charsListFile;
    }

   
}
 