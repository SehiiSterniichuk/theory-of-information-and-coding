package lab2;

import lab2.algorithm.Coder;
import lab2.algorithm.Decoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {

    @Test
    void decode() {
        String input = "Serhii";
        var coder = new Coder(input);
        String codedText = coder.getCodedText();
        var decoder = new Decoder();
        String decodedText = decoder.decode(codedText, coder.getRoot());
        assertEquals(input, decodedText);
    }

    @Test
    void decode2() {
        String input = "Serweflkfwlkjefajhegaiugrwe5g48er6g46ae8r4h6aer54ge1g3a54r48re4ghii";
        var coder = new Coder(input);
        String codedText = coder.getCodedText();
        var decoder = new Decoder();
        String decodedText = decoder.decode(codedText, coder.getRoot());
        assertEquals(input, decodedText);
    }
}