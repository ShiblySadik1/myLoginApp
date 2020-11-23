package com.example.finalProject.audioTask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidlabs.R;
import com.example.finalProject.Audio.Album;

import java.util.List;

public class TrackAdapter extends ArrayAdapter<Track> {

    private List<Track> itemList;
    private Context context;

    IOnItemClickListner listner;
    public TrackAdapter(List<Track> itemList, Context ctx) {
        super(ctx, android.R.layout.simple_list_item_1, itemList);
        this.itemList = itemList;
        this.context = ctx;
        listner = (IOnItemClickListner) ctx ;
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
            v = inflater.inflate(R.layout.track_item, null);
        }

        Track c = itemList.get(position);
        Button button = (Button) v.findViewById(R.id.track_name);
        button.setText(c.getId());
        TextView text1 = (TextView) v.findViewById(R.id.album);
        text1.setText(c.getName());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onClick(c.getName());
            }
        });
        return v;

    }


}