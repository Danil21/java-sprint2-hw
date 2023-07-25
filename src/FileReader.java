import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.File;

public class FileReader {

   static final String pathDir = "./resources/";

   ArrayList<String> readFileContents(String nameFile) {
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(pathDir + nameFile + ".csv")));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return new ArrayList<>();
        }
    }

       String readFileContentsString(String nameFile) {
        try {
            return Files.readString(Path.of(pathDir + nameFile + ".csv"));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return null;
        }
    }

    // {"m.202101.csv","m.202102.csv","m.202103.csv"}

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
 