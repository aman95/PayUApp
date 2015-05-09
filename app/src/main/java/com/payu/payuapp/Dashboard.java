package com.payu.payuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.payu.payuapp.graphics.Graph;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Dashboard extends ActionBarActivity {

    protected List<FeedItem> feedItems = new ArrayList<>();
    protected StockAdapter adapter;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toast.makeText(this,"HIIIIII",Toast.LENGTH_SHORT).show();

        //Setting up AppBar
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //Setting up Navigation Drawer
        Navigation_Drawer drawer_fragment = (Navigation_Drawer) getSupportFragmentManager().findFragmentById(R.id.fragment_Navigation_Drawer);
        drawer_fragment.setUp(R.id.fragment_Navigation_Drawer, (DrawerLayout) findViewById(R.id.drawerLayout_Dashboard), toolbar);

        //Setting up recycler view for trending Stocks
        RecyclerView trendingStocksList = (RecyclerView)findViewById(R.id.trending_stocks_list);
        trendingStocksList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        trendingStocksList.setLayoutManager(llm);
        adapter = new StockAdapter(this, feedItems);
        trendingStocksList.setAdapter(adapter);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://10.100.86.148/payuapi/public/api/v1/stock/trending", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    Iterator<?> keys = data.keys();

                    while( keys.hasNext() ) {
                        String key = (String) keys.next();
                        if (data.get(key) instanceof JSONObject) {
                            JSONObject jsonObject = data.getJSONObject(key);
                            String company_name =jsonObject.getString("name");


                            FeedItem feedItem = new FeedItem(company_name, "h");
                            feedItems.add(feedItem);

                        }
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
        // Inflate the activity_graphics_graphmenu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
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

    public void changeToGraph(View view) {
        Intent i = new Intent(this,Graph.class);
        startActivity(i);
    }
}
