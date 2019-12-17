package com.example.snclient.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.snclient.model.response.NewStatusResponse;
import com.example.snclient.ui.adapter.HomeAdapter;
import com.example.snclient.model.ItemNews;
import com.example.snclient.databinding.FragmentHomeBinding;
import com.example.snclient.retrofitservices.RetrofitUnits;
import com.example.snclient.retrofitservices.Services;
import com.example.snclient.model.response.BaseResponse;
import com.example.snclient.model.request.NewStatusRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements HomeAdapter.ISocial, SwipeRefreshLayout.OnRefreshListener {
    private FragmentHomeBinding binding;
    private List<ItemNews> listNews;
    private Services services;
    private int id_user;
    private ExecutorService ex;
    private HomeAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        adapter = new HomeAdapter(this);
        listNews = new ArrayList<>();

//        createVirtualData();
        Bundle bundle = this.getArguments();
        id_user = bundle.getInt("idUser");
        services = RetrofitUnits.getServices();
        getListNews();
        binding.homeContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.homeContent.setAdapter(adapter);

        binding.homeRefresh.setOnRefreshListener(this);
        return binding.getRoot();
    }


    public void getListNews() {
        final NewStatusRequest request = new NewStatusRequest();
        request.setId_user(id_user);
        services.getAllNewByIdUser(request).enqueue(new Callback<BaseResponse<List<NewStatusResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<NewStatusResponse>>> call, Response<BaseResponse<List<NewStatusResponse>>> response) {
                binding.homeRefresh.setRefreshing(false);
                adapter.setLoadingMore(false);
                if (response.body().getData() != null) {
                    for (NewStatusResponse datum : response.body().getData()) {
                        int id = datum.getId();
                        int iduser = datum.getId_user();
                        String numberComment = datum.getComment_status();
                        String content = datum.getContent();
                        String numberLike = datum.getLike_status();
                        String linkImage = datum.getLink_image();
                        String timenew = datum.getTimenew();
                        String acountName = datum.getFalname();
                        String linkAvatar = datum.getAvatar();
                        if (linkAvatar==null||linkAvatar.equals("")){
                            linkAvatar = "https://image1.infogame.vn/2017/03/21/108_thumbb.jpg";
                        }
                        if (acountName==null|| acountName.equals("")){
                            acountName = "Quoc Cuong";
                        }
                        listNews.add(new ItemNews(id, iduser, linkAvatar, acountName,  linkImage,content, numberLike, numberComment, timenew));
                    }
                } else {
                    return;
                }
                binding.homeContent.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<BaseResponse<List<NewStatusResponse>>> call, Throwable t) {
                Toast.makeText(HomeFragment.this.getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                binding.homeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public int getCount() {
        if (listNews.size() == 0) {
            return 0;
        }
        return listNews.size();
    }

    @Override
    public ItemNews getData(int position) {
        return listNews.get(position);
    }

    @Override
    public void onClickNews(int position) {

    }

    @Override
    public void openNewDetailFragment(int id) {
        ((SocialActivity)getActivity()).openNewDetailFragment(id);
//        SocialFragment fragment = new SocialFragment();
//        fragment.addNewDetailFragment(id);
    }

    @Override
    public void onRefresh() {
        if (adapter.isLoadingMore()) {
            binding.homeRefresh.setRefreshing(false);
            return;
        }
    }
}
