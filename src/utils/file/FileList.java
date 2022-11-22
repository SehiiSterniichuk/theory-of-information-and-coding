package utils.file;


public enum FileList {

    CODED_OUTPUT_FILE_NAME("Compressed"),
    CODED_OUTPUT_FILE_AS_TEXT_NAME("CompressedAsText.txt"),
    DECODED_OUTPUT_FILE_NAME("Decompressed.txt"),
    ZIP_FILE_NAME("Zip.zip"),
    PATH_TO_FOLDER("src/resources/"),
    PATH_TO_RESULT("src/resources/result/"),
    TEXT_FORMAT(".txt"),
    CONSOLE_FILE_NAME("Console.txt"),
    PATH_TO_TEXT_FOLDER(PATH_TO_FOLDER + "casual/eng/"),
    PATH_TO_MEDIA_FOLDER(PATH_TO_FOLDER + "differentTypes/");

    private final String path;

    FileList(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return getPath();
    }

    public String add(FileList file) {
        return path + file.path;
    }
}
