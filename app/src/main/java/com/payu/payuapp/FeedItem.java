package com.payu.payuapp;

/**
 * Created by nitin on 9/5/15.
 */
public class FeedItem {
    private String name;
    private String logo;
    private String ticker;
    private String price;
    private String high;
    private String low;
    private int id;


    public FeedItem(String name, String logo, String ticker,String price,String high,String low,int id)
    {
        this.name = name;
        this.logo = logo;
        this.ticker = ticker;
        this.price = price;
        this.high = high;
        this.low = low;
        this.id = id;
    }

    public String getCompanyName() {
        return name;
    }
    public String getTicker () {
        return ticker;
    }
    public String getPrice() {
        return price;
    }
    public String getLow() {
        return low;
    }
    public String getHigh() {
        return high;
    }
    public int getId(){
        return id;
    }
    public String getLogo() {
        return logo;
    }

    public void setCompanyName(String title) {
        this.name =title;
    }

    public String getCompanyLogo() {
        return logo;
    }

    public void setCompanyLogo(String logo) {
        this.logo = logo;
    }
}