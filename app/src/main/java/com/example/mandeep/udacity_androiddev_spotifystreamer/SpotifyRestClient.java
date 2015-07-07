package com.example.mandeep.udacity_androiddev_spotifystreamer;

import com.google.gson.annotations.SerializedName;

import retrofit.RestAdapter;

/**
 * Created by Mandeep on 7/4/2015.
 */
public class SpotifyRestClient {

    private static SpotifyAPI SPOTIFY_REST_CLIENT;
    private static String ROOT="https://api.spotify.com";
    static{
        setupRestClient();
    }

    private SpotifyRestClient(){}

    public static SpotifyAPI get() {
        return SPOTIFY_REST_CLIENT;
    }

    private static void setupRestClient() {
        RestAdapter builder=new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        SPOTIFY_REST_CLIENT = builder.create(SpotifyAPI.class);
    }

    class RestError {
        @SerializedName("code")
        public int code;
        @SerializedName("error")
        public String error;
        @SerializedName("error_description")
        public String error_description;
    }
}
