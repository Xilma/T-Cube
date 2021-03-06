package com.example.android.tictactoe;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseBoardFragment extends Fragment {

    public ChooseBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return inflater.inflate(R.layout.fragment_choose_board, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView three, five;

        three = view.findViewById(R.id.three);
        five = view.findViewById(R.id.five);

        three.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent threeBoard = new Intent (getActivity(), ThreeBoardActivity.class);
                startActivity(threeBoard);
                getActivity().finish();
            }
        });

        five.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent fiveBoard = new Intent (getActivity(), FiveBoardActivity.class);
                startActivity(fiveBoard);
                getActivity().finish();
            }
        });
    }
}
