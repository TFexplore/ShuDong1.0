package com.example.shudong10.first;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shudong10.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.content.ContentValues.TAG;

public class FindFragment extends Fragment {

    private FindViewModel mViewModel;
    RecyclerAdapter myAdapter1;
    RecyclerView recyclerView;
    public static FindFragment newInstance() {
        return new FindFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("TAG", "onCreateView: findcreat");
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.GONE);
        return inflater.inflate(R.layout.find_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FindViewModel.class);
        // TODO: Use the ViewModel
        myAdapter1=new RecyclerAdapter();
        recyclerView=getView().findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(myAdapter1);


        TextView myQuestion=getView().findViewById(R.id.textView14);

        myQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller= Navigation.findNavController(v);
                controller.navigate(R.id.action_findFragment_to_myQuestionFragment);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: find");
    }

}