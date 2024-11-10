// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules;

/**
 * Checker Rule
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
public interface Rule {

    /**
     * Run the rule against the given input.
     *
     * @param input The data necessary to run the rule.
     * @param verbose Print out the results of running the rule.
     * @return If the input passes the rule or not.
     */
    boolean check(String input, boolean verbose);
}
