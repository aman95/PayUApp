package com.payu.payuapp;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class BuyStocks extends ActionBarActivity {

    protected List<FeedItem> feedItems = new ArrayList<>();
    protected StockAdapter adapter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        //Setting up AppBar
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //Setting up Navigation Drawer
        Navigation_Drawer drawer_fragment = (Navigation_Drawer) getSupportFragmentManager().findFragmentById(R.id.fragment_Navigation_Drawer);
        drawer_fragment.setUp(R.id.fragment_Navigation_Drawer, (DrawerLayout) findViewById(R.id.drawerLayout_Dashboard), toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notifications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }

    public void changeToLeaderboard(View view) {
        Intent i = new Intent(this,Leaderboard.class);
        startActivity(i);
        finish();

    }

    public void changeToPortfolio(View view) {

        Intent i = new Intent(this,BuyStocks.class);
        startActivity(i);
        finish();


    }
    public void changeToNotifications(View view) {
        Navigation_Drawer.closeDrawer();
    }
}
