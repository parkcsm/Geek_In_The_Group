package com.idealist.www.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idealist.www.myapplication.Activity.interestpost;
import com.idealist.www.myapplication.Item.ShareItem;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.idealist.www.myapplication.Activity.ListView.RecyclerView.shareholder.EXTRA_ID;
import static com.idealist.www.myapplication.Activity.ListView.RecyclerView.shareholder.EXTRA_POSITION;
import static com.idealist.www.myapplication.Activity.ListView.RecyclerView.shareholder.EXTRA_TEXT;
import static com.idealist.www.myapplication.Activity.ListView.RecyclerView.shareholder.EXTRA_URI_STRING;

public class content_change extends AppCompatActivity {

    ArrayList<ShareItem> listItem = new ArrayList<>();

    public static Uri image;
    String image_string;
    String id;
    String text;
    int position;
    ImageView change_img;
    TextView change_text;
    Button change_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_change);


        change_img = findViewById(R.id.change_img);
        change_text = findViewById(R.id.change_text);
        change_btn = findViewById(R.id.change_btn);

        Intent myintent = getIntent();

        //이미지
        id = myintent.getStringExtra(EXTRA_ID);
        image_string = myintent.getStringExtra(EXTRA_URI_STRING);
        image = Uri.parse(image_string);
        Glide.with(this).load(image).centerCrop().into(change_img);

        //텍스트
        text = myintent.getStringExtra(EXTRA_TEXT);
        change_text.setText(text);

        //버튼
        change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });

        position = myintent.getIntExtra(EXTRA_POSITION, 0);

        registerForContextMenu(change_img); //사진 롱버튼 / 카메라,앨범


        //list 불러오기 load
        SharedPreferences sharedPreferences = getSharedPreferences(interestpost.interestname, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
        Gson gson = new Gson();
        String json = sharedPreferences.getString("postlist", null);
        Type type = new TypeToken<ArrayList<ShareItem>>() {
        }.getType();
        listItem = gson.fromJson(json, type);
    }

    private void change() {


        if (image_string == null) {
            Toast.makeText(this, "사진을 등록해주세요!", Toast.LENGTH_SHORT).show();
        } else {
            if (change_text.getText().toString().length() <= 5 || change_text.getText().toString().length() >20) {
                Toast.makeText(this, "5자이상 20자 미만의 글자를 입력해주세요!", Toast.LENGTH_SHORT).show();
            } else {

                text = change_text.getText().toString();
                ShareItem item = new ShareItem(image_string, id, text, false, 0);
                listItem.remove(position);
                listItem.add(position, item);

                //SAVE
                SharedPreferences sharedPreferences = getSharedPreferences(interestpost.interestname, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(listItem);
                editor.putString("postlist", json);
                editor.commit();
                finish();
                Toast.makeText(this, "글이 수정되었습니다.", Toast.LENGTH_SHORT).show();

            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            image = data.getData();
            image_string = image.toString();
            Glide.with(this).load(image).centerCrop().into(change_img);

        }

//        Glide.with(this).load(url).into(ivFaust);

        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {

            image = data.getData();
            image_string = image.toString();
            Glide.with(this).load(image).centerCrop().into(change_img);

        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // 컨텍스트 메뉴가 최초로 한번만 호출되는 콜백 메서드
        Log.d("test", "onCreateContextMenu");
//        getMenuInflater().inflate(R.menu.main, menu);

        menu.setHeaderTitle("프로필사진 등록방법을 선택하세요!");
        menu.add(0, 1, 100, "사진 촬영");
        menu.add(0, 2, 100, "갤러리에서 불러오기");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // 롱클릭했을 때 나오는 context Menu 의 항목을 선택(클릭) 했을 때 호출
        switch (item.getItemId()) {
            case 1:// 사진 촬영 선택시
                Intent CameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (CameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(CameraIntent, 1000);
                }
                return true;
            case 2:// 사진 불러오기 선택시
                Intent myintent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(myintent, 1);
                return true;
        }

        return super.onContextItemSelected(item);
    }
}
