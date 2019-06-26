package com.idealist.www.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 박종원 on 2018-03-05.
 */

public class comment_holder extends RecyclerView.ViewHolder {

    private  final Context context;
    private comment_adapter madapter;

    TextView cvrv_id;
    TextView cvrv_tv;

    public comment_holder(View itemView, final comment_adapter comment_adapter) {
        super(itemView);

        madapter = comment_adapter;
        context = itemView.getContext();

        cvrv_id = itemView.findViewById(R.id.cvrv_id);
        cvrv_tv = itemView.findViewById(R.id.cvrv_tv);
    }
}
