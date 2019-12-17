package com.example.snclient.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.snclient.R;
import com.example.snclient.databinding.FragmentRegisterBinding;
import com.example.snclient.retrofitservices.RetrofitUnits;
import com.example.snclient.retrofitservices.Services;
import com.example.snclient.model.response.BaseResponse;
import com.example.snclient.model.request.RegisterRequest;
import com.example.snclient.model.database.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private FragmentRegisterBinding binding;
    private Services services;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        services = RetrofitUnits.getServices();
        binding.btnRegister.setOnClickListener(this);
        binding.goLogin.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                final RegisterRequest request = new RegisterRequest();
                String tvUser = binding.tvUsername.getText().toString();
                String tvPass = binding.tvPassword.getText().toString();
                String tvRewritePass = binding.tvConfirmPassword.getText().toString();
                String tvDob = binding.tvDob.getText().toString();
                if (tvUser == null || tvUser.equals("")) {
                    binding.tvUsername.setHint("You doesn't input your username");
                    binding.tvUsername.setHintTextColor(Color.RED);
                    return;
                }
                if (tvPass == "" || tvPass.equals("")) {
                    binding.tvPassword.setHint("You doesn't input your password");
                    binding.tvPassword.setHintTextColor(Color.RED);
                    return;
                }
                if (tvRewritePass == "" || tvRewritePass.equals("")) {
                    binding.tvConfirmPassword.setHint("You doesn't input your password");
                    binding.tvConfirmPassword.setHintTextColor(Color.RED);
                    return;
                }
                if (!tvPass.equals(tvRewritePass)) {
                    binding.tvConfirmPassword.setHint("Your confirm pass is not success");
                    binding.tvConfirmPassword.setHintTextColor(Color.RED);
                    return;
                }
                if (tvDob == "" || tvDob.equals("")) {
                    binding.tvDob.setHint("You doesn't input your date of birth");
                    binding.tvDob.setHintTextColor(Color.RED);
                    return;
                }
                    request.setUsername(tvUser);
                    request.setPassword(tvPass);
                    request.setAvatar("test");
                    request.setDob(tvDob);
                    services.register(request)
                            .enqueue(new Callback<BaseResponse<UserProfile>>() {
                                @Override
                                public void onResponse(Call<BaseResponse<UserProfile>> call, Response<BaseResponse<UserProfile>> response) {
                                    if (response.body().isSusccess()) {
                                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                ((StartActivity) getActivity()).openLoginFragment();
                                            }
                                        }, 4000);
                                    } else {
                                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<BaseResponse<UserProfile>> call, Throwable t) {
                                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                break;
            case R.id.go_login:
                ((StartActivity) getActivity()).openLoginFragment();
                break;
        }
    }
}
