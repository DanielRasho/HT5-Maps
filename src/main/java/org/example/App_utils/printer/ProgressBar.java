package org.example.App_utils.printer;

/**
 * Methods for printing progress bar like:
 * Attribute [█████ ...]
 */
public class ProgressBar {

    /**
     * Get a progress bar to console.
     * @param name name which will precede the progress bar.
     * @param total Total number of things;
     * @param done How many of the total, is done;
     * @param length The length of the bar in characters.
     * @param barColor Color of the bar.
     * @return
     */
    public static String getProgressBar(String name, int total, int done, int length, AnsiColors barColor){
        if (total <= 0)
            throw new IllegalArgumentException("total must be bigger than 0.");

        String doneChar = "█";
        String emptyChar = "·";
        int percentage;

        // Calculating which percentage of the bar will be fill.
        if(done <= 0)
            percentage = 0;
        else if (done >= total)
            percentage = length;
        else
            percentage = (done * length)/total;

        // Generating bar.
        String bar = name + " "
                + "["
                + barColor.getCode()
                + (doneChar.repeat(percentage))
                + AnsiColors.RESET.getCode()
                + emptyChar.repeat(length - percentage)
                + "]";

        return bar;
    }

    /**
     * Prints a progress bar to console.
     * @param name name which will precede the progress bar.
     * @param total Total number of things;
     * @param done How many of the total, is done;
     * @param length The length of teh bar in Characters.
     * @param barColor Color of the bar.
     */
    public static void progressBar(String name, int total, int done, int length,AnsiColors barColor){
        Printer.println(getProgressBar(name, total, done, length,barColor));
    }
}
