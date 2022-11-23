package lab4.view;

import dnl.utils.text.table.TextTable;

import java.util.List;

public class ResultTableLab4 {
    private static final String[] heading = {"Назва", "Розмір", "Розшифровано",
            "Розшифровано:Гемінг", "Помилки", "Знайдені помилки"};
    private final TextTable table;

    public ResultTableLab4(final List<ResultLab4> results) {
        final int col = results.size();
        final int row = heading.length;
        String[][] data = new String[col][row];
        for (int i = 0; i < col; i++) {
            final var dataRow = data[i];
            final var result = results.get(i);
            dataRow[0] = result.source();
            dataRow[1] = result.sizeOfFile().toString();
            dataRow[2] = String.valueOf(result.reallyIsCorrectDecoding());
            dataRow[3] = String.valueOf(result.decodedIsCorrectByValidator());
            dataRow[4] = result.realErrors().toString();
            dataRow[5] = result.foundErrors().toString();
        }
        table = new TextTable(heading, data);
    }

    public void print() {
        table.printTable();
    }
}
