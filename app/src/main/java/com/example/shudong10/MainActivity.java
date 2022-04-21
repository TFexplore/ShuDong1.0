package com.example.shudong10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.shudong10.second.adapter.DemoCardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
implements DemoCardFragment.OnActionListener {
    private static final String TAG = "Tag in main";

    private MyViewModle myViewModle;

    NavController navController;
    private long pressdTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//关闭toobar
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){//状态栏文字颜色随主题色变化
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_main);

        myViewModle=new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(),this)).get(MyViewModle.class);
        myViewModle.load();

        InternetTool internetTool=InternetTool.getInternetTool();
        internetTool.setMyViewModle(myViewModle);
        internetTool.setListening(true);
        internetTool.recListenUDP();

        internetTool.setNetstatus(false);

        internetTool.serConnect();

        pressdTime=0;

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigationView);

        navController= Navigation.findNavController(this,R.id.fragment_main);//navihost

        AppBarConfiguration configuration=new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,configuration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
               // Log.d("TAG", "onDestinationChanged: ---------"+destination.getId());

                //System.out.println(destination.getId()==R.id.login);
            }
        });



    }
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                //顶部状态栏
                window.setStatusBarColor(colorResId);

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        switch (navController.getCurrentDestination().getId()){
            case R.id.myQuestionFragment:
                navController.navigate(R.id.action_myQuestionFragment_to_findFragment);
                Log.d("TAG", "onSupportNavigateUp: 1");
                break;
            case R.id.findFragment:
                navController.navigate(R.id.action_findFragment_to_firstFragment);
                Log.d("TAG", "onSupportNavigateUp: 2");
                break;
            case R.id.firstFragment:

                break;
            default:break;
        }

        return super.onSupportNavigateUp();

    }

    @Override
    public void onBackPressed() {
        long pressTime=System.currentTimeMillis();
        Log.d(TAG, "onBackPressed: main:"+pressdTime+"  "+pressTime);
        switch (navController.getCurrentDestination().getId()){
            case R.id.myQuestionFragment:
                navController.navigate(R.id.action_myQuestionFragment_to_findFragment);
                Log.d("TAG", "onSupportNavigateUp: 1");
                break;
            case R.id.findFragment:

                if (pressTime-pressdTime>800) {
                    Toast.makeText(MainActivity.this,"再按一次返回主界面...",Toast.LENGTH_SHORT).show();
                    pressdTime=pressTime;
                }else navController.navigate(R.id.action_findFragment_to_firstFragment);
                break;
            case R.id.firstFragment:

                if (pressTime-pressdTime>800) {
                    Toast.makeText(MainActivity.this,"再按一次退出程序...",Toast.LENGTH_SHORT).show();
                    pressdTime=pressTime;

                }else System.exit(0);
                break;
            default: super.onBackPressed();
            break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: main");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: main");
        myViewModle.save();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        InternetTool.getInternetTool().setListening(false);
        Log.d(TAG, "onDestroy: main");
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
}