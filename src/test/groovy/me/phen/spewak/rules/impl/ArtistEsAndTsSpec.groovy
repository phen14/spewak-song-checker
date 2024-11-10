// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules.impl

import me.phen.spewak.rules.Rule
import spock.lang.Shared
import spock.lang.Specification

/**
 * Test the "Artist E's and T's" Rule.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
class ArtistEsAndTsSpec extends Specification {
    @Shared Rule rule = new ArtistEsAndTs();

    def check(String input, boolean shouldPass) {
        when:
        boolean passed = rule.check(input, true);

        then:
        passed == shouldPass;

        where:
        input          | shouldPass
        ''              | true
        'e'             | false
        't'             | false
        'E'             | false
        'T'             | false
        'et'            | false
        'te'            | false
        'ee'            | true
        'tt'            | true
        'etet'          | true
        'eett'          | true
        'ttee'          | true
        'ett'           | false
        'tee'           | false
        'eet'           | false
        'tee'           | false
        'e t '          | false
        'The Beatles'   | true
        'Bee Gees'      | true
        'The Bee Gees'  | false
        'é'             | false
        'éE'            | true
    }
}
