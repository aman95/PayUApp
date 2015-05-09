package com.payu.payuapp;

/**
 * Created by nitin on 9/5/15.
 */
public class FeedItem {
    private String name;
    private String logo;

    public FeedItem(String name, String logo)
    {
        this.name = name;
        this.logo = logo;
    }

    public String getCompanyName() {
        return name;
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