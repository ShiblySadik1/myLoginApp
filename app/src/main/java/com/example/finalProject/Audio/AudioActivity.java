package com.example.finalProject.Audio;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidlabs.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AudioActivity extends AppCompatActivity {

    private Album album;

    private Fragment current;
    private AudioSearchFragment searchFragment;
    private AudioListFragment listFragment;
    private AudioItemFragment itemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute();
        searchFragment = new AudioSearchFragment();
        listFragment = new AudioListFragment();
        itemFragment = new AudioItemFragment();
    }

    @Override
    public void onBackPressed() {
        back();
    }

    /**
     *
     * @param fragment
     */
    private void changeFragment(Fragment fragment) {

        if (!fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.frAudio, fragment).commit();
        }

        getSupportFragmentManager().beginTransaction().hide(current).show(fragment).commit();

        current = fragment;
    }

    public void openSearch() {
        changeFragment(searchFragment);
    }

    public void openList() {
        changeFragment(listFragment);
    }

    public void openItem() {
        changeFragment(itemFragment);
    }

    public void back() {
        if (current == searchFragment) ;
        else if (current == listFragment) openSearch();
        else if (current == itemFragment) openList();
    }

    /**
     *
     * @return
     */
    public Album getAlbum() {
        return album;
    }

    /**
     *
     * @param album
     */
    public void setAlbum(Album album) {
        this.album = album;
    }
}


class MyAsyncTasks extends AsyncTask<String, Void, String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // display a progress dialog for good user experiance
//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setMessage("Please Wait");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        List<String> result = new ArrayList<String>();

        try {
            URL u = new URL("https://www.theaudiodb.com/api/v1/json/1/searchalbum.php?s=adam");

            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");

            conn.connect();
            InputStream is = conn.getInputStream();

            // Read the stream
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ( is.read(b) != -1)
                baos.write(b);

            String JSONResp = new String(baos.toByteArray());
            JSONObject jsonObject = new JSONObject(baos.toString());
            JSONArray arr = (JSONArray) jsonObject.get("album");

            for (int i=0; i < arr.length(); i++) {
//                result.add(convertContact(arr.getJSONObject(i)));
                Log.e("kkk", arr.getJSONObject(i).getString("strArtist"));
            }


//            JSONObject jsonObject = new JSONObject(JSONResp);
//            JSONArray arr = new JSONArray(jsonObject);
//            for (int i=0; i < arr.length(); i++) {
//                result.add(convertContact(arr.getJSONObject(i)));
//            }

//            result.add(convertContact(jsonObject));
//            Log.e("ggg", result.toString());
            return JSONResp;
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    private String convertContact(JSONObject obj) throws JSONException {
//        JSONArray array = new JSONArray(obj.getJSONArray("track"));
//        JSONObject name = (JSONObject) array.get(0);
        String surname = obj.getString("strTrack");
        String email = obj.getString("email");
        String phoneNum = obj.getString("phoneNum");

        return surname;
    }
    @Override
    protected void onPostExecute(String s) {
//        Log.e("hhh",s);

//        Log.d("data", s.toString());
        // dismiss the progress dialog after receiving data from API
//        progressDialog.dismiss();
//        try {
            // JSON Parsing of data
//            JSONArray jsonArray = new JSONArray(s);

//            JSONObject oneObject = jsonArray.getJSONObject(0);
            // Pulling items from the array
//            title = oneObject.getString("title");
//            category = oneObject.getString("category");
//            image = oneObject.getString("image");
//            // display the data in UI
//            titleTextView.setText("Title: "+title);
//            categoryTextView.setText("Category: "+category);
//            // Picasso library to display the image from URL
//            Picasso.with(getApplicationContext())
//                    .load(image)
//                    .into(imageView);


//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


    }

}