// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules.impl

import me.phen.spewak.rules.Rule
import spock.lang.Shared
import spock.lang.Specification

/**
 * Test the "AlphaNumericSpaces" rule.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
class AlphaNumericSpacesSpec extends Specification {

    @Shared Rule rule = new AlphaNumericSpaces("Field");

    def check(String input, boolean shouldPass) {
        when:
        boolean passed = rule.check(input, true);

        then:
        passed == shouldPass;

        where:
        input | shouldPass
        ''              | false // We do require one character
        'ab'            | true
        '12'            | true
        'a1b2'          | true
        'a1 b2'         | true
        'a1 & b2'       | false
        'a1, b2'        | false
        'a1 - b2'       | false
        'a1 (b2)'       | false
        'a1.b2'         | true
        'a1..b2'        | true // Caught by a different rule
    }
}
