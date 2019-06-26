package com.idealist.www.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.idealist.www.myapplication.Activity.ListView.RecyclerView.shareholder.EXTRA_URI_STRING;
import static com.idealist.www.myapplication.Activity.ListView.RecyclerView.shareholder.EXTRA_ID;
import static com.idealist.www.myapplication.Activity.ListView.RecyclerView.shareholder.EXTRA_TEXT;
import static com.idealist.www.myapplication.Activity.ListView.RecyclerView.shareholder.EXTRA_URI_STRING;

public class comment extends AppCompatActivity {

    ImageView cv_iv;
    TextView cv_id, cv_tv;
    EditText cv_cm;

    ImageView btn_cm;
    comment_adapter comment_adapter;
    RecyclerView rvList;
    static ArrayList<comment_Item> listItem = new ArrayList<>();

    Uri uri;
    String id;
    String image_string;
    String writer;
    String text;
    String interestname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        SharedPreferences sharepref = getSharedPreferences("Operation", MODE_PRIVATE);
        id = sharepref.getString("Operation", null);

        Intent myintent = getIntent();

        image_string = myintent.getStringExtra(EXTRA_URI_STRING);
        writer = myintent.getStringExtra(EXTRA_ID);
        text = myintent.getStringExtra(EXTRA_TEXT);
        interestname = myintent.getStringExtra("interestsubject");

        cv_iv = findViewById(R.id.cv_iv);
        cv_id = findViewById(R.id.cv_id);
        cv_tv = findViewById(R.id.cv_tv);
        cv_cm = findViewById(R.id.cv_cm);

        uri = Uri.parse(image_string);
        Glide.with(this).load(uri).centerCrop().into(cv_iv);
        cv_id.setText(writer);
        cv_tv.setText(text);

        btn_cm = findViewById(R.id.btn_cm);
        btn_cm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

        rvList = findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }

    private void add() {


        if (cv_cm.getText().toString().length() < 5) {

            Toast.makeText(this, "댓글을 5자이상 입력해주세요!", Toast.LENGTH_SHORT).show();

        } else {

            comment_Item item = new comment_Item(id, cv_cm.getText().toString());
            listItem.add(0, item);
            comment_adapter = new comment_adapter(this, listItem);
            rvList.setAdapter(comment_adapter);

            //SAVE
            SharedPreferences sharedPreferences = getSharedPreferences(interestname, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(listItem);
            editor.putString(writer + text, json); //postnumber로 구분을 해준다!
            editor.commit();
        }
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


        comment_adapter = new comment_adapter(this, listItem);
        rvList.setAdapter(comment_adapter);
    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences(interestname, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
        Gson gson = new Gson();
        String json = sharedPreferences.getString(writer + text, null);
        Type type = new TypeToken<ArrayList<comment_Item>>() {
        }.getType();
        listItem = gson.fromJson(json, type);
        if (listItem == null) {
            listItem = new ArrayList<>();
        }
    }

}
