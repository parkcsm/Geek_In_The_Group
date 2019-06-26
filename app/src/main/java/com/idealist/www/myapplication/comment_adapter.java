package com.idealist.www.myapplication;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 박종원 on 2018-03-05.
 */

public class comment_adapter extends RecyclerView.Adapter<comment_holder> {


    private Activity Context;
    private ArrayList<comment_Item> ListItem;

    public comment_adapter(android.content.Context context, ArrayList<comment_Item> listItem) {
        Context = (Activity) context;
        ListItem = listItem;
    }

    @Override
    public comment_holder onCreateViewHolder(ViewGroup parent, int viewType) {


        View baseView = View.inflate(Context, R.layout.comment_view, null);
        comment_holder comment_holder = new comment_holder(baseView, this);
        return comment_holder;

    }

    @Override
    public void onBindViewHolder(comment_holder holder, int position) {

        comment_Item item = ListItem.get(position);

        holder.cvrv_id.setText(item.getId());
        holder.cvrv_tv.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return ListItem.size();
    }
}
