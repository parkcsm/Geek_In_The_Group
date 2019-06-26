package com.idealist.www.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.idealist.www.myapplication.R;

public class registerfinish extends AppCompatActivity implements View.OnClickListener{


boolean 초기화;

    public static final String TAG = "registerfinish";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerfinish);

        Button button_login= findViewById(R.id.Button_login_from_registerfinish);
        button_login.setOnClickListener(this);

//    informationsave.outputText1 = (TextView) findViewById(R.id.outputText1);
//    informationsave.outputText1.setText(getIntent().getExtras().getString("data1"));
//
//    informationsave.outputText2 = (TextView) findViewById(R.id.outputText2);
//    informationsave.outputText2.setText(getIntent().getExtras().getString("data2"));
//
//    informationsave.outputText3 = (TextView) findViewById(R.id.outputText3);
//    informationsave.outputText3.setText(getIntent().getExtras().getString("data3"));
//
//    informationsave.outputText4 = (TextView) findViewById(R.id.outputText4);
//    informationsave.outputText4.setText(getIntent().getExtras().getString("data4"));

     Log.d(TAG,"onCreate");

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.Button_login_from_registerfinish){
            Intent login = new Intent(registerfinish.this, com.idealist.www.myapplication.Activity.login.class);
            startActivity(login);
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            finish();

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
//
//            informationsave.outputText1.setText("초기화 되었습니다.");
//            informationsave.outputText2.setText("초기화 되었습니다.");
//            informationsave.outputText3.setText("초기화 되었습니다.");
//            informationsave.outputText4.setText("초기화 되었습니다.");

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
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}
