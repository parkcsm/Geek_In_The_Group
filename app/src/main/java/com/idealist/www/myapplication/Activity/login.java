package com.idealist.www.myapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.idealist.www.myapplication.R;

import java.util.Random;

public class login extends AppCompatActivity {

    String loginid, loginpswd;

    EditText edtlg_id, edtlg_pswd;
    Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtlg_id = findViewById(R.id.edtlg_id);
        edtlg_pswd = findViewById(R.id.edtlg_pswd);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }


    private void login() {


        loginid = edtlg_id.getText().toString();
        loginpswd = edtlg_pswd.getText().toString();

        SharedPreferences Sharedpref = getSharedPreferences("pswd", MODE_PRIVATE);

            if (Sharedpref.getString(loginid, "randomvalue911013").equals(loginpswd)) {

                SharedPreferences Operation = getSharedPreferences("Operation", MODE_PRIVATE);
                SharedPreferences.Editor OperationEdit = Operation.edit();
                OperationEdit.putString("Operation", loginid);
                OperationEdit.commit();


                Intent myintent = new Intent(login.this, IdealistMain.class);
                startActivity(myintent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                finish();
            } else {
                Toast.makeText(this, "아이디와 비밀번호가 일치하지 않습니다. 다시 입력해주세요!", Toast.LENGTH_SHORT).show();

            }


    }


    private void register() {
        Intent myintent = new Intent(login.this, register.class);
        startActivity(myintent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        finish();
    }

//    @Override
//    public void onClick(View view) {
//
//        if(view.getId()==R.id.Goto_idealistmain){
//            Intent Goto_idealistmain = new Intent(login.this, IdealistMain.class);
//            startActivity(Goto_idealistmain);
//        }
//
//        if(view.getId()==R.id.Button_register_fromlogin){
//            Intent register = new Intent(login.this, com.idealist.www.myapplication.Activity.register.class);
//            startActivity(register);
//        }
//
//    }

    @Override
    protected void onStop() {
        super.onStop();
//
//        edtlg_id.setText("");
//        edtlg_pswd.setText("");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

}
