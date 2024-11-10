// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules.impl;

import me.phen.spewak.rules.Rule;

import static me.phen.spewak.util.Printer.printFailed;
import static me.phen.spewak.util.Printer.printSuccess;

/**
 * RULE: Both the artist and the name of the song (as listed on Spotify) must consist only of letters, numbers, spaces,
 * and a total of at most one (1) period between both fields.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
public class AlphaNumericSpaces implements Rule {
    String fieldName;

    /**
     * Create the rule.
     *
     * @param fieldName Name of the field for use in printing the results.
     */
    public AlphaNumericSpaces(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(String input, boolean verbose) {
        boolean alphaNumeric = input.matches("[A-Za-z0-9\\. ]+");
        if (!alphaNumeric) {
            if (verbose) {
                printFailed(fieldName + " contains characters other than letters, numbers, spaces, or periods.");
            }
            return false;
        }

        if (verbose) {
            printSuccess(fieldName + " contains only letters, numbers, spaces, or periods.");
        }
        return true;
    }
}
