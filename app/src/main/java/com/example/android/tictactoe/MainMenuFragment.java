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
                Fragment chooseBoard = new ChooseBoardFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.menu_options, chooseBoard);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

}
