package com.example.shudong10.first;

import android.graphics.Outline;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import com.example.shudong10.ClientEntity;
import com.example.shudong10.InternetTool;
import com.example.shudong10.MainActivity;
import com.example.shudong10.MyViewModle;
import com.example.shudong10.R;
import com.example.shudong10.UITool.MyDialog;
import com.example.shudong10.databinding.FragmentFirstBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FisrstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FisrstFragment extends Fragment {

    private Banner banner;
    MyDialog mMyDialog;
    private MyViewModle myViewModle;
    private MutableLiveData<ClientEntity> client;
    private Handler handler;
    private int i;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentFirstBinding binding;
    InternetTool internetTool;

    public FisrstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FisrstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FisrstFragment newInstance(String param1, String param2) {
        FisrstFragment fragment = new FisrstFragment();
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
        binding = FragmentFirstBinding.inflate(getLayoutInflater());
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);
        MainActivity.setWindowStatusBarColor(requireActivity(),requireActivity().getResources().getColor(R.color.first));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InternetTool internetTool = InternetTool.getInternetTool();
        myViewModle=internetTool.getMyViewModle();
        client=myViewModle.getMycilent();

        initBanner(getView());
        i = 0;

       handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.arg1 == -1) {
                    i++;
                    Log.d(TAG, "handleMessage: ");
                    binding.textView24.setText(" " + i);
                }
                return false;
            }
        });
        internetTool.setMlistener(new InternetTool.onMsgReturnedListener() {
            @Override
            public void onMsgReturned(String msg) {
                Message message = Message.obtain();
                Log.d(TAG, "onMsgReturned1: " + msg);
                message.arg1 = -1;
                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception ex) {

            }
        });

        client.observe(getViewLifecycleOwner(), new Observer<ClientEntity>() {
            @Override
            public void onChanged(ClientEntity clientEntity) {
                if (clientEntity.getName().length()>=6){

                    binding.username.setTextSize(14);
                }
                binding.username.setText(clientEntity.getName());

                binding.lv.setText("Lv."+clientEntity.getLv());
            }
        });



        binding.qinggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_firstFragment_to_findFragment);
            }
        });
        binding.biaobai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_firstFragment_to_biaobaiqiangFragment);
            }
        });
        binding.dialogGonggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyDialog.show();
            }
        });
    }




    @Override
    public void onPause() {
        super.onPause();

        Log.d("TAG", "onPause:first");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: first");
    }

    private void initBanner(View v) {
        View view = getLayoutInflater().inflate(R.layout.dialog, null);
        TextView tip = view.findViewById(R.id.tip);
        TextView content = view.findViewById(R.id.content);
        content.setText("    由于开发进度落后，为方便展示app主题，目前app没有连接后台数据。\n" +"待完善。。。。。。"+
                "\n\n\n");
        tip.setText("公告");
        mMyDialog = new MyDialog(requireContext(), 0, 0, view, R.style.DialogTheme);
        mMyDialog.setCancelable(true);
        if (myViewModle.dialog) {
            mMyDialog.show();
            myViewModle.dialog=false;
        }





        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.banner1);
        list.add(R.drawable.banner2);
        list.add(R.drawable.banner3);

        banner = v.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        banner.start();
        banner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        });
        banner.setClipToOutline(true);
        banner.setClickable(true);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Log.d(TAG, "OnBannerClick: "+position);
            }
        });

    }
}
