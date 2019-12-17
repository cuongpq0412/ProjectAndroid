package com.example.snclient.ui;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.snclient.R;
import com.example.snclient.model.database.UserProfile;
import com.example.snclient.model.request.CommentRequest;
import com.example.snclient.model.request.GetOneNewRequest;
import com.example.snclient.model.request.NewStatusRequest;
import com.example.snclient.model.response.BaseResponse;
import com.example.snclient.model.response.CommentResponse;
import com.example.snclient.model.response.NewStatusResponse;
import com.example.snclient.retrofitservices.RetrofitUnits;
import com.example.snclient.retrofitservices.Services;
import com.example.snclient.ui.adapter.NewDetailAdapter;
import com.example.snclient.comment.ItemComment;
import com.example.snclient.databinding.FragmentNewsDetailBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewDetailFragment extends Fragment implements NewDetailAdapter.INewDetail {
    private FragmentNewsDetailBinding binding;
    private List<ItemComment> listComments;
    private int id_new;
    private Services services;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsDetailBinding.inflate(inflater,container,false);
        Bundle bundle = this.getArguments();
        id_new = bundle.getInt("idnew");
        listComments = new ArrayList<>();
        services = RetrofitUnits.getServices();
        getItemNew();
        getAllCommentByIdUser();
        binding.commentContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.commentContent.setAdapter(new NewDetailAdapter(this));
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return binding.getRoot();
    }

    private void getItemNew(){
        GetOneNewRequest request = new GetOneNewRequest();
        request.setId(id_new);
        services.getOneNewById(request).enqueue(new Callback<BaseResponse<NewStatusResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<NewStatusResponse>> call, Response<BaseResponse<NewStatusResponse>> response) {
                if (response.body().getData()==null){
                    return;
                }
                NewStatusResponse data= response.body().getData();
                binding.acountName.setText(data.getFalname());
                binding.newsContent.setText(data.getContent());
                binding.datetime.setText(data.getTimenew());
                binding.numberLike.setText(data.getLike_status());
                binding.numberComment.setText(data.getComment_status());
                if (data.getAvatar()==null||data.getAvatar().equals("")){
                    Glide.with(binding.cvAvatar)
                            .load(R.drawable.avatar)
                            .into(binding.cvAvatar);
                }else{
                    Glide.with(binding.cvAvatar)
                            .load(data.getAvatar())
                            .error(R.drawable.ic_launcher_background)
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(binding.cvAvatar);
                }
                if (data.getLink_image()==null||data.getLink_image().equals("")){
                    Glide.with(binding.newsImage)
                            .load(R.drawable.girl)
                            .into(binding.newsImage);
                }else{
                    Glide.with(binding.newsImage)
                            .load(data.getLink_image())
                            .error(R.drawable.ic_launcher_background)
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(binding.newsImage);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<NewStatusResponse>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        NewStatusRequest request1 = new NewStatusRequest();
        int id_current_user = ((SocialActivity)getActivity()).getIdFromCache(getActivity());
        request1.setId_user(id_current_user);
        services.getUserById(request1).enqueue(new Callback<BaseResponse<UserProfile>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserProfile>> call, Response<BaseResponse<UserProfile>> response) {
                UserProfile userProfile = response.body().getData();
                if (userProfile==null){
                    return;
                }
                if (userProfile.getAvatar()==null||userProfile.getAvatar().equals("")){
                    Glide.with(binding.myAvatar)
                            .load(R.drawable.avatar)
                            .into(binding.myAvatar);
                }else{
                    Glide.with(binding.cvAvatar)
                            .load(userProfile.getAvatar())
                            .error(R.drawable.ic_launcher_background)
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(binding.myAvatar);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<UserProfile>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllCommentByIdUser(){
        CommentRequest request = new CommentRequest();
        request.setId_news(id_new);
        services.getAllCommentByNewId(request).enqueue(new Callback<BaseResponse<List<CommentResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<CommentResponse>>> call, Response<BaseResponse<List<CommentResponse>>> response) {
                for (CommentResponse datum : response.body().getData()) {
                    ItemComment itemComment = new ItemComment();
                    itemComment.setName(datum.getFalname());
                    itemComment.setContent(datum.getContent());
                    itemComment.setTimeUp(datum.getTimecm());
                    itemComment.setAvatar(datum.getAvatar());
                    listComments.add(itemComment);
                }
                binding.commentContent.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BaseResponse<List<CommentResponse>>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getCount() {
        if (listComments.size()==0){
            return 0;
        }
        return listComments.size();
    }

    @Override
    public ItemComment getData(int position) {
        return listComments.get(position);
    }

    @Override
    public void onClickItem() {

    }
}
