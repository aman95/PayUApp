package com.payu.payuapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.payu.payuapp.R;

/**
 * Created by nitin on 10/5/15.
 */
public class LeaderboardViewHolder extends RecyclerView.ViewHolder{

    protected NetworkImageView userAvatar;
    protected TextView userName;

    public LeaderboardViewHolder(View view) {
        super(view);
        this.userAvatar = (NetworkImageView) view.findViewById(R.id.user_avatar);
        this.userName = (TextView) view.findViewById(R.id.user_name);

    }
}
