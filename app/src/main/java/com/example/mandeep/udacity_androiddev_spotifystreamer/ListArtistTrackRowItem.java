package com.example.mandeep.udacity_androiddev_spotifystreamer;

/**
 * Created by Mandeep on 7/4/2015.
 */
public class ListArtistTrackRowItem {
    public String imageURL;
    public String trackName;
    public String albumName;

    public ListArtistTrackRowItem(String imageURL, String trackName, String albumName)
    {
        this.imageURL=imageURL;
        this.trackName=trackName;
        this.albumName=albumName;
    }

}
