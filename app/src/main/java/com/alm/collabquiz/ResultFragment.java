package com.alm.collabquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ResultFragment extends Fragment {



    public ResultFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_result, container, false); //pass the correct layout name for the fragment

        TextView score,earnedcoins;
        score=view.findViewById(R.id.tvscore);
        earnedcoins=view.findViewById(R.id.tvearnedCoins);

       /* String correct = getArguments().getString("correct12");
        String total = getArguments().getString("total12");
        int points =  100 ;
        score.setText(String.format("%d/%d",correct,total));
        earnedcoins.setText(String.valueOf(points));*/


       /* int corrct=getArguments().getInt("correct");
        int ttl=getArguments().getInt("total");
        score.setText(String.format("%d/%d",corrct,ttl));
        int points = 100;
        earnedcoins.setText(String.valueOf(points));*/

      /*  String one= getArguments().getString("ABC");
        String two= getArguments().getString("QWE");

        score.setText(one);
        earnedcoins.setText(two);*/

        // Inflate the layout for this fragment
        return view;


    }
}