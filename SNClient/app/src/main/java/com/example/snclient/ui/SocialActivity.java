package com.example.snclient.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

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

public class SocialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        Intent intent = getIntent();
        int id = intent.getIntExtra("IdResponse",0);
//        openNewDetailFragment();
        openSocialFragment(id);

    }
    public void openStartActivity(){
        Intent intent = new Intent();
        intent.setClassName(this, StartActivity.class.getName());
        startActivity(intent);
        finish();
    }

    public void openSocialFragment(int id) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideAllFragment(manager,transaction);
        Bundle bundle = new Bundle();
        bundle.putInt("idUser",id);
        SocialFragment fragment = new SocialFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.content, fragment, fragment.getClass().getName())
                .commit();
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
    public void openNewDetailFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideAllFragment(manager,transaction);
        NewDetailFragment fragment = new NewDetailFragment();
        transaction.add(R.id.content, fragment, fragment.getClass().getName())
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

    public void openNewDetailFragment(int id) {
        SocialFragment fragment = (SocialFragment)getSupportFragmentManager().findFragmentByTag(SocialFragment.class.getName());
       fragment.addNewDetailFragment(id);

    }
}
