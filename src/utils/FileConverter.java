package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

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
        if (!file.isFile()) {
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
        return getFileAsString(file.getPath());
    }

    public static byte[] getFileAsByteArray(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getFileAsByteArray(File file) {
        return getFileAsByteArray(file.getPath());
    }

    public static String getBinaryFileAsString(String path) {
        var array = getFileAsByteArray(path);
        return Base64.getEncoder().encodeToString(array);
    }

    public static String getBinaryFileAsString(File file) {
        return getBinaryFileAsString(file.getPath());
    }

    public File getFile() {
        return file;
    }
}
