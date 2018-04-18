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
public class ChooseXOHumanFive extends Fragment {


    public ChooseXOHumanFive() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return inflater.inflate(R.layout.fragment_choose_xo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView letterO, letterX;

        letterO = view.findViewById(R.id.letter_o);
        letterX = view.findViewById(R.id.letter_x);

        letterO.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent threeBoard = new Intent (getActivity(), FiveBoardHumanActivity.class);
                threeBoard.putExtra("Letter_O", 'O');
                startActivity(threeBoard);
                getActivity().finish();
            }
        });

        letterX.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent threeBoard = new Intent (getActivity(), FiveBoardHumanActivity.class);
                threeBoard.putExtra("Letter_X", 'X');
                startActivity(threeBoard);
                getActivity().finish();
            }
        });
    }

}
