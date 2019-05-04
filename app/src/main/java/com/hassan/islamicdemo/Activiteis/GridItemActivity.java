package com.hassan.islamicdemo.Activiteis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.hassan.islamicdemo.R;


public class GridItemActivity extends AppCompatActivity {
    TextView gridData;

    TextView gridData1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);

        gridData = findViewById(R.id.griddata);
        gridData1 = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String receivedName =  intent.getStringExtra("name");
        String receivedImage =  intent.getStringExtra("image");

        // int receivedImage = intent.getIntExtra("image",0);
        gridData.setText(receivedName);

        gridData1.setText(receivedImage);
        //enable back Button
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
