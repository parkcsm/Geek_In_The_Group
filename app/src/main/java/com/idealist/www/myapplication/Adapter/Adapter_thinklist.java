package com.idealist.www.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.idealist.www.myapplication.R;
import com.idealist.www.myapplication.Item.ThinkItem;

import java.util.ArrayList;

/**
 * Created by 박종원 on 2018-02-08.
 */

public class Adapter_thinklist extends ArrayAdapter<ThinkItem> {
    private Activity context;
    private int id;
    ArrayList<ThinkItem> array;


    public Adapter_thinklist(Context context, int resource, ArrayList<ThinkItem> objects){
        super(context,resource,objects);
        this.context = (Activity) context;
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
        final ThinkItem thinkItem =array.get(position);

        TextView Think_txt_Number = (TextView) convertView.findViewById(R.id.Think_txt_Number);
        TextView Think_txt_Year = (TextView) convertView.findViewById(R.id.Think_txt_Year);
        TextView Think_txt_month = (TextView) convertView.findViewById(R.id.Think_txt_month);
        TextView Think_txt_day = (TextView) convertView.findViewById(R.id.Think_txt_day);
        TextView Think_txt_Opinion =(TextView)  convertView.findViewById(R.id.Think_txt_Opinion);
        TextView Think_txt_Source =(TextView)  convertView.findViewById(R.id.Think_txt_Source);

        CheckBox Think_CheckBox = (CheckBox) convertView.findViewById(R.id.Think_CheckBox);
        CheckBox Think_ChecBox_share = (CheckBox) convertView.findViewById(R.id.Think_share); //나중에 처리
        Think_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                thinkItem.setNumberischecked(isChecked);
            }
        });

        Think_ChecBox_share.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                thinkItem.setShareischecked(isChecked);
            }
        });


        int positionset = position+1;
        Think_txt_Number.setText(positionset+""); // ""을 넣음으로써 스트링화시켜준다.
        Think_txt_Year.setText(thinkItem.getYear()); //friendItem.getName()이 String이기 때문에, toString을 빼줘도 된다.
        Think_txt_month.setText(thinkItem.getMonth().toString());
        Think_txt_day.setText(thinkItem.getDay().toString());
        Think_txt_Opinion.setText(thinkItem.getOpinion().toString());
        Think_txt_Source.setText(thinkItem.getSource().toString());


        Think_CheckBox.setChecked(thinkItem.numberischecked());
        Think_ChecBox_share.setChecked(thinkItem.shareischecked());

        return convertView;
    }
}

