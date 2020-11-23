package com.example.finalProject.audioTask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidlabs.R;
import com.example.finalProject.Audio.Album;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AlbumDetailsActivity extends AppCompatActivity implements IOnItemClickListner {

    private ListView lv;
    private TrackAdapter adapter;
    public List<Track> albums;
    private String id;
    private String name;
    private String image;
    private String description;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);

        getSupportActionBar().hide();
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        image = getIntent().getStringExtra("image");
        description = getIntent().getStringExtra("description");
        TextView descriptionText = (TextView) findViewById(R.id.description);
        descriptionText.setText(description);
        progressBar = findViewById(R.id.progress_circular);


        ImageView imageView = (ImageView) findViewById(R.id.image);

        if (image!= null && !image.equals("") ){
            Picasso.get().load(image).into(imageView);

        }
        lv = findViewById(R.id.track_list);
        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute("https://theaudiodb.com/api/v1/json/1/track.php?m=" + id);

        albums = new ArrayList<>();
        adapter = new TrackAdapter(albums, this);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {


        });
    }

    @Override
    public void onClick(String track) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q=" + track + name));
        startActivity(browserIntent);
    }

    public class MyAsyncTasks extends AsyncTask<String, Void, List<Track>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
        }
        List<Track> result = new ArrayList<Track>();
        JSONObject jsonObject ;

        @Override
        protected List<Track> doInBackground(String... params) {


            String stringUrl = params[0];
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
//                connection.setRequestMethod(get);
//                connection.setReadTimeout(READ_TIMEOUT);
//                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                String data = stringBuilder.toString();

                jsonObject = new JSONObject(data);
                JSONArray arr = (JSONArray) jsonObject.get("track");

                Track track ;
                for (int i=0; i < arr.length(); i++) {
                    track = new Track();
                    Log.e("kkk", arr.getJSONObject(i).getString("strArtist"));
                    track.setId(arr.getJSONObject(i).getString("idTrack"));
                    track.setName(arr.getJSONObject(i).getString("strTrack"));

                    result.add(track);
                }
            }
            catch(Exception e){
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Track> s) {
            super.onPostExecute(s);

            if (s != null){

                albums.addAll(s);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

        }

    }
}