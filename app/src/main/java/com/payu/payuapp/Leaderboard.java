package com.payu.payuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.payu.payuapp.adapters.LeaderboardAdapter;
import com.payu.payuapp.adapters.LeaderboardFeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Leaderboard extends ActionBarActivity {

    protected List<LeaderboardFeedItem> feedItems = new ArrayList<>();
    protected LeaderboardAdapter adapter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        //Setting up AppBar
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //Setting up Navigation Drawer
        Navigation_Drawer drawer_fragment = (Navigation_Drawer) getSupportFragmentManager().findFragmentById(R.id.fragment_Navigation_Drawer);
        drawer_fragment.setUp(R.id.fragment_Navigation_Drawer, (DrawerLayout) findViewById(R.id.drawerLayout_Dashboard), toolbar);

        //Setting up recycler view.
        RecyclerView leaderboardList = (RecyclerView)findViewById(R.id.leaderboard_list);
        leaderboardList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        leaderboardList.setLayoutManager(llm);
        adapter = new LeaderboardAdapter(this, feedItems);
        leaderboardList.setAdapter(adapter);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://10.100.86.148/payuapi/public/api/v1/leaderboard/net", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");

                    for(int i = 0; i < data.length(); i++) {
                        JSONObject jsonObject = data.getJSONObject(i);
                        String user_name = jsonObject.getString("username");
                        Log.d("Json", user_name);

                        LeaderboardFeedItem feedItem = new LeaderboardFeedItem(user_name, "h");
                        feedItems.add(feedItem);
                    }

                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Test", error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest, "stock");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leaderboard, menu);
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
        Navigation_Drawer.closeDrawer();

    }

    public void changeToPortfolio(View view) {
        Intent i = new Intent(this,Portfolio.class);
        startActivity(i);
        finish();

    }
    public void changeToNotifications(View view) {
        Intent i = new Intent(this,Notifications.class);
        startActivity(i);
        finish();

    }
}
