package com.example.finalProject.Audio;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidlabs.R;

import java.util.ArrayList;

public class AudioListFragment extends Fragment {

    private ListView lv;
    private ArrayAdapter<String> adapter;
    private ArrayList<Album> albums;

    private AudioActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (AudioActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audio_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        buildListView();
    }

    /**
     *
     * @param v
     */
    private void findViews(View v) {
        lv = v.findViewById(R.id.lvAudioList);
    }

    private void buildListView() {
        ArrayList<String> list = new ArrayList<>();
        adapter = new ArrayAdapter<>(activity, R.layout.row_audio_list, list);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {

        });
    }
}
