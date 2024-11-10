package me.phen.spewak.rules

import spock.lang.Specification

/**
 * Test the Checker class, which is to say, test all the rules at once.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
class CheckerSpec extends Specification {
    private Checker checker = new Checker();

    def testChecker(String artist, String title, boolean shouldPass) {
        when:
        boolean passed = checker.check(artist, title, false);

        then:
        passed == shouldPass;

        where:
        artist              | title                         | shouldPass
        ""                  | ""                            | false // Need at least one character
        "The Beatles"       | "Strawberry Fields Forever"   | true
        "The Bealles"       | "Strawberry Fields Forever"   | false // One 't' in artist.
        "Thy Boatles"       | "Strawberry Fields Forever"   | false // One 'e' in artist.
        "The Beatles"       | "The Long and Winding Road"   | false // "The" in title.
        "The Beatles"       | "P.S. I Love You"             | false // Too many periods.
        "The. Beatles"      | "PS. I Love You"              | false // Too many periods.
        "The Beatles"       | "Money (That's What I Want)"  | false // Invalid characters.
        "Green Day"         | "She"                         | false // Title too short.
        "Bob"               | "A Song of Normal Length"     | false // Artist too short.
        "The Primitive Radio Gods"  | "Standing Outside a Broken Phone Booth with Money in My Hand" | false // Title too long.
        "Marky Mark and the Funky Bunch featuring Loleatta Holloway" | "Good Vibrations"            | false // Artist too long.

    }
}
