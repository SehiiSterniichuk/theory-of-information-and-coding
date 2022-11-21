package utils.file;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileManager {

    @SuppressWarnings("unused")
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
            throw new RuntimeException("Archiving of file was failed");
        }
        return zipFileName;
    }

    @SuppressWarnings("unused")
    public File archiveFileToZip(String sourceFileName, String zipFileName) {
        return archiveFileToZip(new File(sourceFileName), new File(zipFileName));
    }

    @SuppressWarnings("unused")
    public File archiveFileToZip(String sourceFileName, File zipFile) {
        return archiveFileToZip(new File(sourceFileName),zipFile);
    }

    public File archiveFileToZip(File sourceFile, String zipFileName) {
        return archiveFileToZip(sourceFile, new File(zipFileName));
    }
}
