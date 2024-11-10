// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules;

import me.phen.spewak.rules.impl.AlphaNumericSpaces;
import me.phen.spewak.rules.impl.ArtistEsAndTs;
import me.phen.spewak.rules.impl.Length;
import me.phen.spewak.rules.impl.Periods;
import me.phen.spewak.rules.impl.TitleThe;

import java.util.Arrays;
import java.util.List;

import static me.phen.spewak.util.Printer.*;

/**
 * Check if a song passes all the rules.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
public class Checker {

    private static final String ARTIST_FIELD_LABEL = "Artist";
    private static final String TITLE_FIELD_LABEL = "Title";

    private static final List<Rule> ARTIST_RULES = Arrays.asList(
            new AlphaNumericSpaces(ARTIST_FIELD_LABEL),
            new ArtistEsAndTs(),
            new Length(ARTIST_FIELD_LABEL)
    );
    private static final List<Rule> TITLE_RULES = Arrays.asList(
            new AlphaNumericSpaces(TITLE_FIELD_LABEL),
            new TitleThe(),
            new Length(TITLE_FIELD_LABEL)
    );
    private static final List<Rule> COMBINED_RULES = Arrays.asList(
            new Periods()
    );

    /**
     * Run the rules.
     *
     * @param artist Song artist.
     * @param title Song title.
     * @param verbose Print the results of each individual check?
     * @return Whether the song is valid.
     */
    public boolean check(String artist, String title, boolean verbose) {
        if (verbose) {
            System.out.println("Artist: [" + artist + "].  Title:[" + title + "].");
        }

        boolean valid = true;
        for (Rule rule : ARTIST_RULES) {
            valid = rule.check(artist, verbose) && valid;
        }
        for (Rule rule : TITLE_RULES) {
            valid = rule.check(title, verbose) && valid;
        }
        for (Rule rule : COMBINED_RULES) {
            valid = rule.check(artist + title, verbose) && valid;
        }

        if (valid) {
            printSuccess("Artist: [" + artist + "].  Title:[" + title + "].");
        } else {
            printFailed("Artist: [" + artist + "].  Title:[" + title + "].");
        }

        return valid;
    }
}
