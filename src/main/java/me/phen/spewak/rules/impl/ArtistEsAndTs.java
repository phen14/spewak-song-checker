// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak.rules.impl;

import me.phen.spewak.rules.Rule;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static me.phen.spewak.util.Printer.*;

/**
 * RULE: The artist (as listed on Spotify) must either not have an "e" in their name, or have multiple e's in their name
 * AND the artist (as listed on Spotify) must either not have an "t" in their name, or have multiple t's in their name.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
public class ArtistEsAndTs implements Rule {
    private static final Set<Character> E = new HashSet<>(Arrays.asList('E', 'e'));
    private static final Set<Character> T = new HashSet<>(Arrays.asList('T', 't'));

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(String artist, boolean verbose) {
        boolean valid = true;
        String normalizedArtist = Normalizer.normalize(artist, Normalizer.Form.NFD);

        int eCount = 0;
        int tCount = 0;
        for (char c : normalizedArtist.toCharArray()) {
            if (E.contains(c)) {
                ++eCount;
            } else if (T.contains(c)) {
                ++tCount;
            }
        }

        if (eCount == 1) {
            if (verbose) {
                printFailed("Artist has exactly one 'e'.");
            }
            valid = false;
        } else {
            if (verbose) {
                printSuccess("Artist has valid number of 'e's.");
            }
        }

        if (tCount == 1) {
            if (verbose) {
                printFailed("Artist has exactly one 't'.");
            }
            valid = false;
        } else {
            if (verbose) {
                printSuccess("Artist has valid number of 't's.");
            }
        }

        return valid;
    }
}
