// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.spewak;

import me.phen.spewak.rules.Checker;
import me.phen.spewak.spotify.Spotify;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static me.phen.spewak.util.Printer.*;

/**
 * Run the program.
 *
 * @author Patrick W. Henstebeck
 * @since 2024 November 04 Monday
 */
public class Runner {
    private final Checker checker = new Checker();
    private final Spotify spotify;

    /**
     * Run the thing.
     *
     * @param args Command-line arguments.  (None expected.)
     */
    public static void main(String[] args) {
        try {
            Runner runner = new Runner();
            runner.run();
        } catch (Exception e) {
            System.err.println("Oops. " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    /**
     * Initialize the runner.
     *
     * @throws IOException
     */
    private Runner() throws IOException {
        spotify = new Spotify();
    }

    /**
     * Repeatedly read console input to get a song or file to check.
     *
     * @throws IOException
     */
    private void run() throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Thread.sleep(2000); // Let the SLF4J warnings in the IntelliJ console pass.

        printBold("Welcome to the Spewak Round Song Checker!");
        System.out.println("This will check if your song(s) match the rules related to the artist and song names.");
        printWarning("It does not check the release date or if it was covered by the proper YouTuber!");

        while (true) {
            System.out.println("\n\nEnter one of the following:");
            System.out.println(" - A link to a Spotify track, including the \"https://\".");
            System.out.println(" - An artist and title, in the format of \"[Artist] ~ [Title]\".");
            System.out.println(" - A path to a file ending in \".txt\" containing one of the above options on each line.");
            System.out.println(" - \"exit\" or \"quit\" to end.");
            String input = br.readLine();

            if ("exit".equalsIgnoreCase(input) || "quit".equalsIgnoreCase(input)) {
                System.out.println("Good bye.");
                return;
            }

            // File
            if (input.endsWith(".txt")) {
                handleFileInput(input);
                continue;
            }

            // Spotify Link
            if (input.startsWith("http://") || input.startsWith("https://")) {
                handleSpotifyUrlInput(input, true);
                continue;
            }

            // "[Artist] - [Title]"
            if (input.contains(" ~ ")) {
                handleArtistTildeTitleInput(input, true);
                continue;
            }

            printError("Invalid input.  Try again.");
        }
    }

    /**
     * Read a file and process each line.
     *
     * @param input Path to the file.
     */
    private void handleFileInput(String input) {
        try {
            Path path = Paths.get(input);
            Files.lines(path).forEach(this::handleFileLine);
        } catch (IOException | SecurityException e) {
            printError("Could not read file " + input + ": " + e.getMessage());
        }
    }

    /**
     * Process a line from a file.
     *
     * @param line The text from one line in a file.
     */
    private void handleFileLine(String line) {
        // Spotify Link
        if (line.startsWith("http://") || line.startsWith("https://")) {
            handleSpotifyUrlInput(line, false);
            return;
        }

        // "[Artist] - [Title]"
        if (line.contains(" ~ ")) {
            handleArtistTildeTitleInput(line, false);
            return;
        }

        printError("Invalid line: [" + line + "].  Try again.");
    }

    /**
     * Load song data from a Spotify URL.
     *
     * @param input URL to a Spotify track.
     * @param verbose Print results of each individual rule check.
     */
    private void handleSpotifyUrlInput(String input, boolean verbose) {
        String id = Spotify.extractId(input, verbose);
        if (id == null) {
            printError("Invalid Spotify URL or track not found: [" + input + "].");
            return;
        }

        try {
            Track track = spotify.getSong(id);
            String artist = Spotify.getArtist(track);
            String title = Spotify.getTitle(track);

            checker.check(artist, title, verbose);
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            printError("Error getting Spotify track: " + e.getMessage());
        }
    }

    /**
     * Test a song that was directly inputted.
     *
     * @param input The artist and title of a song, separated by a space, tilde, space.
     * @param verbose Print results of each individual rule check.
     */
    public void handleArtistTildeTitleInput(String input, boolean verbose) {
        String[] splits = input.split(" ~ ");
        if (splits.length != 2) {
            printError("Too many tildes.");
            return;
        }

        checker.check(splits[0], splits[1], verbose);
    }
}
