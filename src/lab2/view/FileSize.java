package lab2.view;

import java.text.DecimalFormat;

public record FileSize(long size) {
    private static final DecimalFormat format = new DecimalFormat("#,##0.#");
    private static final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};

    @Override
    public String toString() {
        if (size <= 0) return "0";
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return format.format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
