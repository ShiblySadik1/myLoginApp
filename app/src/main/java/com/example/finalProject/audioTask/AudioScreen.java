package com.example.finalProject.audioTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidlabs.R;
import com.example.finalProject.Audio.Album;

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

public class AudioScreen extends AppCompatActivity implements ISearchClickLiestner {

    private ListView lv;
    private AudioAdapter adapter;
    public List<Album> albums;
    private ProgressBar progressBar;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_screen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Audio");

        lv = findViewById(R.id.lvAudioList);
        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute("https://www.theaudiodb.com/api/v1/json/1/searchalbum.php?s=adam");

        progressBar = findViewById(R.id.progress_circular);
        imageView = findViewById(R.id.not_found);
        albums = new ArrayList<>();
        adapter = new AudioAdapter(albums, this);

lv.setAdapter(adapter);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, AlbumDetailsActivity.class);
            intent.putExtra("id", albums.get(i).getId());
            intent.putExtra("name", albums.get(i).getArtistName());
            intent.putExtra("image", albums.get(i).getImage());
            intent.putExtra("description", albums.get(i).getDescription());
            startActivity(intent);
        });
    }

    private void openSearchFragment(){
        FragmentManager fm = getSupportFragmentManager();
        SearchDialog editNameDialogFragment = SearchDialog.newInstance();
        editNameDialogFragment.show(fm, "Confirm Reservation Dialog");
        editNameDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }else if (item.getItemId() == R.id.search){
            openSearchFragment();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onClickSearch(String name) {
        albums.clear();
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.VISIBLE);
        if (imageView.getVisibility() == View.VISIBLE){
            imageView.setVisibility(View.GONE);
        }
        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute("https://www.theaudiodb.com/api/v1/json/1/searchalbum.php?s=" + name);

    }

    public class MyAsyncTasks extends AsyncTask<String, Void, List<Album>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
        }
        List<Album> result = new ArrayList<Album>();
        JSONObject jsonObject ;
        @Override
        protected List<Album> doInBackground(String... params) {


            String stringUrl = params[0];
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);

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
                JSONArray arr = (JSONArray) jsonObject.get("album");

                Album album ;
                for (int i=0; i < arr.length(); i++) {
                    album = new Album();
                    album.setId(arr.getJSONObject(i).getString("idAlbum"));
                    album.setArtistName(arr.getJSONObject(i).getString("strArtist"));
                    album.setAlbumName(arr.getJSONObject(i).getString("strAlbum"));
                    album.setImage(arr.getJSONObject(i).getString("strAlbumThumb"));
                    album.setRate(arr.getJSONObject(i).getString("intScore"));
                    album.setYear(arr.getJSONObject(i).getString("intYearReleased"));
                    album.setDescription(arr.getJSONObject(i).getString("strDescriptionES"));


                    result.add(album);
                }
            }
            catch(Exception e){
                result = null;
                e.printStackTrace();
            }
            return result;
        }


        @Override
        protected void onPostExecute(List<Album> s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);

            if (s != null){
                albums.clear();
                albums.addAll(s);
                adapter.notifyDataSetChanged();
            }else {
                imageView.setVisibility(View.VISIBLE);
            }

        }

    }
}

