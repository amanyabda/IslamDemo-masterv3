package com.hassan.islamicdemo.Activiteis;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.hassan.islamicdemo.BuildConfig;
import com.hassan.islamicdemo.R;


public class ImagesAdapter  extends  RecyclerView.Adapter<ImagesAdapter.MyViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<Images> imageModelArrayList;
    private Context context;



    public ImagesAdapter(Context context,ArrayList<Images> imageModelArrayList) {
        this.context=context;
        this.inflater = LayoutInflater.from(context);
        this.imageModelArrayList = imageModelArrayList; }
    @NonNull
    @Override
    public ImagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_image, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder; }


    @Override
    public void onBindViewHolder(@NonNull ImagesAdapter.MyViewHolder holder, int position) {

        holder.image.setImageResource(imageModelArrayList.get(position).getImage());
        holder.share.setText(imageModelArrayList.get(position).getShare());
        holder.well.setText(imageModelArrayList.get(position).getWall());



        holder.well.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WallpaperManager wallpaperManager=WallpaperManager.getInstance(context);
                try {
                    wallpaperManager.setBitmap(viewToBitmap(holder.image,holder.image.getWidth(),holder.image.getHeight()));
                    Toast.makeText(context, "تم بنجاح تعين الصورة كخلفية", Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    e.printStackTrace();


                }
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                Bitmap bitmap = viewToBitmap(holder.image,holder.image.getWidth(),holder.image.getHeight());;
                try {
                    File file = new File(context.getExternalCacheDir(), "qr.png");
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//                    Log.e( "Path : ",""+file);
//                    Log.e( "Uri : ",""+Uri.fromFile(file) );

//                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                    intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider",file));
                    intent.setType("image/png");
                    context.startActivity(Intent.createChooser(intent, "مشاركة الصورة عبر"));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView save,share,well;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.a2);
            share = (TextView) itemView.findViewById(R.id.share);
            well = (TextView) itemView.findViewById(R.id.set_wallpaper);



        }

    }



    public File getDisc(){
        File file= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return new File(file,"ImageDemo");
    }

    public static Bitmap viewToBitmap(View view, int width, int hight) {
        Bitmap bitmap = Bitmap.createBitmap(width, hight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;


    }


    private void refreshGallery(File file) {
        Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
    }

}
