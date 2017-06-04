package com.praveen_rewar.s_mart;


import android.app.LauncherActivity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.praveen_rewar.s_mart.R.id.item_name;
import static com.praveen_rewar.s_mart.R.id.item_price;
//import static com.praveen_rewar.s_mart.R.id.price;

/**
 * Created by praveen on 3/6/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private List<ItemList> itemLists;
    private Context context;

    public RecyclerAdapter(List<ItemList> itemList, Context context) {
        this.itemLists = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        System.out.println("Kuch to hua hai"+getItemCount());
        ItemList itemList = itemLists.get(position);
        holder.item_name.setText(itemLists.get(position).getItem());
        holder.item_price.setText(itemLists.get(position).getPrice());
        Glide.with(context).load(itemLists.get(position).getImage()).into(holder.item_image);
    }

    @Override
    public int getItemCount() {
        return itemLists.size();
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView item_name,item_price;
        ImageView item_image;
        public RecyclerViewHolder(View view){
            super(view);
            item_name = (TextView) view.findViewById(R.id.item_name);
            item_price = (TextView) view.findViewById(R.id.item_price);
            item_image = (ImageView) view.findViewById(R.id.item_image);
        }
    }
}

