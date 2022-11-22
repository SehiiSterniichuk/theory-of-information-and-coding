package lab4.view;

import lab2.view.FileSize;

import java.util.List;

public record ResultLab4(String source, FileSize sizeOfFile, boolean decodedIsCorrect,
                         List<Integer> foundErrors,
                         List<Integer> realErrors){
}
