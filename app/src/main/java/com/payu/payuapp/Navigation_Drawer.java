package com.payu.payuapp;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.payu.payuapp.preferences.setSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class Navigation_Drawer extends Fragment {


    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private static final String KEY_USER_LEAREND_DRAWER="user_learnd_drawer";
    private View containerView;


    public Navigation_Drawer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(setSharedPreferences.readFromPreferences(getActivity(),KEY_USER_LEAREND_DRAWER,"false"));
        if(savedInstanceState != null)
            mFromSavedInstanceState = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }


}
