package model;

import java.util.ArrayList;
import java.util.Collections;

public class Artist implements Comparable<Artist> {
    private final Integer artistId;
    private final String artistName;
    private final Event artistEvent;

    public static ArrayList<Artist> artistArray = new ArrayList<>();

    public Artist(Integer artistId, String artistName, Event artistEvent) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistEvent = artistEvent;

        Collections.sort(artistArray);
    }

    public static void listArtists() {
        System.out.println(Color.GREEN + "\nArtists in system: " + Color.RESET);
        for (Artist artist : Artist.artistArray) System.out.println("Id: " + Color.YELLOW + artist.artistId + Color.RESET + "\tName: " + Color.PURPLE + artist.artistName + Color.RESET);
    }

    public static ArrayList<Artist> getArtistByEvent(Event event) {
        ArrayList<Artist> artistList = new ArrayList<>();
        for (Artist artist : artistArray) if (artist.artistEvent.getEventId().equals(event.getEventId())) artistList.add(artist);
        return artistList;
    }

    public Integer getArtistId() {
        return this.artistId;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public Event getArtistEvent() {
        return this.artistEvent;
    }

    @Override
    public int compareTo(Artist other) {
        return artistId.compareTo(other.artistId);
    }
}
