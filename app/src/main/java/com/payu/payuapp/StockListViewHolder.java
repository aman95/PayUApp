package com.payu.payuapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by nitin on 9/5/15.
 */
public class StockListViewHolder extends RecyclerView.ViewHolder {

    protected NetworkImageView companyLogo;
    protected TextView companyName;

    public StockListViewHolder(View view) {
        super(view);
        this.companyLogo = (NetworkImageView) view.findViewById(R.id.company_logo);
        this.companyName = (TextView) view.findViewById(R.id.company_name);

    }

}
