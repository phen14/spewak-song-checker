// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules.impl

import me.phen.spewak.rules.Rule
import spock.lang.Shared
import spock.lang.Specification

/**
 * Test the "TitleThe" rule.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
class TitleTheSpec extends Specification {
    @Shared Rule rule = new TitleThe();

    def check(String input, boolean shouldPass) {
        when:
        boolean passed = rule.check(input, true);

        then:
        passed == shouldPass;

        where:
        input           | shouldPass
        ''              | true
        'The'           | false
        'The '          | false
        'the'           | false
        'the '          | false
        ' The'          | false
        ' The '         | false
        ' the'          | false
        ' the '         | false
        ' then '        | true
        'Then '         | true
        ' then'         | true
        'Do the thing'  | false
        'Hello goodbye' | true
    }
}
