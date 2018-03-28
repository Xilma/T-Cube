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
public class PageTwoHelpFragment extends Fragment {

    private TextView back, next;

    public PageTwoHelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_two_help, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back = view.findViewById(R.id.back);
        next = view.findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment pageOne = new PageOneHelpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.help_container, pageOne);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment pageThree = new PageThreeHelpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.help_container, pageThree);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
