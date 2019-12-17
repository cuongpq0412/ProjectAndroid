package com.example.snclient.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.snclient.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        context = this;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, new SplashFragment(), SplashFragment.class.getName())
                .commit();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getIdFromCache(context) != 0) {
                        startSocialActivity(getIdFromCache(context));
                    return;
                }
                openLoginFragment();
            }
        }, 3000);
    }

    public void startSocialActivity(int id) {
        Intent intent = new Intent();
        intent.setClassName(this, SocialActivity.class.getName());
        intent.putExtra("IdResponse", id);
        startActivity(intent);
        finish();
    }

    public static int getIdFromCache(Context context) {
        File cDir = Environment.getDataDirectory();
        String DATA_DIRECTORY = cDir.getPath()+"/data/"+ context.getPackageName()+ "/token/"+ "logintoken";
        File cacheFile = new File(DATA_DIRECTORY);
        if (cacheFile.exists()==true){
            String strLine = "";
            StringBuilder text = new StringBuilder();
            try {
                FileReader fReader = new FileReader(cacheFile);
                BufferedReader bfReader = new BufferedReader(fReader);
                if ((strLine = bfReader.readLine()) != null) {
                    text.append(strLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Integer.parseInt(strLine);
        }
        return 0;
    }

    public void openRegisterFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideAllFragment(manager, transaction);
        transaction.add(R.id.content, new RegisterFragment()
                , RegisterFragment.class.getName())
                .commit();
    }

    public void openLoginFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideAllFragment(manager, transaction);
        transaction.add(R.id.content, new LoginFragment()
                , LoginFragment.class.getName())
                .commit();
    }

    public static void hideAllFragment(FragmentManager manager, FragmentTransaction transaction) {
        List<Fragment> fs = manager.getFragments();
        for (Fragment f : fs) {
            if (f != null && f.isVisible()) {
                transaction.hide(f);
            }
        }
    }
}
