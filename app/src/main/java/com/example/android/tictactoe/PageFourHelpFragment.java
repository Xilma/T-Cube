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
public class PageFourHelpFragment extends Fragment {

    private TextView prev, done;

    public PageFourHelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_four_help, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prev = view.findViewById(R.id.back);
        done = view.findViewById(R.id.next);

        prev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment pageThree = new PageThreeHelpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.help_container, pageThree);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(getActivity(), MainActivity.class);
                startActivity(menu);
                getActivity().finish();
            }
        });
    }

}
