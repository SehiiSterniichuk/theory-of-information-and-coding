package lab2.view;

import dnl.utils.text.table.TextTable;

import java.util.List;

public class ResultTable {
//    private static final String[] heading = {"Name", "Original:Size", "Original:Entropy",
//            "Shannon:Size", "Shannon:Compression", "Shannon:Saved",
//            "Zip:Size", "Zip:Saved"};
    private static final String[] heading = {"Назва", "Оригінал:Розмір", "Оригінал:Ентропія",
            "Програма:Розмір", "Програма:Коеф. стиснення", "Програма:Збережено",
            "Zip:Розмір", "Zip:Збережено"};
    private final TextTable table;

    public ResultTable(final List<Result> results) {
        final int col = results.size();
        final int row = heading.length;
        String[][] data = new String[col][row];
        for (int i = 0; i < col; i++) {
            final var dataRow = data[i];
            final var result = results.get(i);
            dataRow[0] = result.name();
            dataRow[1] = result.originalSize().toString();
            dataRow[2] = result.entropyString();
            dataRow[3] = result.compressedSize().toString();
            dataRow[4] = result.coefficientOfCompressionString();
            dataRow[5] = result.shannonSaveSpaceRatioString();
            dataRow[6] = result.zipSize().toString();
            dataRow[7] = result.zipSaveSpaceRatioString();
        }
        table = new TextTable(heading, data);
    }

    public void print() {
        table.printTable();
    }
}
