package com.example.android.tictactoe;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseOptionFragment extends Fragment {

    private TextView token_o, token_x;

    public ChooseOptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_option, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        token_o = view.findViewById(R.id.token_O);
        token_x = view.findViewById(R.id.token_X);

        token_o.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectBoard();
            }
        });

        token_x.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectBoard();
            }
        });

    }

    public void selectBoard(){
        //Navigate to Stations Fragment
        Fragment chooseBoard = new ChooseBoardFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.menu_options, chooseBoard);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
