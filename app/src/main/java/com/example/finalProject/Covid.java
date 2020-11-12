package com.example.finalProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.example.androidlabs.R;
import java.util.ArrayList;

public class Covid extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();
    MyListAdapter myAdapter;
    ImageButton searchButton;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid);

        ListView myList = findViewById(R.id.listView);
        myAdapter = new MyListAdapter();
        myList.setAdapter(myAdapter);

        searchText = findViewById(R.id.searchText);
        searchButton = findViewById(R.id.magnify);
        searchButton.setOnClickListener( (click) ->
        {

        });
    }

        class MyListAdapter extends BaseAdapter {

            @Override //number of items in the list
            public int getCount() {
                return list.size();
            }

            @Override // what string goes at row;
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override // database id at row i
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View v, ViewGroup parent) {  //this returns the layout that will be positioned at the specified row in the list.

                LayoutInflater inflater = getLayoutInflater();

                //make a new row:
                View newView = inflater.inflate(R.layout.row_covidlayout, parent, false);

                //set what the text should be for this row:
                TextView tView = newView.findViewById(R.id.searchText);
                tView.setText(getItem(position).toString());

                //return it to be put in the table
                return newView;
            }
        }
}
