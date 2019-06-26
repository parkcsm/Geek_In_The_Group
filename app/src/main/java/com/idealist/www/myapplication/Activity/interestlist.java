package com.idealist.www.myapplication.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.idealist.www.myapplication.R;

public class interestlist extends AppCompatActivity {

    ProgressDialog simpleWaitDialog;
    String id;
    Button btn_itrtprt;
    EditText edt_subject1, edt_subject2, edt_subject3, edt_subject4, edt_subject5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interestlist);

        SharedPreferences Sharepref = getSharedPreferences("Operation",MODE_PRIVATE);
        id = Sharepref.getString("Operation",null);

        edt_subject1 = findViewById(R.id.edt_subject1);
        edt_subject2 = findViewById(R.id.edt_subject2);
        edt_subject3 = findViewById(R.id.edt_subject3);
        edt_subject4 = findViewById(R.id.edt_subject4);
        edt_subject5 = findViewById(R.id.edt_subject5);
        btn_itrtprt = findViewById(R.id.btn_itrtprt);

        btn_itrtprt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               PostTask postTask = new PostTask();
                postTask.execute();
            }
        });


        load();
    }



    private void itrtprt_save() {
        SharedPreferences sharedPreferences = getSharedPreferences(id,MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("interest1",edt_subject1.getText().toString());
        editor.putString("interest2",edt_subject2.getText().toString());
        editor.putString("interest3",edt_subject3.getText().toString());
        editor.putString("interest4",edt_subject4.getText().toString());
        editor.putString("interest5",edt_subject5.getText().toString());
        editor.commit();

    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences(id,MODE_PRIVATE);
        edt_subject1.setText(sharedPreferences.getString("interest1","관심사 1"));
        edt_subject2.setText(sharedPreferences.getString("interest2","관심사 2"));
        edt_subject3.setText(sharedPreferences.getString("interest3","관심사 3"));
        edt_subject4.setText(sharedPreferences.getString("interest4","관심사 4"));
        edt_subject5.setText(sharedPreferences.getString("interest5","관심사 5"));
    }

    class PostTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            simpleWaitDialog = ProgressDialog.show(interestlist.this, "잠시만 기다려주세요", "정보 저장중...");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            itrtprt_save();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            simpleWaitDialog.dismiss();
            Intent myintent = new Intent(interestlist.this,IdealistMain.class);
            startActivity(myintent);
            Toast.makeText(interestlist.this, "관심사정보가 저장되었습니다.", Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_slide_out_top);

    }
}
