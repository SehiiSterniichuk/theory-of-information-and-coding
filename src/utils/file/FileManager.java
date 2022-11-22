package utils.file;

import java.io.*;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileManager {

    public void byteWrite(File file, byte[] array) {
        try (var outputStream = new FileOutputStream(file)) {
            for (var i : array) {
                outputStream.write(i);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public File write(String fileName, String text) {
        File file = new File(fileName);
        return write(file, text);
    }

    public File write(File file, String text) {
        try (var myWriter = new FileWriter(file)) {
            myWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public void write(String fileName, byte[] array) {
        File file = new File(fileName);
        write(file, array);
    }

    public void write(File file, byte[] array) {
        try {
            java.nio.file.Files.write(Path.of(file.getPath()), array);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File archiveFileToZip(File fileToZip, File zipFileName) {
        try (FileOutputStream fos = new FileOutputStream(zipFileName);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            try (FileInputStream fis = new FileInputStream(fileToZip)) {
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Archiving of file was failed", e);
        }
        return zipFileName;
    }

    public File archiveFileToZip(File sourceFile, String zipFileName) {
        return archiveFileToZip(sourceFile, new File(zipFileName));
    }

    public byte[] binaryStringToBytes(byte[] array) {
        final int length = array.length;
        int sizeOfArray = length / 8;
        if (length % 8 != 0) {
            sizeOfArray++;
        }
        byte[] data = new byte[sizeOfArray];
        for (int i = 0; i < length; i++) {
            byte c = array[i];
            if (c == '1') {
                data[i >> 3] |= 0x80 >> (i & 0x7);
            } else if (c != '0') {
                throw new IllegalArgumentException("Invalid char in binary string");
            }
        }
        return data;
    }
}
