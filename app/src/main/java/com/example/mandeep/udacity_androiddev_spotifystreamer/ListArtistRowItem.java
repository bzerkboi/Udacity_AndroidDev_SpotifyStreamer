package com.example.mandeep.udacity_androiddev_spotifystreamer;

/**
 * Created by Mandeep on 7/4/2015.
 */
public class ListArtistRowItem {

    public String imageURL;
    public String artistName;
    public String artistId;

    public ListArtistRowItem(String imageURL, String artistName, String artistId)
    {
        this.imageURL=imageURL;
        this.artistName=artistName;
        this.artistId=artistId;
    }
}
