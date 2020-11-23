package com.example.finalProject.audioTask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.androidlabs.R;

import java.util.HashMap;
import java.util.Map;

public class SearchDialog extends DialogFragment {


    public static SearchDialog newInstance() {
        SearchDialog frag = new SearchDialog();
        Bundle args = new Bundle();

        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        EditText text=view.findViewById(R.id.artist_name);


        ISearchClickLiestner liestner = (ISearchClickLiestner) getActivity();
        Button button=view.findViewById(R.id.search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!text.getText().equals("")){
                    liestner.onClickSearch(text.getText().toString());
                }
                dismiss();
            }
        });

        return view;
    }

}
