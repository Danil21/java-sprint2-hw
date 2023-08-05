import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.File;

public class FileReader {

   ArrayList<String> readFileContents(String pathDir, String nameFile) {
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(pathDir + nameFile + ".csv")));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return new ArrayList<>();
        }
    }

    String getPurposeFileName(){ // доп метод который определяет пренадлежньсть отчета, если типов отчетов будет больше чем годовой и месячный
        String typeReport = "month";
        if(getNameListFileReport().toString().charAt(0) == 'y'){ typeReport = "year";}
        return typeReport;
    }

    ArrayList<Character> getNameListFileReport(){
        File[] files = new File(ReportManager.pathDir).listFiles();
        ArrayList<String> listFileName = new ArrayList<>();

        if (files != null) {
            for(File file : files) {
                listFileName.add(file.toString());
            }
        } else {return null;}

        ArrayList<Character> charsListFile = new ArrayList<>();

        for (int i = 0; i < listFileName.toString().length(); i++) {
            charsListFile.add(listFileName.toString().charAt(i));
        }
        
        return charsListFile;
    }

   
}
 