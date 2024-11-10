// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules.impl

import me.phen.spewak.rules.Rule
import spock.lang.Shared
import spock.lang.Specification

/**
 * Test the "Periods" rule.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
class PeriodsSpec extends Specification {
    @Shared Rule rule = new Periods();

    def check(String input, boolean shouldPass) {
        when:
        boolean passed = rule.check(input, true);

        then:
        passed == shouldPass;

        where:
        input           | shouldPass
        ''              | true
        '.'             | true
        '..'            | false
        '.............' | false
        'Mr.'           | true
        'Mr. Mr.'       | false
        'Mr. Mister'    | true
    }
}
