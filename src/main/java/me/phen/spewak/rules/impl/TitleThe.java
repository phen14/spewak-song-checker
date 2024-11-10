// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules.impl;

import me.phen.spewak.rules.Rule;

import static me.phen.spewak.util.Printer.printFailed;
import static me.phen.spewak.util.Printer.printSuccess;

/**
 * RULE: The name of the song (as listed on Spotify) must not have the word "the" in it.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
public class TitleThe implements Rule {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(String input, boolean verbose) {
        String lowerInput = input.toLowerCase();

        if (lowerInput.equals("the") || lowerInput.startsWith("the ") || lowerInput.endsWith(" the") || lowerInput.contains(" the ")) {
            if (verbose) {
                printFailed("Title contains the word 'the'.");
            }
            return false;
        }

        if (verbose) {
            printSuccess("Title does not contain the word 'the'.");
        }
        return true;
    }
}
