package com.payu.payuapp.adapters;

/**
 * Created by nitin on 10/5/15.
 */
public class LeaderboardFeedItem {
    private String name;
    private String avatar;

    public LeaderboardFeedItem(String name, String logo)
    {
        this.name = name;
        this.avatar = logo;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String title) {
        this.name =title;
    }

    public String getUserAvatar() {
        return avatar;
    }

    public void setCompanyLogo(String logo) {
        this.avatar = logo;
    }
}
