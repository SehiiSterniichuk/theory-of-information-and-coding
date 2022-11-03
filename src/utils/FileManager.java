package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public File byteWrite(String fileName, String text) {
        File file = new File(fileName);
        return byteWrite(file, text);
    }

    public File byteWrite(File file, String text) {
        byte[] bytes = text.getBytes();
        return byteWrite(file, bytes);
    }

    public File byteWrite(File file, byte[] array) {
        try (var outputStream = new FileOutputStream(file)) {
            for (var i : array) {
                outputStream.write(i);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return file;
    }

    public File createAndWrite(String fileName, String text) {
        File file = new File(fileName);
        return createAndWrite(file, text);
    }

    public File createAndWrite(File file, String text) {
        try (var myWriter = new FileWriter(file)) {
            myWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
