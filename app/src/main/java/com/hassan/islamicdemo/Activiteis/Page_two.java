package com.hassan.islamicdemo.Activiteis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.hassan.islamicdemo.R;

public class Page_two extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_two);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        List<Images> imageList = new ArrayList();
        imageList.add(new Images(R.drawable.e1,"مشاركة الصورة ","تعيين خلفية"));
        imageList.add(new Images(R.drawable.e2,"مشاركة الصورة ","تعيين خلفية"));
        imageList.add(new Images(R.drawable.e3,"مشاركة الصورة ","تعيين خلفية"));

        final ImagesAdapter adapter = new ImagesAdapter(this, (ArrayList<Images>) imageList);
        recyclerView.setAdapter(adapter);

    }}