package com.maliotis.drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.maliotis.drawer.Fragments.BaseFragment;
import com.maliotis.drawer.Fragments.DrawerFragment;
import static com.maliotis.drawer.FragmentEnums.*;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements DrawerFragment.OnFragmentInteractionListener {

    private FrameLayout mFrameLayout;
    private DrawerFragment mDrawerFragment;
    private ArrayList<BaseFragment> mBaseFragments;
    private RelativeLayout mRelativeLayout;
    private int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFrameLayout = new FrameLayout(this);
        ViewGroup.LayoutParams layoutParams =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mFrameLayout.setLayoutParams(layoutParams);
        setContentView(mFrameLayout);

        createFragments();

    }

    private void createFragments() {
        createDrawerFragment();
    }

    private void createDrawerFragment() {
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT);
        //layoutParams.gravity = Gravity.START;

        mRelativeLayout = new RelativeLayout(this);
        mRelativeLayout.setLayoutParams(layoutParams);
        mRelativeLayout.setId(View.generateViewId());

        mDrawerFragment = DrawerFragment.newInstance("This","That");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(mRelativeLayout.getId(), mDrawerFragment).commit();

        mFrameLayout.addView(mRelativeLayout,layoutParams);
    }

    @Override
    public void onFragmentInteraction(Map.Entry entry) {
        if (entry.getKey() == HELLO_BUTTON.toString()) {
            drawerInteraction();
        }

    }

    private void drawerInteraction() {
        DrawerLayout drawerLayout = (DrawerLayout) mDrawerFragment.getView();
        if (drawerLayout != null) {
            if (drawerLayout.isDrawerOpen(Gravity.START)) {
                drawerLayout.closeDrawer(Gravity.START);
            } else {
                drawerLayout.openDrawer(Gravity.START);
            }
        }
    }

}
