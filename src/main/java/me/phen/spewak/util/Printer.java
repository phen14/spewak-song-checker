// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.util;

import static me.phen.spewak.util.Colors.*;

/**
 * Print text in living color.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
public class Printer {
    /**
     * Print the text in green with a prefix of "OK: ".
     *
     * @param text The text to print.
     */
    public static void printSuccess(String text) {
        System.out.println(GREEN + "OK: " + text + RESET);
    }

    /**
     * Print the text in red with a prefix of "Failure: ".
     *
     * @param text The text to print.
     */
    public static void printFailed(String text) {
        System.out.println(RED + "Failure: " + text + RESET);
    }

    /**
     * Print the text in bold white.
     *
     * @param text The text to print.
     */
    public static void printBold(String text) {
        System.out.println(WHITE_BOLD_BRIGHT + text + RESET);
    }

    /**
     * Print the text in bold yellow with a prefix of "Warning: ".
     *
     * @param text The text to print.
     */
    public static void printWarning(String text) {
        System.out.println(YELLOW_BOLD_BRIGHT + "Warning: " + text + RESET);
    }

    /**
     * Print the text in bold red with a prefix of "Error: ".
     *
     * @param text The text to print.
     */
    public static void printError(String text) {
        System.out.println(RED_BOLD_BRIGHT + "Error: " + text + RESET);
    }

}
