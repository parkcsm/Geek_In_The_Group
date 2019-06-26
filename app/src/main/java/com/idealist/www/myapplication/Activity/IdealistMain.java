package com.idealist.www.myapplication.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.idealist.www.myapplication.R;


public class IdealistMain extends AppCompatActivity implements View.OnClickListener {

//    LinearLayout profile_interest;
//    TabLayout tabLayout;
//    ViewPager viewPager;

    public static String id;

    Uri uri;
    String Uri_String;

    Bitmap bitmap;
    LinearLayout Li_nickname, Li_interestlist, Li_logout;
    ImageView profile;
    TextView InitialActivity, nickname;
    TextView tv_interest1, tv_interest2, tv_interest3, tv_interest4, tv_interest5;
    ImageView friendlist, thinklist, sharelist;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idealist_main);

//        profile_interest = findViewById(R.id.profile_interest);


        InitialActivity = findViewById(R.id.gomain);
        InitialActivity.setOnClickListener(this);

        nickname = findViewById(R.id.Button_nickname);
        Li_nickname = findViewById(R.id.Li_nickname);
        Li_nickname.setOnClickListener(this);

        profile = findViewById(R.id.Button_profilechange);
        profile.setOnClickListener(this);

        Li_logout = findViewById(R.id.Li_logout);
        Li_logout.setOnClickListener(this);

        Li_interestlist = findViewById(R.id.Li_interest);
        Li_interestlist.setOnClickListener(this);

        tv_interest1 = findViewById(R.id.interest1);
        tv_interest2 = findViewById(R.id.interest2);
        tv_interest3 = findViewById(R.id.interest3);
        tv_interest4 = findViewById(R.id.interest4);
        tv_interest5 = findViewById(R.id.interest5);


//        friendlist = findViewById(R.id.Button_friend);
//        friendlist.setOnClickListener(this);
//
//        thinklist = findViewById(R.id.Button_thinking);
//        thinklist.setOnClickListener(this);
//
//        sharelist = findViewById(R.id.Button_share);
//        sharelist.setOnClickListener(this);

        load();

        /** 로그인시 환영합니다 메시지 컨트롤*/
        if (setting.loginsuccess == false) {
            Toast.makeText(this, id + "님 환영합니다!", Toast.LENGTH_SHORT).show();
        }
        setting.loginsuccess = true;


        final ImageView iv1 = findViewById(R.id.imageView1);
        final ImageView iv2 = findViewById(R.id.imageView2);
        final ImageView iv3 = findViewById(R.id.imageView3);
        final ImageView iv4 = findViewById(R.id.imageView4);
        final ImageView iv5 = findViewById(R.id.imageView5);
        final ImageView iv6 = findViewById(R.id.imageView6);
        final ImageView iv7 = findViewById(R.id.imageView7);
        final ImageView iv8 = findViewById(R.id.imageView8);
        final ImageView iv9 = findViewById(R.id.imageView9);


        final AnimationDrawable animationDrawable = (AnimationDrawable) iv1.getBackground();
        if (animationDrawable.isRunning()) {
            animationDrawable.stop();
        } else {
            animationDrawable.start();
        }


        final AnimationDrawable animationDrawable2 = (AnimationDrawable) iv2.getBackground();
        if (animationDrawable2.isRunning()) {
            animationDrawable2.stop();
        } else {
            animationDrawable2.start();
        }

        final AnimationDrawable animationDrawable3 = (AnimationDrawable) iv3.getBackground();
        if (animationDrawable3.isRunning()) {
            animationDrawable3.stop();
        } else {
            animationDrawable3.start();
        }

        final AnimationDrawable animationDrawable4 = (AnimationDrawable) iv4.getBackground();
        if (animationDrawable4.isRunning()) {
            animationDrawable4.stop();
        } else {
            animationDrawable4.start();
        }

        final AnimationDrawable animationDrawable5 = (AnimationDrawable) iv5.getBackground();
        if (animationDrawable5.isRunning()) {
            animationDrawable5.stop();
        } else {
            animationDrawable5.start();
        }

        final AnimationDrawable animationDrawable6 = (AnimationDrawable) iv6.getBackground();
        if (animationDrawable6.isRunning()) {
            animationDrawable6.stop();
        } else {
            animationDrawable6.start();
        }

        final AnimationDrawable animationDrawable7 = (AnimationDrawable) iv7.getBackground();
        if (animationDrawable7.isRunning()) {
            animationDrawable7.stop();
        } else {
            animationDrawable7.start();
        }

        final AnimationDrawable animationDrawable8 = (AnimationDrawable) iv8.getBackground();
        if (animationDrawable8.isRunning()) {
            animationDrawable8.stop();
        } else {
            animationDrawable8.start();
        }

        final AnimationDrawable animationDrawable9 = (AnimationDrawable) iv9.getBackground();
        if (animationDrawable9.isRunning()) {
            animationDrawable9.stop();
        } else {
            animationDrawable9.start();
        }


        /** 타임라인 뷰페이저 컨트롤*/

//        tabLayout = findViewById(R.id.tl_Tab);
//        viewPager = findViewById(R.id.vp_Pager);
//
//        Fragment[] arrFragments = new Fragment[5];
//        arrFragments[0] = new interest1post();
//        arrFragments[1] = new interest2post();
//        arrFragments[2] = new interest3post();
//        arrFragments[3] = new interest4post();
//        arrFragments[4] = new interest5post();
//        arrFragments[5] = new interest6post();


//        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), arrFragments);
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 옵션
     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.up:
//                InitialActivity.setVisibility(View.GONE);
//                profile_interest.setVisibility(View.GONE);
//                tabLayout.setVisibility(View.VISIBLE);
//                viewPager.setVisibility(View.VISIBLE);
//                return true;
//            case R.id.down:
//                InitialActivity.setVisibility(View.VISIBLE);
//                profile_interest.setVisibility(View.VISIBLE);
//                tabLayout.setVisibility(View.GONE);
//                viewPager.setVisibility(View.GONE);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.gomain) {
            Intent gomain = new Intent(this, MainActivity.class);
            startActivity(gomain);
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            //메인으로 돌아가기
        }

        if (view.getId() == R.id.Li_nickname) {
            Intent myIntent = new Intent(this, privateinformation.class);
            startActivity(myIntent);
            overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);

            //개인정보보기
        }

        if (view.getId() == R.id.Li_logout) {
            show();
            // 로그아웃하기(어플종료), 아직 실제 어플 종료는 구현 안됨
        }

//        if (view.getId() == R.id.Button_friend) {
//            Intent myIntent = new Intent(this, friendlist.class);
//            startActivity(myIntent);
//            // 친구목록
//        }
//
//        if (view.getId() == R.id.Button_thinking) {
//            Intent myIntent = new Intent(this, thinklist.class);
//            startActivity(myIntent);
//            // 생각목록
//        }
//
//        if (view.getId() == R.id.Button_share) {
//            Intent myIntent = new Intent(this, sharelist.class);
//            startActivity(myIntent);
//            //글 읽으러가기
//        }


        if (view.getId() == R.id.Button_profilechange) {

            Intent myintent = new Intent(this, profileinfo.class);
            startActivity(myintent);
            overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
        }

        if (view.getId() == R.id.Li_interest) {
            Intent myintent = new Intent(this, interestlist.class);
            startActivity(myintent);
            overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
        }
    }


    private Bitmap getBitmapFromString(String json) {
/*
* This Function converts the String back to Bitmap
* */

        //스트링에서 비트맵으로 바꿀 필요만있음 (Load) ->프로필사진만 등록해주면 되기 때문에
        byte[] decodedString = Base64.decode(json, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    private void load() {

        SharedPreferences sharedPref = getSharedPreferences("Operation", MODE_PRIVATE);
        id = sharedPref.getString("Operation", null);
        nickname.setText(id, null);

        SharedPreferences sharedPreferences = getSharedPreferences(id, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
        if (sharedPreferences.getString("profilephoto", null) != null) {
            String savefile = sharedPreferences.getString("profilephoto", null);
            Uri_String = savefile;
            uri = Uri.parse(Uri_String);
            Glide.with(this).load(uri).centerCrop().into(profile);
        }

        final SharedPreferences interestlist = getSharedPreferences(id, MODE_PRIVATE);

        final String str_interest1;
        str_interest1 = interestlist.getString("interest1", "관심사 1");
        tv_interest1.setText(str_interest1);
        tv_interest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_interest1.getText().toString().equals("") || tv_interest1.getText().toString().equals("관심사 1")) {
                    Toast.makeText(IdealistMain.this, "관심사를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Intent myintent = new Intent(IdealistMain.this, interestpost.class);
                    myintent.putExtra("interestsubject", str_interest1);
                    startActivity(myintent);
                    overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
                }

            }
        });

        final String str_interest2;
        str_interest2 = interestlist.getString("interest2", "관심사 2");
        tv_interest2.setText(str_interest2);
        tv_interest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_interest2.getText().toString().equals("") || tv_interest2.getText().toString().equals("관심사 2")) {
                    Toast.makeText(IdealistMain.this, "관심사를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Intent myintent = new Intent(IdealistMain.this, interestpost.class);
                    myintent.putExtra("interestsubject", str_interest2);
                    startActivity(myintent);
                    overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
                }
            }
        });


        final String str_interest3;
        str_interest3 = interestlist.getString("interest3", "관심사 3");
        tv_interest3.setText(str_interest3);
        tv_interest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_interest3.getText().toString().equals("") || tv_interest3.getText().toString().equals("관심사 3")) {
                    Toast.makeText(IdealistMain.this, "관심사를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Intent myintent = new Intent(IdealistMain.this, interestpost.class);
                    myintent.putExtra("interestsubject", str_interest3);
                    startActivity(myintent);
                    overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
                }
            }
        });


        final String str_interest4;
        str_interest4 = interestlist.getString("interest4", "관심사 4");
        tv_interest4.setText(str_interest4);
        tv_interest4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_interest4.getText().toString().equals("") || tv_interest4.getText().toString().equals("관심사 4")) {
                    Toast.makeText(IdealistMain.this, "관심사를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Intent myintent = new Intent(IdealistMain.this, interestpost.class);
                    myintent.putExtra("interestsubject", str_interest4);
                    startActivity(myintent);
                    overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
                }
            }
        });


        final String str_interest5;
        str_interest5 = interestlist.getString("interest5", "관심사 5");
        tv_interest5.setText(str_interest5);
        tv_interest5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_interest5.getText().toString().equals("") || tv_interest5.getText().toString().equals("관심사 5")) {
                    Toast.makeText(IdealistMain.this, "관심사를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Intent myintent = new Intent(IdealistMain.this, interestpost.class);
                    myintent.putExtra("interestsubject", str_interest5);
                    startActivity(myintent);
                    overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
                }
            }
        });


    }

//    private class MyFragmentAdapter extends FragmentPagerAdapter {
//
//        private Fragment[] arrFragments;
//
//        public MyFragmentAdapter(FragmentManager supportFragmentManager, Fragment[] arrFragments) {
//            super(supportFragmentManager);
//
//            this.arrFragments = arrFragments;
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return arrFragments[position];
//        }
//
//        @Override
//        public int getCount() {
//            return arrFragments.length;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//
//            SharedPreferences sharedPreferences = getSharedPreferences(id,MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//
//            switch(position){
//                case 0:
//                    if(sharedPreferences.getString("interestpost","").equals("")){
//                        editor.putString("interestpost","관심사 1");
//                        editor.commit();
//                    }
//                    return sharedPreferences.getString("interestpost","관심사 1");
//                case 1:
//                    if(sharedPreferences.getString("interest2","").equals("")){
//                        editor.putString("interest2","관심사 2");
//                        editor.commit();
//                    }
//                    return  sharedPreferences.getString("interest2","관심사 2");
//                case 2:
//                    if(sharedPreferences.getString("interest3","").equals("")){
//                        editor.putString("interest3","관심사 3");
//                        editor.commit();
//                    }
//                    return sharedPreferences.getString("interest3","관심사 3");
//                case 3:
//                    if(sharedPreferences.getString("interest4","").equals("")){
//                        editor.putString("interest4","관심사 4");
//                        editor.commit();
//                    }
//                    return sharedPreferences.getString("interest4","관심사 4");
//                case 4:
//                    if(sharedPreferences.getString("interest5","").equals("")){
//                        editor.putString("interest5","관심사 5");
//                        editor.commit();
//                    }
//                    return sharedPreferences.getString("interest5","관심사 5");
//                default:
//                    return "";
//            }
//        }
//    }


    @Override
    protected void onRestart() {
        super.onRestart();
        load();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }


    void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("정말 로그아웃 하시겠습니까?");
        builder.setMessage("로그아웃시 자동으로 정보가 저장됩니다.");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
//                        moveTaskToBack(true);
                        finish();
                        setting.loginsuccess = false;
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
