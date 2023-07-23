import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.File;

public class FileReader {

   static final String pathDir = "./resources/";

    ArrayList<String> readFileContents(String fileName) {
      //  String path = "./resources/" + fileName;
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(pathDir + fileName)));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return new ArrayList<>();
        }
    }

    // {"m.202101.csv","m.202102.csv","m.202103.csv"}

    String getPurposeFileName(){
       // char firstChar = getNameListFileReport().toString().charAt(0);
        String typeReport = null;
        if(getNameListFileReport().toString().charAt(0) == 'm'){ typeReport = "month";}
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
 