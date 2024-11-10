package me.phen.spewak.spotify;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author Patrick W. Henstebeck
 * @since 2024 November 09 Saturday
 */
public class Spotify {
    private static final String CONFIG_PATH = "spotify.config";

    private static final String CLIENT_ID_KEY = "client.id";
    private static final String CLIENT_SECRET_KEY = "client.secret";

    private final SpotifyApi spotifyApi;

    /**
     * Initialize the Spotify facade.
     */
    public Spotify() throws IOException {
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(CONFIG_PATH);
        props.load(stream);

        String clientId = props.getProperty(CLIENT_ID_KEY);
        String clientSecret = props.getProperty(CLIENT_SECRET_KEY);

        spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(URI.create("espn.com"))
                .build();

        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();

        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Get the Spotify Track.
     *
     * @param id Spotify track Id.
     * @return Spotify Track object.
     * @throws IOException
     * @throws ParseException
     * @throws SpotifyWebApiException
     */
    public Track getSong(String id) throws IOException, ParseException, SpotifyWebApiException {
        return spotifyApi.getTrack(id).build().execute();
    }

    /**
     * Extract the track Id from a track URL.
     *
     * @param url URL to a Spotify track.
     * @param verbose Print results of each individual rule check.
     * @return Track Id
     */
    public static String extractId(String url, boolean verbose) {
        int idStart = url.indexOf("/track/");
        if (idStart == -1) {
            return null;
        }

        idStart += 7; // Add length of what we were searching for.

        int idEnd = url.indexOf("?si");
        String id = (idEnd == -1) ? url.substring(idStart) : url.substring(idStart, idEnd);
        if (verbose) {
            System.out.println("Track ID: " + id);
        }
        return id;
    }

    /**
     * Get the title field from the Track information.
     *
     * @param track Spotify track.
     * @return Title field.
     */
    public static String getTitle(Track track) {
        return track.getName();
    }

    /**
     * Get the artist field from the Track information.
     *
     * @param track Spotify track.
     * @return Artist field.
     */
    public static String getArtist(Track track) {
        return Arrays.stream(track.getArtists())
                .map(ArtistSimplified::getName)
                .collect(Collectors.joining(", "));
    }
}
