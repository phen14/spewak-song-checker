// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules.impl

import me.phen.spewak.rules.Rule
import spock.lang.Shared
import spock.lang.Specification

/**
 * Test the "Length" rule.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
class LengthSpec extends Specification {
    @Shared Rule rule = new Length("Field");

    def check(String input, boolean shouldPass) {
        when:
        boolean passed = rule.check(input, true);

        then:
        passed == shouldPass;

        where:
        input                                   | shouldPass
        ''                                      | false
        '1'                                     | false
        '123'                                   | false
        '1234'                                  | true
        '1234567890'                            | true
        '12345678901234567890'                  | true
        '123456789012345678901234567890'        | true
        '12345678901234567890123456789012345'   | true
        '123456789012345678901234567890123456'  | false
    }
}
