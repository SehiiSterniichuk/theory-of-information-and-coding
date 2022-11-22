package lab4.view;

import lab2.view.FileSize;

import java.util.List;

public record ResultLab4(String source, FileSize sizeOfFile,
                         byte[] code,
                         byte[] hammingCode,
                         byte[] hammingDeCode,
                         boolean decodedIsCorrectByValidator,
                         boolean reallyIsCorrectDecoding,
                         List<Integer> foundErrors,
                         List<Integer> realErrors){
}
