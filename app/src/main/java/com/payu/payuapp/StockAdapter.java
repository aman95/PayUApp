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
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_info, parent, false);
                StockListViewHolder mh = new StockListViewHolder(v);

                return mh;
        }

        @Override
        public void onBindViewHolder(StockListViewHolder holder, int position) {

                FeedItem feedItem = feedItemList.get(position);

                holder.companyLogo.setImageUrl("http://10.100.86.148/payuapi/public/logos/"+feedItem.getId()+".png", AppController.getInstance().getImageLoader());

                holder.companyName.setText(feedItem.getCompanyName());
                holder.ticker.setText(feedItem.getTicker());
                holder.price.setText(feedItem.getPrice());
                holder.low.setText(feedItem.getLow());
                holder.high.setText(feedItem.getHigh());
                holder.companyLogo.setTag(feedItem.getId());

        }

        @Override
        public int getItemCount() {


                return (null != feedItemList ? feedItemList.size() : 0);
        }

        /*@Override
        public void onClick(View v) {

                Toast.makeText(v.getContext(), "The Item Clicked is: " + getPosition(), Toast.LENGTH_SHORT).show();

        }*/
}


