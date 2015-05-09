package com.payu.payuapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.payu.payuapp.AppController;
import com.payu.payuapp.R;

import java.util.List;

/**
 * Created by nitin on 10/5/15.
 */
public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardViewHolder>  {

    private List<LeaderboardFeedItem> feedItemList;

    private Context mContext;

    public LeaderboardAdapter(Context context, List<LeaderboardFeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }



    @Override
    public LeaderboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.leader_board_item, parent,false);
        LeaderboardViewHolder mh = new LeaderboardViewHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(LeaderboardViewHolder holder, int position) {

        LeaderboardFeedItem feedItem = feedItemList.get(position);

        holder.userAvatar.setImageUrl("http://lorempixel.com/50/50", AppController.getInstance().getImageLoader());

        holder.userName.setText(feedItem.getUserName());

    }

    @Override
    public int getItemCount() {


        return (null != feedItemList ? feedItemList.size() : 0);
    }
}
