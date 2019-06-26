package com.idealist.www.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.idealist.www.myapplication.Item.FriendItem;
import com.idealist.www.myapplication.R;

import java.util.ArrayList;

/**
 * Created by 박종원 on 2018-02-06.
 */

public class Adapter_friendlist extends ArrayAdapter<FriendItem> {

    private FragmentActivity context;
    private int id;
    ArrayList<FriendItem> array;

    public Adapter_friendlist(Context context, int resource, ArrayList<FriendItem> objects){
        super(context,resource,objects);
        this.context = (FragmentActivity) context;
        this.id = resource;
        this.array = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(id,null);
        }
        final FriendItem friendItem =array.get(position);

        TextView Friend_txt_number = (TextView) convertView.findViewById(R.id.Friend_txt_Number);
        TextView Friend_txt_name = (TextView) convertView.findViewById(R.id.Friend_txt_Name);
        TextView Friend_txt_major = (TextView) convertView.findViewById(R.id.Friend_txt_Major);
        TextView Friend_txt_mail = (TextView) convertView.findViewById(R.id.Friend_txt_Mail);
        TextView Friend_txt_phonenumber =(TextView)  convertView.findViewById(R.id.Friend_txt_PhonNumber);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.Friend_CheckBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                friendItem.setIschecked(isChecked);

            }
        });


        int positionset = position+1;
        Friend_txt_number.setText(positionset+""); // ""을 넣음으로써 스트링화시켜준다.
        Friend_txt_name.setText(friendItem.getName()); //friendItem.getName()이 String이기 때문에, toString을 빼줘도 된다.
        Friend_txt_major.setText(friendItem.getMajor().toString());
        Friend_txt_mail.setText(friendItem.getMail().toString());
        Friend_txt_phonenumber.setText(friendItem.getPhonenumber().toString());
        checkBox.setChecked(friendItem.ischecked());

        return convertView;
    }
}
