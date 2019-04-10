package com.marolix.newstabs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class News_Details extends AppCompatActivity {
    RecyclerView recyclerView;
   ImageView image,share;
   TextView title,time,heading,description;
    ArrayAdapter<String> adapter;
    File imagePath;
   static ArrayList<Map<String,String>> al1;
    ArrayList<Map<String,String>> al;
    LinearLayout relate;
    RecyclerAdapter recycler;
    public RecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__details);
        recyclerView=findViewById(R.id.recyclerView);
        image=findViewById(R.id.image);
        title=findViewById(R.id.title);
        time=findViewById(R.id.time);
        share=findViewById(R.id.share);
        heading=findViewById(R.id.heading);
        relate=findViewById(R.id.relate);
        description=findViewById(R.id.description);
        al1=new ArrayList<>();
        al1=new ArrayList<>();
        al= (ArrayList<Map<String, String>>) getIntent().getSerializableExtra("al");
        int position= getIntent().getIntExtra("position",0);
        Picasso.with(this)
                .load(al.get(position).get("urltoimage"))
                .into(image);
        title.setText(al.get(position).get("category"));
        time.setText(al.get(position).get("publishdate"));
        heading.setText(al.get(position).get("heading"));
        description.setText(al.get(position).get("description"));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerAdapter=new RecyclerAdapter(this,al,position);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(News_Details.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setAdapter(recyclerAdapter);
        share.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        relate.setVisibility(v.GONE);
        Bitmap bitmap = takeScreenshot(v);
        saveBitmap(bitmap);
        shareIt();
        relate.setVisibility(v.VISIBLE);
    }
});
    }

    @Override
  public void onBackPressed() {
//        Intent a = new Intent(Intent.ACTION_MAIN);
//        a.addCategory(Intent.CATEGORY_HOME);
//        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(a);
Intent i =new Intent(News_Details.this,MainActivity.class);
startActivity(i);
    }

    private void shareIt() {
//if (isPermissionGranted()) {
//    Uri uri = Uri.fromFile(imagePath);
    Uri uri = FileProvider.getUriForFile(News_Details.this, BuildConfig.APPLICATION_ID + ".provider", imagePath);
    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
    sharingIntent.setType("image/*");
    //String shareBody = "My highest score with screen shot";
    //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Catch score");
    //sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
    sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
    startActivity(Intent.createChooser(sharingIntent, "Share via"));
//}
    }
    private void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png"); ////File imagePath
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }
    public Bitmap takeScreenshot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }
    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (
                    (checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)
                            &&(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "below 23");
            Log.v("TAG", "Permission is revoked");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return false;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Uri uri = Uri.fromFile(imagePath);
                    Uri uri = FileProvider.getUriForFile(News_Details.this, BuildConfig.APPLICATION_ID + ".provider",imagePath);
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("image/*");
                    sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied1", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }
}
