package com.example.mandeep.udacity_androiddev_spotifystreamer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A placeholder fragment containing a simple view.
 */
public class ArtistTracksActivityFragment extends Fragment {

    private CustomListArtistTrackArrayAdapter mArtistTrackListAdapter;


    public ArtistTracksActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_artist_tracks, container, false);

        //We need to get the intent first
        Intent intent = getActivity().getIntent();
        String artistId;
        String artistName;


        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            artistId= intent.getStringExtra(Intent.EXTRA_TEXT); //Get the artist id out of the intent
            artistName = intent.getStringExtra(Intent.EXTRA_USER);

            ActionBarActivity actionBarActivity= (ActionBarActivity)getActivity();
            actionBarActivity.getSupportActionBar().setSubtitle(artistName);

            //We need to issue another api call here to get the artist tracks
            SpotifyRestClient.get().getTop10Tracks(artistId, "US", new Callback<Spotify_ArtistTopTenTracks>() {
                @Override
                public void success(Spotify_ArtistTopTenTracks spotify_artistTopTenTracks, Response response) {

                    List<ListArtistTrackRowItem> tracks = new ArrayList<ListArtistTrackRowItem>();

                    for(int i=0;i<spotify_artistTopTenTracks.tracks.size();i++)
                    {
                        String trackName;
                        String albumName;
                        String imageURL;

                        if (spotify_artistTopTenTracks.tracks.get(i).album.images.size() > 1) {
                            imageURL = spotify_artistTopTenTracks.tracks.get(i).album.images.get(0).url; //get the image, blocking load
                        } else {
                            imageURL = "https://play.spotify.edgekey.net/client/adc3363/images/logo.png";
                        }

                        trackName=spotify_artistTopTenTracks.tracks.get(i).name;
                        albumName = spotify_artistTopTenTracks.tracks.get(i).album.name;
                        tracks.add(new ListArtistTrackRowItem(imageURL, trackName, albumName));
                    }

                    ListView listView = (ListView)rootView.findViewById(R.id.list_view_artist_tracks);
                    mArtistTrackListAdapter = new CustomListArtistTrackArrayAdapter(
                            getActivity(),
                            R.layout.list_item_artist_track,
                            tracks);

                    listView.setAdapter(mArtistTrackListAdapter);
                }

                @Override
                public void failure(RetrofitError error) {
                    SpotifyRestClient.RestError errorBody = (SpotifyRestClient.RestError) error.getBodyAs(SpotifyRestClient.RestError.class);
                }
            });

        }

        return rootView;
    }
}
