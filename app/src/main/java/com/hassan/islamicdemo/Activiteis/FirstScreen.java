package com.hassan.islamicdemo.Activiteis;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.hassan.islamicdemo.PrayersService.PrayersService;
import com.hassan.islamicdemo.R;

import com.hassan.islamicdemo.Home.MainActivity;
import com.hassan.islamicdemo.Utils.AppConstants;


public class FirstScreen extends AppCompatActivity {

    GridView gridView;
    String[] fruitNames = {"اوقات الصلاة", "الادعية", "اسماء الله الحسنى", "بطاقات تهنئة"};
    int[] fruitImages = {R.drawable.calendar, R.drawable.dua, R.drawable.asm2,
            R.drawable.ic_message};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        //finding listview
        gridView = findViewById(R.id.gridview);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),fruitImages[0],Toast.LENGTH_LONG).show();

                if (i == 0) {
                    //  Toast.makeText(getApplicationContext(),view.getId()+"",Toast.LENGTH_LONG).show();

                    checkLocationPermissions();


                } else if (i == 1) {
                    //  Toast.makeText(getApplicationContext(),view.getId()+"",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(FirstScreen.this, DouActivity.class);
                    startActivity(intent);
                } else if (i == 2) {
                    //Toast.makeText(getApplicationContext(),view.getId()+"",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(FirstScreen.this, Names_Of_Alla.class);
                    startActivity(intent);
                } else if (i == 3) {
                    // Toast.makeText(getApplicationContext(),view.getId()+"",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(FirstScreen.this, Congratulation.class);
                    startActivity(intent);
                }


            }
        });


    }




    public static boolean isLocationEnabled(Context context) {
        return getLocationMode(context) != Settings.Secure.LOCATION_MODE_OFF;
    }

    private static int getLocationMode(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF);
    }

    public void checkLocationPermissions(){

        if (ActivityCompat.checkSelfPermission(FirstScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(FirstScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FirstScreen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    AppConstants.LOCATION_REQUEST);
            checkLocationPermissions();
        } else {


            Intent intent = new Intent(FirstScreen.this, MainActivity.class);
            startActivity(intent);
        }

    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return fruitImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data1, null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.fruits);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(fruitNames[i]);
            image.setImageResource(fruitImages[i]);
            return view1;


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.itme_message_secreen, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.LOCATION_REQUEST && resultCode == Activity.RESULT_OK) {

            Intent intent = new Intent(FirstScreen.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case android.R.id.home:
                finish();
                break;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "مرحبا اريد ان اشارككم هدا التطبيق " + getString(R.string.app_name) + "  https://play.google.com/store/apps/details?id=" + getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

            case R.id.rate:
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;


        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public void onBackPressed() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.quit_dialog);

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbg);

        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.setTitle("تنبيه");
        dialog.getWindow().setTitleColor(Color.rgb(140, 12, 13));
        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText("هل تريد خروج من التطبيق؟");
        dialog.show();
        Button yesbutton = (Button) dialog.findViewById(R.id.yes_bt);
        // if button is clicked, close the custom dialog
        yesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity(); // اغلاق كافة التطبيق

                dialog.dismiss();

            }
        });


        Button nobutton = (Button) dialog.findViewById(R.id.no_bt);
        // if button is clicked, close the custom dialog
        nobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });


    }



    private boolean isServiceRunning(Class<?> clazz) {
        final ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (clazz.getName().equals(serviceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}


