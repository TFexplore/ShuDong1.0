package com.example.shudong10.third;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shudong10.InternetTool;
import com.example.shudong10.MainActivity;
import com.example.shudong10.MyViewModle;
import com.example.shudong10.R;
import com.example.shudong10.databinding.FragmentThirdBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    private MyViewModle myViewModle;
    private InternetTool internetTool;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentThirdBinding binding;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentThirdBinding.inflate(getLayoutInflater());
        MainActivity.setWindowStatusBarColor(getActivity(),getActivity().getResources().getColor(R.color.thrid));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

     internetTool=InternetTool.getInternetTool();
     myViewModle=internetTool.getMyViewModle();

    if (myViewModle.getMycilent().getValue().getName().length()>8)
         binding.username.setTextSize(18);
     binding.username.setText(myViewModle.getMycilent().getValue().getName());
     binding.lv.setText("Lv."+myViewModle.getMycilent().getValue().getLv());

     init();

    }
    void init(){
        Drawable left1= ContextCompat.getDrawable(getContext(),R.drawable.shangpin);
        Drawable left2= ContextCompat.getDrawable(getContext(),R.drawable.fenxiang);
        Drawable left3= ContextCompat.getDrawable(getContext(),R.drawable.xihuan);
        Drawable left4= ContextCompat.getDrawable(getContext(),R.drawable.yiwen);
        Drawable right= ContextCompat.getDrawable(getContext(),R.drawable.ic_baseline_keyboard_arrow_right_24);
        right.setBounds(10,0,100,100);
        left1.setBounds(0,0,100,100);
        left2.setBounds(0,0,100,100);
        left3.setBounds(0,0,100,100);
        left4.setBounds(0,0,100,100);
        binding.shop.setCompoundDrawables(left1,null,right,null);
        binding.yaoqing.setCompoundDrawables(left2,null,right,null);
        binding.guanzhu.setCompoundDrawables(left3,null,right,null);
        binding.fankui.setCompoundDrawables(left4,null,right,null);




    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("tag","----------------");
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}