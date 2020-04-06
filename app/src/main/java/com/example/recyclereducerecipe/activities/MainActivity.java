package com.example.recyclereducerecipe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclereducerecipe.R;
import com.example.recyclereducerecipe.adapter.RecipeViewAdapter;
import com.example.recyclereducerecipe.model.Recipe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "MainActivity";
    private TextView myTextView;
    String[] recipename = {"Burrito", "Chicken Sandwhich", "Hamburger", "Greek Salad", "Manacotti", "Pizza", "Roast_Beef"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.r_layout);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecipeViewAdapter(getRecipeName());
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((RecipeViewAdapter) mAdapter).setOnItemClickListener(new RecipeViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
                String name = (recipename[position]);
                Toast.makeText(MainActivity.this, name + " was clicked!", Toast.LENGTH_SHORT).show();
            }});}

            public ArrayList<Recipe> getRecipeName() {
                ArrayList results = new ArrayList<Recipe>();
                for (int index = 0; index < 20; index++) {
                    Recipe obj = new Recipe("recipe" + index);
                    results.add(index, obj);
                }
                return results;
            }}
