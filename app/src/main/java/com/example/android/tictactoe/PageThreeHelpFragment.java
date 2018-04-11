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
public class PageThreeHelpFragment extends Fragment {

    public PageThreeHelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return inflater.inflate(R.layout.fragment_page_three_help, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView back = view.findViewById(R.id.back);
        TextView next = view.findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment pageTwo = new PageTwoHelpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.help_container, pageTwo);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment pageFour = new PageFourHelpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.help_container, pageFour);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

}
