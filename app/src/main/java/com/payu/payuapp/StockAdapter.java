package com.payu.payuapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by nitin on 9/5/15.
 */
public class StockAdapter extends RecyclerView.Adapter<StockListViewHolder> {

        private List<FeedItem> feedItemList;

        private Context mContext;

        public StockAdapter(Context context, List<FeedItem> feedItemList) {
                this.feedItemList = feedItemList;
                this.mContext = context;
        }


        @Override
        public StockListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_info, null);
                StockListViewHolder mh = new StockListViewHolder(v);

                return mh;
        }

        @Override
        public void onBindViewHolder(StockListViewHolder holder, int position) {

                FeedItem feedItem = feedItemList.get(position);

                holder.companyLogo.setImageUrl("http://lorempixel.com/50/50", AppController.getInstance().getImageLoader());

                holder.companyName.setText(feedItem.getCompanyName());

        }

        @Override
        public int getItemCount() {


                return (null != feedItemList ? feedItemList.size() : 0);
        }
}
