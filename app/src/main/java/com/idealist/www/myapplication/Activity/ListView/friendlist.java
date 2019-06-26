package com.idealist.www.myapplication.Activity.ListView;

import android.app.Application;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idealist.www.myapplication.Adapter.Adapter_friendlist;
import com.idealist.www.myapplication.Item.FriendItem;
import com.idealist.www.myapplication.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class friendlist extends AppCompatActivity {

    private String id; // 로그인아이디 구분


    private Button btn_add, btn_delete;
    private EditText edt_Name, edt_Major, edt_Mail, edt_PhoneNumber;
    private CheckBox cb;
    private ListView listview;
    private ArrayList<FriendItem> array;
    private Adapter_friendlist adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);

        SharedPreferences Operation = getSharedPreferences("Operation",MODE_PRIVATE); //로그인 아이디 구분
        id = Operation.getString("Operation",null);
        btn_add = (Button) findViewById(R.id.Friend_register);
        btn_delete = (Button) findViewById(R.id.Friend_delete);
        edt_Name = (EditText) findViewById(R.id.Friend_edt_Name);
        edt_Major = (EditText) findViewById(R.id.Friend_edt_Major);
        edt_Mail = (EditText) findViewById(R.id.Friend_edt_Mail);
        edt_PhoneNumber = (EditText) findViewById(R.id.Friend_edt_PhonNumber);
        cb = findViewById(R.id.Friend_CheckBox);
        listview = (ListView) findViewById(R.id.Friend_listview);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Addwork();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteWork();
            }
        });

        load();
        adapter = new Adapter_friendlist(this, R.layout.custom_listview_friendlist, array);
        listview.setAdapter(adapter);


    }


    public void Addwork() {
        if (edt_Name.getText().toString().equals("") || edt_Major.getText().toString().equals("")) {

            AlertDialog.Builder builder = new AlertDialog.Builder(friendlist.this);
            builder.setTitle("NAME과 MAJOR는 반드시 입력!");
            builder.setMessage("Name : 기본정보(반드시 입력) \nMajor : 기본정보(반드시 입력) \nG-mail : 입력시 구글드라이브 공유가능" +
                    " \nPhone : 입력시 채팅가능");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();

        } else {
            final String name = edt_Name.getText().toString();
            final String major = edt_Major.getText().toString();
            final String mail = edt_Mail.getText().toString();
            final String phonenumber = edt_PhoneNumber.getText().toString();



            FriendItem item = new FriendItem(name, major, mail, phonenumber);
            array.add(array.size(), item);
            edt_Name.setText("");
            edt_Major.setText("");
            edt_Mail.setText("");
            edt_PhoneNumber.setText("");
            edt_Name.requestFocus();

            //SAVE
            SharedPreferences sharedPreferences = getSharedPreferences(id, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(array);
            editor.putString("friend list", json);
            editor.commit();
        }


    }


    private void DeleteWork() {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).ischecked()) {
                array.remove(i);
                i--;
            }
        }
        adapter.notifyDataSetChanged();

        //SAVE
        SharedPreferences sharedPreferences = getSharedPreferences(id, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(array);
        editor.putString("friend list", json);
        editor.commit();
    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences(id, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
        Gson gson = new Gson();
        String json = sharedPreferences.getString("friend list", null);
        Type type = new TypeToken<ArrayList<FriendItem>>() {}.getType();
        array = gson.fromJson(json, type);
        if (array == null) {
            array = new ArrayList<>();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //SAVE
        SharedPreferences sharedPreferences = getSharedPreferences(id, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(array);
        editor.putString("friend list", json);
        editor.commit();
    }
}
