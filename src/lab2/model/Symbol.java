package lab2.model;

import java.util.Objects;

public final class Symbol {
    private final String letter;
    private final double probability;
    private final StringBuilder code;

    public Symbol(String letter, double probability) {
        this(letter, probability, "");
    }

    public Symbol(String letter, double probability, String code) {
        this.letter = letter;
        this.probability = probability;
        this.code = new StringBuilder(code);
    }

    public String letter() {
        return letter;
    }

    public double probability() {
        return probability;
    }

    public String code() {
        return code.toString();
    }

    public void add(String i){
        code.append(i);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if(!(obj instanceof Symbol symbol)){
            return false;
        }
        return Objects.equals(this.letter, symbol.letter) &&
                Objects.equals(this.probability, symbol.probability) &&
                Objects.equals(this.code(), symbol.code());
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, probability, code);
    }

    @Override
    public String toString() {
        return "Symbol[" +
                "letter=" + letter + ", " +
                "probability=" + probability + ", " +
                "code=" + code + ']';
    }

}
