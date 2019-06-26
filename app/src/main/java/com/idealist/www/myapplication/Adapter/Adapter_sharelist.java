package com.idealist.www.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.idealist.www.myapplication.Activity.ListView.RecyclerView.shareholder;
import com.idealist.www.myapplication.Item.ShareItem;
import com.idealist.www.myapplication.R;

import java.util.ArrayList;

/**
 * Created by 박종원 on 2018-02-25.
 */

public class Adapter_sharelist extends RecyclerView.Adapter<shareholder> {

    public boolean onBind;
    private Activity mContext;
    private ArrayList<ShareItem> shareItems;


    public Adapter_sharelist(Context context, ArrayList<ShareItem> listItem) {
        mContext = (Activity) context;
        shareItems = listItem;
    }

    @Override
    public shareholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View baseView = View.inflate(mContext, R.layout.custom_recyclerview_sharelist, null);
        shareholder shareHolder = new shareholder(baseView, this);

        return shareHolder;
    }


    @Override
    public void onBindViewHolder(shareholder holder, int position) {

        ShareItem item = shareItems.get(position);

        Glide.with(mContext).load(item.getUri()).centerCrop().into(holder.ivImg);
        holder.tvWriterName.setText(item.getId());
        holder.tvWriterText.setText(item.getPostText());
        holder.cbLike.setChecked(item.getisUserLike());
        onBind=true;
        holder.tvLikeCount.setText(String.valueOf(item.getPostLikeCount()));
        onBind = false;
    }

    @Override
    public int getItemCount() {
        return shareItems.size();
    }

    public void onLikeClicked(int position) {
        ShareItem item = shareItems.get(position);
        Toast.makeText(mContext, position + " : " + item.getPostText(), Toast.LENGTH_LONG).show();
    }

    public void like(int position) {
        ShareItem item = shareItems.get(position);


        if (item.isUserLike == true) {
            item.postLikeCount++;

            if (onBind==false) {
                notifyDataSetChanged();
            }
        } else if (item.isUserLike == false) {
            item.postLikeCount--;

            if (onBind==false) {
                notifyDataSetChanged();
            }
        }


    }

}
