package com.example.snclient.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.snclient.R;
import com.example.snclient.databinding.FragmentFriendBinding;
import com.example.snclient.model.ItemFriend;
import com.example.snclient.model.database.Friend;
import com.example.snclient.model.database.UserProfile;
import com.example.snclient.model.request.AddFriendRequest;
import com.example.snclient.model.request.FriendRequest;
import com.example.snclient.model.response.BaseResponse;
import com.example.snclient.retrofitservices.RetrofitUnits;
import com.example.snclient.retrofitservices.Services;
import com.example.snclient.ui.adapter.FriendAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendFragment extends Fragment implements FriendAdapter.IFriend {
    private FragmentFriendBinding binding;
    private List<ItemFriend> listAcount;
    private Services services;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFriendBinding.inflate(inflater, container, false);
        services = RetrofitUnits.getServices();
        listAcount = new ArrayList<>();

//        createVirtualData();
        getAllFriend();
        binding.friendContent.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.friendContent.setAdapter(new FriendAdapter(this));
        return binding.getRoot();
    }

    public void getAllFriend() {
        if (services == null) {
            return;
        }
        FriendRequest request = new FriendRequest();
        request.setId_user(((SocialActivity)getActivity()).getIdFromCache(getActivity()));
        services.getAllFriend(request).enqueue(new Callback<BaseResponse<List<Friend>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Friend>>> call, Response<BaseResponse<List<Friend>>> response) {

            }

            @Override
            public void onFailure(Call<BaseResponse<List<Friend>>> call, Throwable t) {

            }
        });
    }

    public void getAllFriend2() {
        if (services == null) {
            return;
        }
        services.getAllUser().enqueue(new Callback<BaseResponse<List<UserProfile>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<UserProfile>>> call, Response<BaseResponse<List<UserProfile>>> response) {
                List<UserProfile> data = response.body().getData();
                if (data == null || data.size() == 0) {
                    return;
                }
                for (UserProfile datum : data) {

                    ItemFriend friend = new ItemFriend();
                    friend.setId_friend(datum.getId());
                    friend.setId_user(((SocialActivity) getActivity()).getIdFromCache(getActivity()));
                    friend.setLinkAvatar(datum.getAvatar());
                    friend.setUserInfo(datum.getPhone());
                    friend.setCountry(datum.getPhone());
                    friend.setFalname(datum.getFalname());
                    listAcount.add(friend);
                }
                binding.friendContent.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BaseResponse<List<UserProfile>>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getCount() {
        if (listAcount == null || listAcount.size() == 0) {
            return 0;
        }
        return listAcount.size();
    }

    @Override
    public ItemFriend getData(int position) {
        return listAcount.get(position);
    }

    @Override
    public void addFriend(int id_user, int id_friend) {
        AddFriendRequest request = new AddFriendRequest();
        request.setId_user(id_user);
        request.setId_friend(id_friend);
        services.addFriend(request).enqueue(new Callback<BaseResponse<Friend>>() {
            @Override
            public void onResponse(Call<BaseResponse<Friend>> call, Response<BaseResponse<Friend>> response) {
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse<Friend>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public Services getServices() {
        return services;
    }
}
