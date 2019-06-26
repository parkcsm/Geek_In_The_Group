package com.idealist.www.myapplication.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.idealist.www.myapplication.Item.ShareItem;
import com.idealist.www.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    ScrollView mbtiview;
    ScrollView wpiview;
    ScrollView enneagramview;

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ShareItem> listItem = new ArrayList<>();
//        Toast.makeText(this, listItem.toString(), Toast.LENGTH_SHORT).show();


        Button button_register= findViewById(R.id.Button_register);
       button_register.setOnClickListener(this);

        Button button_login= findViewById(R.id.Button_login);
        button_login.setOnClickListener(this);

        Button button_gombti= findViewById(R.id.gombti);
        button_gombti.setOnClickListener(this);

        Button button_gowpi= findViewById(R.id.gowpi);
        button_gowpi.setOnClickListener(this);

        Button button_goenneagram= findViewById(R.id.goenneagram);
        button_goenneagram.setOnClickListener(this);

        Button button_mbtiexplanation = findViewById(R.id.mbtiexplanation);
        button_mbtiexplanation.setOnClickListener(this);

        Button button_wpiexplanation = findViewById(R.id.wpiexplanation);
        button_wpiexplanation.setOnClickListener(this);

        Button button_enneagramexplanation = findViewById(R.id.enneagramexplanation);
        button_enneagramexplanation.setOnClickListener(this);

        mbtiview = findViewById(R.id.mbtiview);
        wpiview = findViewById(R.id.wpiview);
        enneagramview = findViewById(R.id.enneagramview);

        Log.d(TAG,"Oncreat");

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG,"Onclick");

        if(view.getId()==R.id.Button_register) {
           Intent register = new Intent(MainActivity.this, com.idealist.www.myapplication.Activity.register.class);
           startActivity(register);
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            Toast.makeText(this, "회원가입을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
        }

        if(view.getId()==R.id.Button_login){

            if(setting.loginsuccess==true){
                Intent myintent = new Intent(this,IdealistMain.class);
                startActivity(myintent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);

            } else {
                Intent login = new Intent(MainActivity.this, com.idealist.www.myapplication.Activity.login.class);
                startActivity(login);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                Toast.makeText(this, "로그인을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        }

        if(view.getId()==R.id.gombti){
            Intent gombti = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.16personalities.com/ko"));
            startActivity(gombti);
        }

        if(view.getId()==R.id.gowpi){
            Intent gowpi = new Intent(Intent.ACTION_VIEW, Uri.parse("https://check.wisdomcenter.co.kr/checkup/test/detail.htm?id=1"));
            startActivity(gowpi);
        }

        if(view.getId()==R.id.goenneagram){
            Intent goenneagram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://enneagram-app.appspot.com/quest"));
            startActivity(goenneagram);
        }



        if(view.getId()==R.id.mbtiexplanation){
            mbtiview.setVisibility(View.VISIBLE);
            wpiview.setVisibility(View.INVISIBLE);
            enneagramview.setVisibility(View.INVISIBLE);
        }

        if(view.getId()==R.id.wpiexplanation){
            mbtiview.setVisibility(View.INVISIBLE);
            wpiview.setVisibility(View.VISIBLE);
            enneagramview.setVisibility(View.INVISIBLE);
        }

        if(view.getId()==R.id.enneagramexplanation){
            mbtiview.setVisibility(View.INVISIBLE);
            wpiview.setVisibility(View.INVISIBLE);
            enneagramview.setVisibility(View.VISIBLE);
        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"OnStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"OnRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"OnResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"OnDestroy");
    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("정말 어플을 종료 하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();

    }
}
