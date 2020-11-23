package com.example.finalProject.audioTask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidlabs.R;
import com.example.finalProject.Audio.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AudioAdapter extends ArrayAdapter<Album> {

    private List<Album> itemList;
    private Context context;

    public AudioAdapter(List<Album> itemList, Context ctx) {
        super(ctx, android.R.layout.simple_list_item_1, itemList);
        this.itemList = itemList;
        this.context = ctx;
    }

    public int getCount() {
        if (itemList != null)
            return itemList.size();
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.album_item, null);
        }

        Album c = itemList.get(position);
        TextView text = (TextView) v.findViewById(R.id.album_name);
        text.setText(c.getAlbumName());

        TextView text1 = (TextView) v.findViewById(R.id.name);
        text1.setText(c.getArtistName());

        TextView text2 = (TextView) v.findViewById(R.id.year);
        text2.setText(c.getYear());

        TextView text3 = (TextView) v.findViewById(R.id.rate);
        if (c.getRate() != null) {
            text3.setText(c.getRate());
        }

        ImageView imageView = (ImageView) v.findViewById(R.id.image);

        if (c.getImage() != null && !c.getImage().equals("")) {
            Picasso.get().load(c.getImage()).placeholder(R.drawable.image_placeholder).into(imageView);
        }
        return v;

    }

    public List<Album> getItemList() {
        return itemList;
    }

    public void setItemList(List<Album> itemList) {
        this.itemList = itemList;
    }

}