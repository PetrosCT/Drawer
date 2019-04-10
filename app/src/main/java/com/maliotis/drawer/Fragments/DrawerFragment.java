package com.maliotis.drawer.Fragments;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maliotis.drawer.FragmentEnums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static com.maliotis.drawer.FragmentEnums.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DrawerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DrawerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawerFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //private ArrayList<Integer> mButtonIds;
    private HashMap<String, Integer> mButtonIds;

    public HashMap<String, Integer> getButtonIds() {
        return mButtonIds;
    }



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DrawerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrawerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrawerFragment newInstance(String param1, String param2) {
        DrawerFragment fragment = new DrawerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mButtonIds = new HashMap<>();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        DrawerLayout.LayoutParams drawerLayoutParams =
                new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        drawerLayoutParams.gravity = Gravity.START;
        DrawerLayout drawerLayout = new DrawerLayout(Objects.requireNonNull(getActivity()));
        drawerLayout.setLayoutParams(drawerLayoutParams);
        drawerLayout.setBackgroundColor(Color.WHITE);


        RelativeLayout.LayoutParams relativeLayoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        relativeLayout.setLayoutParams(relativeLayoutParams);
        relativeLayout.setBackgroundColor(Color.WHITE);


        RelativeLayout.LayoutParams textViewParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        textViewParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        Button button = new Button(getActivity());
        button.setId(View.generateViewId());
        //mButtonIds.add(button.getId());
        mButtonIds.put(HELLO_BUTTON.toString(),button.getId());
        button.setText("Hello");
        button.setTextSize(20f);
        button.setOnClickListener(this::onClick);
        relativeLayout.addView(button, textViewParams);

        RelativeLayout relativeLayout1 = createRelativeLayout();

        // Add relativeLayout to drawer
        drawerLayout.addView(relativeLayout, relativeLayoutParams);
        drawerLayout.addView(relativeLayout1);


        return drawerLayout;
    }

    private RelativeLayout createRelativeLayout() {
        DrawerLayout.LayoutParams lp = new DrawerLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.MATCH_PARENT);
        lp.gravity=Gravity.START;
        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        relativeLayout.setLayoutParams(lp);
        relativeLayout.setBackgroundColor(Color.WHITE);
        TextView textView = new TextView(getActivity());
        textView.setText("Hello world1");
        relativeLayout.addView(textView);

        return relativeLayout;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

        for (Map.Entry<String, Integer> entry: mButtonIds.entrySet()) {
            int id = entry.getValue();
            if (view.getId() == id) {
                mListener.onFragmentInteraction(entry);
            }
        }
    }



}
