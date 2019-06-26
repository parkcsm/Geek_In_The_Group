package com.idealist.www.myapplication.Activity.ListView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idealist.www.myapplication.Adapter.Adapter_thinklist;
import com.idealist.www.myapplication.R;
import com.idealist.www.myapplication.Item.ThinkItem;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class thinklist extends AppCompatActivity {


    private String id; // 로그인아이디 구분


    private Button btn_add, btn_delete;
    private EditText edt_Year, edt_Month, edt_Day, edt_Opinion, edt_Source;
    private ListView listview;
    private ArrayList<ThinkItem> array;
    private Adapter_thinklist adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinklist);


        SharedPreferences Operation = getSharedPreferences("Operation",MODE_PRIVATE); //로그인 아이디 구분
        id = Operation.getString("Operation",null);

        btn_add = (Button) findViewById(R.id.Think_register);
        btn_delete = (Button) findViewById(R.id.Think_delete);
        edt_Year = (EditText) findViewById(R.id.Think_edt_Year);
        edt_Month = (EditText) findViewById(R.id.Think_edt_Month);
        edt_Day = (EditText) findViewById(R.id.Think_edt_day);
        edt_Opinion = (EditText) findViewById(R.id.Think_edt_Opinion);
        edt_Source = (EditText) findViewById(R.id.Think_edt_Source);

        listview = (ListView) findViewById(R.id.Think_listview);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Addwork();
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          Deletework();

            }


        });

        load();

        adapter = new Adapter_thinklist(this, R.layout.custom_listview_thinklist, array);
        listview.setAdapter(adapter);


    }


    private void Addwork(){
        if(edt_Opinion.getText().toString().equals("")){

            AlertDialog.Builder builder = new AlertDialog.Builder(thinklist.this);
            builder.setTitle("모든 정보를 반드시 입력하셔야 합니다.");
            builder.setMessage("년월일 : 입력선택사항 \nMyThink : 기본정보(반드시 입력) \nSource : 입력선택사항");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();

        }
        else {



            final String year = edt_Year.getText().toString();
            final String month = edt_Month.getText().toString();
            final String day = edt_Day.getText().toString();
            final String opinion = edt_Opinion.getText().toString();
            final String source = edt_Source.getText().toString();

            ThinkItem item = new ThinkItem(year,month,day,opinion,source);
            array.add(array.size(),item);
            edt_Year.setText("");
            edt_Month.setText("");
            edt_Day.setText("");
            edt_Opinion.setText("");
            edt_Source.setText("");
            edt_Opinion.requestFocus();



            //SAVE
            SharedPreferences sharedPreferences = getSharedPreferences(id,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(array);
            editor.putString("think list",json);
            editor.commit();
        }
    }

    private void Deletework() {

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).numberischecked()) {
                array.remove(i);
                i--;
            }
        }
        adapter.notifyDataSetChanged();
        //SAVE
        SharedPreferences sharedPreferences = getSharedPreferences(id,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(array);
        editor.putString("think list",json);
        editor.commit();
    }


    private void load(){
        SharedPreferences sharedPreferences = getSharedPreferences(id,MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("think list",null);
        Type type =new TypeToken<ArrayList<ThinkItem>>() {}.getType();
        array = gson.fromJson(json,type);
        if(array ==null){
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
        editor.putString("think list", json);
        editor.commit();
    }


}