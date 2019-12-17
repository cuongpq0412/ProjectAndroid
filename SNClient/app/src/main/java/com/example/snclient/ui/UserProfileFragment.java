package com.example.snclient.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.snclient.R;
import com.example.snclient.databinding.FragmentUserProfileBinding;
import com.example.snclient.model.database.UserProfile;
import com.example.snclient.model.request.NewStatusRequest;
import com.example.snclient.model.response.BaseResponse;
import com.example.snclient.retrofitservices.RetrofitUnits;
import com.example.snclient.retrofitservices.Services;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserProfileFragment extends Fragment implements View.OnClickListener {
    private FragmentUserProfileBinding binding;
    private File cacheFile;
    private Services services;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentUserProfileBinding.inflate(inflater,container,false);
        services  = RetrofitUnits.getServices();
        Bundle bundle = this.getArguments();
        int id = bundle.getInt("iduser");
        getUserProfile(id);
        binding.btnSignout.setOnClickListener(this);
        return binding.getRoot();
    }
    private void getUserProfile(int id){
        NewStatusRequest request = new NewStatusRequest();
        request.setId_user(id);
        services.getUserById(request).enqueue(new Callback<BaseResponse<UserProfile>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserProfile>> call, Response<BaseResponse<UserProfile>> response) {
                UserProfile userProfile =response.body().getData();
                if (userProfile==null){
                    return;
                }
                binding.etName.setText(userProfile.getFalname());
                binding.etDatebirth.setText(userProfile.getDob());
                binding.etPhone.setText(userProfile.getPhone());
                if (userProfile.getAvatar()==null||userProfile.getAvatar().equals("")){
                    Glide.with(binding.avatar)
                            .load(R.drawable.avatar)
                            .into(binding.avatar);
                }else {
                    Glide.with(binding.avatar)
                            .load(userProfile.getAvatar())
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                            .into(binding.avatar);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<UserProfile>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signout:
                File cDir = Environment.getDataDirectory();
                String DATA_DIRECTORY = cDir.getPath()+"/data/"+ getContext().getPackageName()+ "/token/logintoken";
                cacheFile = new File(DATA_DIRECTORY);
                if (cacheFile.delete()==true){
                    ((SocialActivity)getActivity()).openStartActivity();
                }
                break;
        }
    }
}
