package com.idealist.www.myapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idealist.www.myapplication.Adapter.Adapter_sharelist;
import com.idealist.www.myapplication.Item.ShareItem;
import com.idealist.www.myapplication.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class interestpost extends AppCompatActivity {


    int postLikeCount;
    boolean postLike;
    String bitmap_String;
    String postText;
    Bitmap bitmap;
    static String id;
    public static String interestname;
    TextView indicator_interst;
    public static RecyclerView rvList;

    public static ArrayList<ShareItem> listItem = new ArrayList<>();
    Adapter_sharelist adapter;
    ImageView fab_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interestpost);

        SharedPreferences sharepref = getSharedPreferences("Operation", MODE_PRIVATE);
        id = sharepref.getString("Operation", null);


        Intent myintent = getIntent();
        interestname = myintent.getStringExtra("interestsubject");


        indicator_interst = findViewById(R.id.ic_interest);
        indicator_interst.setText(interestname);

        rvList = findViewById(R.id.rv_list);
        fab_post = findViewById(R.id.fab_post);


        fab_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenToPost();
            }
        });

        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Toast.makeText(this, "접속한 ID : " + IdealistMain.id, Toast.LENGTH_LONG).show();

    }


    private void ScreenToPost() {
        Intent myintent = new Intent(interestpost.this, Post.class);
        myintent.putExtra("interestsubject", interestname);
        startActivity(myintent);
        overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
    }


    private Bitmap getBitmapFromString(String json) {
/*
* This Function converts the String back to Bitmap
* */
        byte[] decodedString = Base64.decode(json, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }


    @Override
    protected void onResume() {
        super.onResume();
        load();
        adapter = new Adapter_sharelist(this, listItem);
        rvList.setAdapter(adapter);
    }


    private void load() {

        SharedPreferences sharedPreferences = getSharedPreferences(interestname, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
        Gson gson = new Gson();
        String json = sharedPreferences.getString("postlist", null);
        Type type = new TypeToken<ArrayList<ShareItem>>() {
        }.getType();
        listItem = gson.fromJson(json, type);
        if (listItem == null) {
            listItem = new ArrayList<>();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_slide_out_top);
    }
}

