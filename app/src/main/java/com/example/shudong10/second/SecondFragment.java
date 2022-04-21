package com.example.shudong10.second;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shudong10.InternetTool;
import com.example.shudong10.MainActivity;
import com.example.shudong10.MsgEntity;
import com.example.shudong10.MyViewModle;
import com.example.shudong10.R;
import com.example.shudong10.UITool.MyDialog;
import com.example.shudong10.second.adapter.DemoCardFragment;
import com.example.shudong10.second.adapter.MainActivityPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.Objects;

import me.relex.circleindicator.CircleIndicator;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment
implements DemoCardFragment.OnActionListener {

    private  int i;
    MyViewModle myViewModle;
    InternetTool internetTool;
    Gson gson=new Gson();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentSecondBinding binding;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
        binding = FragmentSecondBinding.inflate(getLayoutInflater());
        MainActivity.setWindowStatusBarColor(getActivity(),getActivity().getResources().getColor(R.color.second2));


        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("tag", "onCreate: ");
        ViewPager pager = requireView().findViewById(R.id.pager);
        pager.setAdapter(new MainActivityPagerAdapter(getActivity(), getActivity().getSupportFragmentManager()));
        pager.setPageMargin((int) getResources().getDimension(R.dimen.card_padding) / 4);
        pager.setOffscreenPageLimit(3);
        CircleIndicator indicator =getView().findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        i = 0;
        InternetTool internetTool = InternetTool.getInternetTool();
        myViewModle = internetTool.getMyViewModle();
        binding.historydialogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_secondFragment_to_dialogsFragment);
            }
        });
        binding.Messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(getView());
                navController.navigate(R.id.action_secondFragment_to_messageListFragment);
            }
        });
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.arg1 == -2) {
                    i++;
                    binding.begin.setText(" " + i);
                }
                return false;
            }
        });
        if (myViewModle.onWait != -1) {
            binding.begin.setText("匹配中。。。");
        }

        internetTool.setMlistener(new InternetTool.onMsgReturnedListener() {
            @Override
            public void onMsgReturned(String msg) {
                Message message = Message.obtain();
                Log.d(TAG, "onMsgReturned2: " + msg);
                MsgEntity msgEntity = MsgEntity.getMsgEntity(msg);
                if (msgEntity.getType() == 20 && msgEntity.getValue() == 1) {
                    binding.begin.setText("匹配成功");

                }

                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception ex) {

            }
        });
        MyDialog mMyDialog;
        View view = getLayoutInflater().inflate(R.layout.dialog, null);
        TextView tip = view.findViewById(R.id.tip);
        TextView content = view.findViewById(R.id.content);
        content.setText("    本界面中用户可选择身份进行匹配，匹配成功后会开启一个有时间限制的会话，会话结束后会生成历史记录\n" +
                "可供选择的身份有树洞:负责倾听别人的烦恼。精灵:可以向树洞倾述自己的烦恼，专属树洞：只有自己能看到的记录" +
                "\n\n\n");
        tip.setText("Tip");
        mMyDialog = new MyDialog(requireContext(), 0, 0, view, R.style.DialogTheme);
        mMyDialog.setCancelable(true);
        ImageView imageView=getView().findViewById(R.id.dialog_2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyDialog.show();
            }
        });

      binding.begin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              NavController navController= Navigation.findNavController(getView());
              navController.navigate(R.id.action_secondFragment_to_messageListFragment);
          }
      });

      /*  button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MsgEntity msgEntity=MsgEntity.getMsgEntity();

                msgEntity.setUser(myViewModle.clientEntity.getUser());
                if (myViewModle.onWait==0){//取消匹配
                    myViewModle.type=-1;
                    myViewModle.onWait=-1;
                    msgEntity.setValue(0);
                    msgEntity.setType(21);
                    internetTool.sendMsg(gson.toJson(msgEntity));
                    binding.begin.setText("开始匹配");
                    binding.hole.setClickable(true);
                    binding.talker.setClickable(true);
                }
                else if (myViewModle.onWait==1){//取消匹配
                    myViewModle.type=-1;
                    myViewModle.onWait=-1;
                    msgEntity.setValue(1);
                    msgEntity.setType(21);
                    internetTool.sendMsg(gson.toJson(msgEntity));
                    binding.begin.setText("开始匹配");
                    binding.hole.setClickable(true);
                    binding.talker.setClickable(true);
                }
                else  {
                    if (myViewModle.type!=-1) {//开始匹配
                        myViewModle.onWait = myViewModle.type;
                        msgEntity.setValue(myViewModle.type);
                        msgEntity.setType(20);
                        internetTool.sendMsg(gson.toJson(msgEntity));
                        binding.hole.setClickable(false);
                        binding.talker.setClickable(false);
                        binding.begin.setText("匹配中。。。");
                    }
                    else  Toast.makeText(getContext(),"请选择身份",Toast.LENGTH_LONG).show();
                }


            }
        });
        binding.hole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                myViewModle.type=0;
                myViewModle.onWait=-1;
            }
        });
        binding.talker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModle.type=1;
                myViewModle.onWait=-1;
            }
        });
*/
    }
    @Override
    public void onAction(int id) {
        switch (id) {
            case 0:
                Log.d(TAG, "onAction: 0");
                break;
            case 1:
                Log.d(TAG, "onAction: 1");
                break;
            case 2:
                Log.d(TAG, "onAction: 2");
                break;
        }
    }
        @Override
        public void onStart () {
            super.onStart();
            BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
}