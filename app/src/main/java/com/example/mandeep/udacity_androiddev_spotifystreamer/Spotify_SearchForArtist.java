package com.example.mandeep.udacity_androiddev_spotifystreamer;

import java.util.List;

/**
 * Created by Mandeep on 7/4/2015.
 */
public class Spotify_SearchForArtist {

    public Artists artists;
}

class Artists
{
    public List<Items> items;
}

class Items {
    public List<Images> images;
    public String name;
    public String id;
}

class Images{
    public String url;
}

