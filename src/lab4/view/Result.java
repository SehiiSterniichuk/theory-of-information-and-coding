package lab4.view;

import lab2.view.FileSize;

public record Result (String source, FileSize sizeOfFile, int insertedErrors, boolean decodedIsCorrect){
}
