package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileConverter {
    private final String fileName;
    private final String pathToDataFolder = "src/resources/";
    private final File file;

    public FileConverter(String fileName) {
        this.fileName = fileName;
        file = new File(pathToDataFolder + fileName);
        if (!file.exists()) {
            throw new RuntimeException("File doesn't exist");
        }
        if (!file.isFile() || !file.getName().contains(".txt")) {
            throw new RuntimeException("It is not a correct file");
        }
    }

    public String getFileAsString() {
        return getFileAsString(pathToDataFolder + fileName);
    }

    public static String getFileAsString(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFileAsString(File file) {
        try {
            return Files.readString(Paths.get(file.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFile() {
        return file;
    }
}
