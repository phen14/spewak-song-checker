// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules.impl;

import me.phen.spewak.rules.Rule;

import static me.phen.spewak.util.Printer.printFailed;
import static me.phen.spewak.util.Printer.printSuccess;

/**
 * RULE: The artist and the name of the song must have a total of at most one (1) period between both fields.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
public class Periods implements Rule {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(String input, boolean verbose) {
        int periodCount = 0;
        for (char c : input.toCharArray()) {
            if (c == '.') {
                ++periodCount;
            }
        }

        if (periodCount > 1) {
            if (verbose) {
                printFailed("Artist and Title combined contain " + periodCount + "periods.");
            }
            return false;
        }

        if (verbose) {
            printSuccess("Artist and Title contain a valid number of periods.");
        }
        return true;
    }
}
