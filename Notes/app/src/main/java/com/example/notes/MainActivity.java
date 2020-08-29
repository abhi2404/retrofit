package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ArrayList<notesData>mExampleList;
    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton button;
    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        buildRecyclerView();
        FloatingActionButton save=(FloatingActionButton)findViewById(R.id.Save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedata();
            }

            private void savedata() {
                SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(mExampleList);
                    editor.putString("tasklist", json);
                    editor.apply();
                    Toast toast = Toast.makeText(getApplicationContext(), "Notes Saved", Toast.LENGTH_SHORT);
                    toast.show();
                }
        });



        button=(FloatingActionButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();

            }

        });

    }
    private void buildRecyclerView() {

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        notesAdapter = new NotesAdapter(mExampleList);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setLayoutManager(mLayoutManager);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String edit1 = bundle.getString("title");
            String edit2 = bundle.getString("body");
            insertItem(edit1, edit2);
        }
        ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int i) {
                Toast toast=Toast.makeText(getApplicationContext(),"Note Deleted",Toast.LENGTH_SHORT);
                toast.show();
                int position=target.getAdapterPosition();
                mExampleList.remove(position);
                notesAdapter.notifyDataSetChanged();

                    save();

            }
        });
        helper.attachToRecyclerView(recyclerView);

    }

    private void save() {
        SharedPreferences sharedPreferences=getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(mExampleList);
        editor.putString("tasklist",json);
        editor.apply();
    }

    private void insertItem(String edit1, String edit2) {
        //Intent i=getIntent();
       //String edit1=i.getStringExtra("title");
       //String edit2=i.getStringExtra("body");
        mExampleList.add(new notesData(edit1,edit2));
        notesAdapter.notifyItemInserted(mExampleList.size());
    }

    private void loadData() {
        SharedPreferences sharedPreferences=getSharedPreferences("pref",MODE_PRIVATE);
        Gson gson=new Gson();
        String json= sharedPreferences.getString("tasklist",null);
        Type type = new TypeToken<ArrayList<notesData>>() {}.getType();
        mExampleList=gson.fromJson(json,type);

        if (mExampleList==null){
            mExampleList=new ArrayList<>();
        }
    }

    public void openNewActivity(){
        Intent i;
        i = new Intent(this,NewActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        Intent a=new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }



}