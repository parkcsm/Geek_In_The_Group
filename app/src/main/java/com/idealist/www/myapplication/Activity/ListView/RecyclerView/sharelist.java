package com.idealist.www.myapplication.Activity.ListView.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.idealist.www.myapplication.Adapter.Adapter_sharelist;
import com.idealist.www.myapplication.Item.ShareItem;
import com.idealist.www.myapplication.Activity.Post;
import com.idealist.www.myapplication.R;

import java.util.ArrayList;

public class sharelist extends AppCompatActivity {


    FloatingActionButton fab_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharelist);


        ArrayList<ShareItem> listItem = new ArrayList<>();
        RecyclerView rvList = findViewById(R.id.rv_list);


        Adapter_sharelist adapter = new Adapter_sharelist(this, listItem);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fab_post = findViewById(R.id.fab_post);
        fab_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(CameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(CameraIntent,1000);
                }
            }
        });

        rvList.setAdapter(adapter);
    }


    /** Main -> run Camera -> main -> Post */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==1000 && resultCode == Activity.RESULT_OK){

            Intent startIntent = new Intent(this,Post.class);
            startIntent.setData(data.getData()); //Intent data(카메라)로부터 정보만 빼다가, 다음엑티비티(인텐트)로 넣는것!
            startActivity(startIntent);


            Log.d("onActivityResult","Camera Success");
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            ImageView imageView = null;
//            imageView.setImageBitmap(imageBitmap);
        }

    }
}
