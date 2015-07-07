package com.example.mandeep.udacity_androiddev_spotifystreamer;

import java.util.List;

/**
 * Created by Mandeep on 7/4/2015.
 */
public class Spotify_ArtistTopTenTracks {
    public List<Tracks> tracks;
}

class Tracks {
    public Album album;
    public List<ArtistsTopTen> artists;
    public String id;
    public String name;
}

class Album {
    public String id;
    public List<ArtistAlbumImages> images;
    public String name;
}

class ArtistAlbumImages {
    public String url;
}

class ArtistsTopTen {
    public String id;
    public String name;

}
