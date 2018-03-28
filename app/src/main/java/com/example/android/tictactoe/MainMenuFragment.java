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

    private TextView play, stats, options, help;

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
        play = view.findViewById(R.id.play);
        stats = view.findViewById(R.id.stats);
        options = view.findViewById(R.id.options);
        help = view.findViewById(R.id.help);

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Navigate to Stations Fragment
                Fragment chooseOption = new ChooseOptionFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.menu_options, chooseOption);
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

        options.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent options = new Intent(getActivity(), OptionsActivity.class);
                startActivity(options);
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