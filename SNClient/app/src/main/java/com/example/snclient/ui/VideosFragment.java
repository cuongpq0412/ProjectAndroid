package com.example.snclient.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.snclient.model.ItemVideo;
import com.example.snclient.ui.adapter.VideoAdapter;
import com.example.snclient.databinding.FragmentVideoBinding;

import java.util.ArrayList;
import java.util.List;

public class VideosFragment extends Fragment implements VideoAdapter.IVideos {
    private FragmentVideoBinding binding;
    private List<ItemVideo> listVideos;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVideoBinding.inflate(inflater,container,false);
        binding.videoContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        createVirtualData();
        binding.videoContent.setAdapter(new VideoAdapter(this));
        return binding.getRoot();
    }
    public void createVirtualData(){
        listVideos = new ArrayList<>();
        for (int i = 0; i < 5; i ++){
            ItemVideo data = new ItemVideo();
            data.setnName("Pham Cuong");
            data.setnContent("Chao buoi sang");
            data.setLinkAvatar("https://scontent.fhan2-1.fna.fbcdn.net/v/t1.0-9/42369951_1506530862824244_4438732092053913600_n.jpg?_nc_cat=101&_nc_oc=AQmVIRPGhfp_6I_IIS6q5dQyq45JSk1nWN9NPx4QfVWE--WHmFSnyqOVqfzCdF8rKC4&_nc_ht=scontent.fhan2-1.fna&oh=3a494cbe1d997878f8d61e7cc338d8c4&oe=5E437B9A");
            data.setnDate("20 thang 11 luc 18:05");
            data.setnLinkVideo("https://data33.chiasenhac.com/downloads/1982/4/1981354-189efd4f/128/Dem%20Tam%20Su%20-%20Bang%20Tam_%20Dan%20Nguyen.mp4");
            data.setnNumLike("Quoc Cuong va 27 nguoi khac");
            data.setnNumComment("8 Binh luan");
            listVideos.add(data);
        }
    }
    @Override
    public int getCount() {
        if (listVideos.size()==0){
            return 0;
        }
        return listVideos.size();
    }

    @Override
    public ItemVideo getData(int position) {
        return listVideos.get(position);
    }

    @Override
    public void onClickItem(int position) {

    }

    @Nullable
    @Override
    public Context getContext() {
        return getActivity();
    }
}
