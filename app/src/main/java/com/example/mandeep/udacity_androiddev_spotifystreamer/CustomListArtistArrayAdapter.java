package com.example.mandeep.udacity_androiddev_spotifystreamer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Mandeep on 7/4/2015.
 */
public class CustomListArtistArrayAdapter extends ArrayAdapter<ListArtistRowItem>{
    Context context;

    public CustomListArtistArrayAdapter(Context context, int resourceId, List<ListArtistRowItem> items)
    {
        super(context,resourceId,items);
        this.context=context;
    }

    //This will be used to store the ids of the views we need to udpate
    private class ViewHolder {
        ImageView imageView;
        TextView txtArtist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ListArtistRowItem rowItem = getItem(position);

        LayoutInflater mInflator = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ImageLoader imageLoader=ImageLoader.getInstance();

        if(convertView==null)
        {
            convertView = mInflator.inflate(R.layout.list_item_artist,null);
            holder=new ViewHolder();
            holder.imageView=(ImageView)(convertView.findViewById(R.id.list_image_artist_album));
            holder.txtArtist=(TextView)(convertView.findViewById(R.id.list_item_artist_albumName));
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }

        imageLoader.displayImage(rowItem.imageURL,holder.imageView);
        holder.txtArtist.setText(rowItem.artistName);


        return convertView;
    }
}
