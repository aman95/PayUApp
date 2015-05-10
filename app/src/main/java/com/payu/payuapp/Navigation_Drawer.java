package com.payu.payuapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.payu.payuapp.preferences.setSharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Navigation_Drawer extends Fragment {


    private ActionBarDrawerToggle mDrawerToggle;
    private static DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private static final String KEY_USER_LEAREND_DRAWER="user_learnd_drawer";
    private View containerView;
    TextView userName;
    TextView money;
    View inflatedView = null;
    String username;
    int userMoney;


    public Navigation_Drawer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(setSharedPreferences.readFromPreferences(getActivity(),KEY_USER_LEAREND_DRAWER,"false"));
        username = setSharedPreferences.readFromPreferences(getActivity().getApplicationContext(), "username", "Guest");
        if(savedInstanceState != null)
            mFromSavedInstanceState = true;

        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, "http://10.100.86.148/payuapi/public/api/v1/user/"+username, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data2 = response.getJSONObject("data");
                    userMoney = (data2.getInt("money"));
                    Log.d("JSON",""+userMoney);

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
        AppController.getInstance().addToRequestQueue(jsonObjectRequest2, "stock2");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.inflatedView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        userName = (TextView) inflatedView.findViewById(R.id.userName);
        userName.setText(username);

        money = (TextView)inflatedView.findViewById(R.id.money);
        money.setText("$"+userMoney);
        return inflatedView;
    }

    public void setUp(int fragmentId,DrawerLayout drawerLayout, final Toolbar toolbar) {
        mDrawerLayout=drawerLayout;
        containerView = getActivity().findViewById(fragmentId);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,(R.string.drawer_open),R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    setSharedPreferences.saveToPreferences(getActivity(),KEY_USER_LEAREND_DRAWER,mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(slideOffset<0.4) {
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };

        if(!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });






    }

    public static void  closeDrawer() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }




}
