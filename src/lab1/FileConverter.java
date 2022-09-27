package lab1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileConverter {
    private final String fileName;
    private final String pathToDataFolder = "src/data/";

    public FileConverter(String fileName) {
        this.fileName = fileName;
        File file = new File(pathToDataFolder + fileName);
        if(!file.exists()){
            throw new RuntimeException("File doesn't exist");
        }
        if(!file.isFile() || !file.getName().contains(".txt")){
            throw new RuntimeException("It is not a correct file");
        }
    }

    public String getFileAsString(){
        try {
            return Files.readString(Paths.get(pathToDataFolder + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
