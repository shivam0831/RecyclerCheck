package com.example.sqlite.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sqlite.R;
import com.example.sqlite.database.DatabaseHelper;
import com.example.sqlite.database.model.Note;
import com.example.sqlite.utils.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class NextActivity extends AppCompatActivity {

    private ArrayList<Note> iNoteArrayList = new ArrayList<>();
    private RecyclerView recyclerView;

    private DatabaseHelper db;
    private NextAdapter nAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        recyclerView = findViewById(R.id.recycler);
        db = new DatabaseHelper(this);
//        iNoteArrayList.addAll(db.getAllNotes());

        nAdapter = new NextAdapter(this, iNoteArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(nAdapter);
    }

    public void addSelected(){

    }
}
