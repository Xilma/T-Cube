package com.example.android.tictactoe;


import android.content.pm.ActivityInfo;
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
public class ChooseOpponentFragment extends Fragment {

    public ChooseOpponentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return inflater.inflate(R.layout.fragment_choose_opponent, container, false);
    }

    @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            TextView computer, human;

            computer = view.findViewById(R.id.computer);
            human = view.findViewById(R.id.human);

            computer.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    selectBoardComputer();
                }
            });

            human.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    selectBoardHuman();
                }
            });

        }

        public void selectBoardComputer(){
            //Navigate to Stations Fragment
            Fragment chooseBoard = new ChooseBoardFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.menu_options, chooseBoard);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        public void selectBoardHuman(){
            //Navigate to Stations Fragment
            Fragment chooseBoard = new ChooseOptionFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.menu_options, chooseBoard);
            transaction.addToBackStack(null);
            transaction.commit();
        }
}



