package calculator.mathmate;

public class Concatinator {

    private static int lineSize;
    static StringBuilder display = new StringBuilder(200);
    public static void print(String text, int width)
    {
        int padding = width - text.length();
        while (--padding >= 0) display.append(" ");
        display.append(text);

        lineSize += width;
    }

    /**
     * Print an integer value right-aligned in the column.
     * @param value the value to print
     * @param width the column width
     */
    public static void print(int value, int width)
    {
        print(Integer.toString(value), width);
    }

    /**
     * Print a float value right-aligned in the column.
     * @param value the value to print
     */
    public static void print(float value, int width)
    {
        print(Float.toString(value), width);
    }

    /**
     * Print a double value right-aligned in the column.
     * @param value the value to print
     * @param width the column width
     */
    public static void print(double value, int width)
    {
        print(Double.toString(value), width);
    }

    /**
     * Print a line.
     */
    public static void println()
    {
        display.append("\n");
        lineSize = 0;
    }

    /**
     * Print an underline.
     */
    public static void underline()
    {
        display.append("\n");
        for (int i = 0; i < lineSize; ++i) display.append("-");
        display.append("\n");
        lineSize = 0;
    }

}
