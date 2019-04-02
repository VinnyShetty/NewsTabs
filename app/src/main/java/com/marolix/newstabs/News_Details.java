package com.marolix.newstabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

public class News_Details extends AppCompatActivity {
    RecyclerView recyclerView;
    String [] arr = {"Technology","Football Match","Demontisation"};
    String [] arr1 = {"22/01/2019","23/01/2019","24/01/2019"};
    int [] images =  {R.drawable.sports,R.drawable.sports,R.drawable.sports};
    ArrayAdapter<String> adapter;
    RecyclerAdapter recycler;
    public RecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__details);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerAdapter=new RecyclerAdapter(this,arr,arr1,images);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(News_Details.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setAdapter(recyclerAdapter);

    }
}
