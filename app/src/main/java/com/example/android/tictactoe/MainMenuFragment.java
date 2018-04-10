package com.example.android.tictactoe;


import android.content.Intent;
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
public class MainMenuFragment extends Fragment {

    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView play = view.findViewById(R.id.play);
        TextView stats = view.findViewById(R.id.stats);
        TextView help = view.findViewById(R.id.help);

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Navigate to ChooseBoard Fragment
                Fragment chooseBoard = new ChooseOpponentFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.menu_options, chooseBoard);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        stats.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent stats = new Intent(getActivity(), StatsActivity.class);
                startActivity(stats);
                getActivity().finish();
            }
        });

        help.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent help = new Intent(getActivity(), HelpActivity.class);
                startActivity(help);
                getActivity().finish();
            }
        });

    }

}
