package com.example.mandeep.udacity_androiddev_spotifystreamer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A placeholder fragment containing a simple view.
 */
public class SearchArtistFragment extends Fragment {

    private CustomListArtistArrayAdapter mArtistListAdapter;

    public SearchArtistFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_search_artist, container, false);

        TextView searchArtistText = (TextView) rootView.findViewById(R.id.editText);
        searchArtistText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0 && s != null) {
                    SpotifyRestClient.get().SearchForArtist(s.toString(), "artist", new Callback<Spotify_SearchForArtist>() {
                        @Override
                        public void success(Spotify_SearchForArtist spotify_searchForArtist, Response response) {


                            List<ListArtistRowItem> artists = new ArrayList<ListArtistRowItem>();

                            for (int i = 0; i < spotify_searchForArtist.artists.items.size(); i++) {
                                String artistName;
                                String imageURL;
                                String artistID;

                                if (spotify_searchForArtist.artists.items.get(i).images.size() > 1) {
                                    imageURL = spotify_searchForArtist.artists.items.get(i).images.get(0).url; //get the image, blocking load
                                } else {
                                    imageURL = "https://play.spotify.edgekey.net/client/adc3363/images/logo.png";
                                }

                                artistID = spotify_searchForArtist.artists.items.get(i).id;
                                artistName = spotify_searchForArtist.artists.items.get(i).name;
                                artists.add(new ListArtistRowItem(imageURL, artistName,artistID));
                            }

                            ListView listView = (ListView) rootView.findViewById(R.id.list_view_artists);

                            // The ArrayAdapter will take data from a source and
                            // use it to populate the ListView it's attached to.
                            mArtistListAdapter =
                                    new CustomListArtistArrayAdapter(
                                            getActivity(), // The current context (this activity)
                                            R.layout.list_item_artist, // The name of the layout ID.
                                            artists
                                    );
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String artistId = mArtistListAdapter.getItem(position).artistId;
                                    String aritstName = mArtistListAdapter.getItem(position).artistName;
                                    Intent intent = new Intent(getActivity(),ArtistTracksActivity.class)
                                            .putExtra(Intent.EXTRA_TEXT, artistId)
                                            .putExtra(Intent.EXTRA_USER,aritstName);
                                    startActivity(intent);
                                }
                            });
                            listView.setAdapter(mArtistListAdapter);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            SpotifyRestClient.RestError errorBody = (SpotifyRestClient.RestError) error.getBodyAs(SpotifyRestClient.RestError.class);
                        }
                    });
                } else {
                    if (mArtistListAdapter != null) {
                        mArtistListAdapter.clear();
                    }
                }

            }
        });

        return rootView;
    }
}
