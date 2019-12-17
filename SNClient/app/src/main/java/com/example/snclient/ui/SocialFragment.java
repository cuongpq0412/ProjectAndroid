package com.example.snclient.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.snclient.R;
import com.example.snclient.databinding.FragmentMainSocialBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class SocialFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FragmentMainSocialBinding binding;
    private int id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainSocialBinding.inflate(inflater, container, false);

        Bundle bundle = this.getArguments();
        id = bundle.getInt("idUser");
        addHomeFragment(id);
        binding.bototm.setOnNavigationItemSelectedListener(this);
        return binding.getRoot();
    }


    public static void hideAllFragment(FragmentManager manager, FragmentTransaction transaction) {
        List<Fragment> fs = manager.getFragments();
        for (Fragment f : fs) {
            if (f != null && f.isVisible()) {
                transaction.hide(f);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home:
                addHomeFragment(id);
                break;
            case R.id.video:
                addVideoFragment();
                break;
            case R.id.acount:
                break;
            case R.id.friend:
                addFriendFragment();
                break;
            case R.id.menu:
                addUserProfileFragment(id);
                break;

        }
        return true;
    }
    public void addNewDetailFragment(int id) {
        FragmentManager manager = getChildFragmentManager();
        NewDetailFragment fragment =(NewDetailFragment) manager.findFragmentByTag(NewDetailFragment.class.getName());
        if (fragment != null && fragment.isVisible()) {
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment != null && fragment.isVisible()) {
            hideAllFragment(manager, transaction);
            transaction.show(fragment);
            transaction.commit();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("idnew",id);
        fragment = new NewDetailFragment();
        fragment.setArguments(bundle);
        hideAllFragment(manager, transaction);
        transaction.add(R.id.content, fragment, fragment.getClass().getName());
        transaction.commit();
    }
    private void addFriendFragment() {
        FragmentManager manager = getChildFragmentManager();
        FriendFragment fragment =(FriendFragment) manager.findFragmentByTag(FriendFragment.class.getName());
        if (fragment != null && fragment.isVisible()) {
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment != null && fragment.isVisible()) {
            hideAllFragment(manager, transaction);
            transaction.show(fragment);
            transaction.commit();
            return;
        }
        fragment = new FriendFragment();
        hideAllFragment(manager, transaction);
        transaction.add(R.id.content, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    private void addUserProfileFragment(int id) {
        FragmentManager manager = getChildFragmentManager();
        UserProfileFragment fragment =(UserProfileFragment) manager.findFragmentByTag(UserProfileFragment.class.getName());
        if (fragment != null && fragment.isVisible()) {
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment != null && fragment.isVisible()) {
            hideAllFragment(manager, transaction);
            transaction.show(fragment);
            transaction.commit();
            return;
        }
        fragment = new UserProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("iduser",id);
        fragment.setArguments(bundle);
        hideAllFragment(manager, transaction);
        transaction.add(R.id.content, fragment, fragment.getClass().getName());
        transaction.commit();
    }
    public void addHomeFragment(int id) {
        FragmentManager manager = getChildFragmentManager();
        HomeFragment fragment =(HomeFragment) manager.findFragmentByTag(HomeFragment.class.getName());
        if (fragment != null && fragment.isVisible()) {
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment != null && fragment.isVisible()) {
            hideAllFragment(manager, transaction);
            transaction.show(fragment);
            transaction.commit();
            return;
        }
        fragment = new HomeFragment();
        hideAllFragment(manager, transaction);
        Bundle bundle = new Bundle();
        bundle.putInt("idUser",id);
        fragment.setArguments(bundle);
        transaction.add(R.id.content, fragment, fragment.getClass().getName());
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void addVideoFragment() {
        FragmentManager manager = getChildFragmentManager();
        VideosFragment fragment =(VideosFragment) manager.findFragmentByTag(VideosFragment.class.getName());
        if (fragment != null && fragment.isVisible()) {
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment != null && fragment.isVisible()) {
            hideAllFragment(manager, transaction);
            transaction.show(fragment);
            transaction.commit();
            return;
        }
        fragment = new VideosFragment();
        hideAllFragment(manager, transaction);
        transaction.add(R.id.content, fragment, fragment.getClass().getName());
        transaction.commit();
    }

}
