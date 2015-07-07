package com.example.mandeep.udacity_androiddev_spotifystreamer;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Mandeep on 7/4/2015.
 */
public interface SpotifyAPI {

    //GET https://api.spotify.com/v1/search
    @GET("/v1/search")
    void SearchForArtist(@Query("q") String artistName,@Query("type") String type, Callback<Spotify_SearchForArtist> callback);

    //GET https://api.spotify.com/v1/artists/{id}/top-tracks
    @GET("/v1/artists/{id}/top-tracks")
    void getTop10Tracks(@Path("id") String id,@Query("country") String country, Callback<Spotify_ArtistTopTenTracks> callback);
}
