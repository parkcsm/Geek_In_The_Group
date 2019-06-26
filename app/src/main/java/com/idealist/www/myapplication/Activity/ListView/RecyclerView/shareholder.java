package com.idealist.www.myapplication.Activity.ListView.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idealist.www.myapplication.Activity.IdealistMain;
import com.idealist.www.myapplication.Activity.interestpost;
import com.idealist.www.myapplication.Adapter.Adapter_sharelist;
import com.idealist.www.myapplication.Item.FriendItem;
import com.idealist.www.myapplication.Item.ShareItem;
import com.idealist.www.myapplication.R;
import com.idealist.www.myapplication.comment;
import com.idealist.www.myapplication.content_change;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 박종원 on 2018-02-25.
 */

public class shareholder extends RecyclerView.ViewHolder implements View.OnClickListener {


    boolean likecheck;
    static ArrayList<ShareItem> listItem = new ArrayList<>();
    public static final String EXTRA_URI_STRING = "IMAGE";
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_TEXT = "TEXT";
    public static final String EXTRA_POSITION = "POSITION";

    public CheckBox cbLike;
    public ImageView ivImg, iv_change, iv_delete, iv_comment;
    public TextView tvLikeCount, tvWriterName, tvWriterText, tvUserName, tvUserComment;
    private Adapter_sharelist mAdapter;
    private final Context context;

    public shareholder(View itemView, Adapter_sharelist adapter_sharelist) {
        super(itemView);
        context = itemView.getContext();
        mAdapter = adapter_sharelist;

        ivImg = itemView.findViewById(R.id.iv_img);
        iv_change = itemView.findViewById(R.id.iv_edit);
        iv_delete = itemView.findViewById(R.id.iv_delete);
        tvWriterName = itemView.findViewById(R.id.tv_writer);
        tvWriterText = itemView.findViewById(R.id.tv_writercoment);
        cbLike = itemView.findViewById(R.id.cb_like);
        tvLikeCount = itemView.findViewById(R.id.tv_like_count);
        iv_comment = itemView.findViewById(R.id.iv_comment);
//        tvUserName = itemView.findViewById(R.id.tv_username);
//        tvUserComment = itemView.findViewById(R.id.tv_posttext);


//        cbLike.setOnClickListener(this);
//        ivShare.setOnClickListener(this);


        iv_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WriteComment();
            }
        });
        cbLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int position = getAdapterPosition();
                ShareItem item = interestpost.listItem.get(position); //static!
                item.setPostchecked(isChecked);
            }
        });

        cbLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (((CheckBox) view).isChecked()) {
                    int position = getAdapterPosition();
                    ShareItem item = interestpost.listItem.get(position); //static!
                    String Uri_String = item.getUriString();
                    String id = item.getId();
                    String postText = item.getPostText();
                    boolean Like = true;
                    int Count = item.postLikeCount;
                    Count++;

                    ShareItem newitem = new ShareItem(Uri_String, id, postText, Like, Count);
                    listItem.remove(position);
                    listItem.add(position, newitem);


                    mAdapter = new Adapter_sharelist(context, listItem);
                    interestpost.rvList.setAdapter(mAdapter);
                } else {

                    int position = getAdapterPosition();
                    ShareItem item = interestpost.listItem.get(position); //static!

                    String Uri_String = item.getUriString();
                    String id = item.getId();
                    String postText = item.getPostText();
                    boolean Like = false;
                    int Count = item.postLikeCount;


                    Count--;

                    ShareItem newitem = new ShareItem(Uri_String, id, postText, Like, Count);
                    listItem.remove(position);
                    listItem.add(position, newitem);
                    mAdapter = new Adapter_sharelist(context, listItem);
                    interestpost.rvList.setAdapter(mAdapter);


                }
                //SAVE
                SharedPreferences sharedPreferences = context.getSharedPreferences(interestpost.interestname, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(listItem);
                editor.putString("postlist", json);
                editor.commit();

            }
        });

        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                ShareItem item = interestpost.listItem.get(position); //static!
                if (IdealistMain.id.equals(item.getId())) {

                    show();
                } else {
                    Toast.makeText(context, "글을 삭제할 수 있는 권한이 없습니다.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        iv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                ShareItem item = interestpost.listItem.get(position); //static!

                if (IdealistMain.id.equals(item.getId())) {
                    change();
                } else {
                    Toast.makeText(context, "글을 수정할 수 있는 권한이 없습니다.", Toast.LENGTH_SHORT).show();

                }

            }
        });


        //load
        SharedPreferences sharedPreferences = context.getSharedPreferences(interestpost.interestname, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
        Gson gson = new Gson();
        String json = sharedPreferences.getString("postlist", null);
        Type type = new TypeToken<ArrayList<ShareItem>>() {
        }.getType();
        listItem = gson.fromJson(json, type);


    }

    private void delete() {
        int position = getAdapterPosition();
        listItem.remove(position);
        mAdapter = new Adapter_sharelist(context, listItem);
        interestpost.rvList.setAdapter(mAdapter);

        //SAVE
        SharedPreferences sharedPreferences = context.getSharedPreferences(interestpost.interestname, MODE_PRIVATE); // 로그인한 id 저장소를 불러오기, 이 때 불려와지는 friend list는 각각 다름
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listItem);
        editor.putString("postlist", json);
        editor.commit();
        Toast.makeText(context, "글이 삭제되었습니다.", Toast.LENGTH_SHORT).show();

    }

    private void change() {
        int position = getAdapterPosition();

        ShareItem item = interestpost.listItem.get(position); //static!

        Intent myintent = new Intent(context, content_change.class);
        myintent.putExtra(EXTRA_ID, item.getId());
        myintent.putExtra(EXTRA_TEXT, item.getPostText());
        myintent.putExtra(EXTRA_URI_STRING, item.getUriString());
        myintent.putExtra(EXTRA_POSITION, position);
        this.context.startActivity(myintent);
    }

    private void WriteComment() {
        final Intent myintent;


        switch (getAdapterPosition()) {
            case 0:
                myintent = new Intent(context, comment.class);
                break;

            case 1:
                myintent = new Intent(context, comment.class);
                break;

            default:
                myintent = new Intent(context, comment.class);
                break;
        }
        int position = getAdapterPosition();


        ShareItem item = interestpost.listItem.get(position); //static!
        myintent.putExtra(EXTRA_URI_STRING, item.getUriString());
        myintent.putExtra(EXTRA_ID, item.getId());
        myintent.putExtra(EXTRA_TEXT, item.getPostText());
        myintent.putExtra("interestsubject", interestpost.interestname);

        this.context.startActivity(myintent);
        //this가 핵심이었네!!

    }


//    @Override
//    public void onClick(View view) {
//
//        int position = getAdapterPosition();
//
//        switch (view.getId()) {
//            case R.id.cb_like:
//                mAdapter.onLikeClicked(position);
//                break;
//
//            case R.id.iv_share:
//
//                break;
//        }

    @Override
    public void onClick(View view) {
        WriteComment();

    }

    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h = (int) (newHeight * densityMultiplier);
        int w = (int) (h * photo.getWidth() / ((double) photo.getHeight()));

        photo = Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }

    private String getStringFromBitmap(Bitmap bitmapPicture) {
 /*
 * This functions converts Bitmap picture to a string which can be
 * JSONified.
 * */
        final int COMPRESSION_QUALITY = 100;
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
                byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("해당 글을 삭제하시겠습니까?");
        builder.setMessage("삭제된 정보는 복구불가능합니다.");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        delete();
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

