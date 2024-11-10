// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules.impl;

import me.phen.spewak.rules.Rule;

import static me.phen.spewak.util.Printer.printFailed;
import static me.phen.spewak.util.Printer.printSuccess;

/**
 * RULE: Both the full name of the artist and the name of the song (as listed on Spotify) each must be between four and
 * 35 characters long.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
public class Length implements Rule {
    String fieldName;

    /**
     * Create the rule.
     *
     * @param fieldName Name of the field for use in printing the results.
     */
    public Length(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(String input, boolean verbose) {
        if (input.length() < 4 || input.length() > 35) {
            if (verbose) {
                printFailed("Length of " + fieldName + " (" + input.length() + ") is not between 4 and 35 characters long.");
            }
            return false;
        }

        if (verbose) {
            printSuccess("Length of " + fieldName + " (" + input.length() + ") is between 4 and 35 characters long.");
        }
        return true;
    }
}
