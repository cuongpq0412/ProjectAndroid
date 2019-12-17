package com.example.snclient.ui;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.snclient.R;
import com.example.snclient.model.database.NewsStatus;
import com.example.snclient.databinding.FragmentLoginBinding;
import com.example.snclient.retrofitservices.RetrofitUnits;
import com.example.snclient.retrofitservices.Services;
import com.example.snclient.model.response.BaseResponse;
import com.example.snclient.model.request.LoginRequest;
import com.example.snclient.model.request.NewStatusRequest;
import com.example.snclient.model.database.UserProfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private  FragmentLoginBinding binding;
    private Services services;
    private File cacheFile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        binding.cvBtnSubmit.setOnClickListener(this);
        binding.goRegister.setOnClickListener(this);
        services = RetrofitUnits.getServices();
        return binding.getRoot();
    }

    public void createCache(int id){
        File cDir = Environment.getDataDirectory();
        String DATA_DIRECTORY = cDir.getPath()+"/data/"+ getContext().getPackageName()+ "/token";
        cacheFile = new File(DATA_DIRECTORY);
        cacheFile.mkdir();
        DATA_DIRECTORY = DATA_DIRECTORY+"/logintoken";
        cacheFile = new File(DATA_DIRECTORY);
        String strLine = "";
        StringBuilder text = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(cacheFile);
            BufferedReader bufferedReader  = new BufferedReader(fileReader);
           while (( strLine = bufferedReader.readLine())!=null){
               text.append(strLine+"\n");
               Toast.makeText(getContext(),"Temporarily saved contents in " + strLine,Toast.LENGTH_SHORT).show();
               Log.d("LoginFramgnet:","value is : " +strLine);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fileWriter=null;
        try {
            fileWriter = new FileWriter(cacheFile);
            fileWriter.write( String.valueOf(id));
            fileWriter.close();
            Toast.makeText(getContext(),"Temporarily saved contents in " + cacheFile.getPath(),Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cv_btn_submit:
                final LoginRequest request = new LoginRequest();
                String user = binding.etEmail.getText().toString();
                String pass = binding.etPassword.getText().toString();
                if (user == null || user.equals("")){
                    binding.etEmail.setHint("You doesn't input your username");
                    binding.etEmail.setHintTextColor(Color.RED);
                    return;
                }
                if (pass == null || pass.equals("")){
                    binding.etPassword.setHint("You doesn't input your password");
                    binding.etPassword.setHintTextColor(Color.RED);
                    return;
                }

                request.setUsername(user);
                request.setPassword(pass);
                services.login(request)
                        .enqueue(new Callback<BaseResponse<UserProfile>>() {
                            @Override
                            public void onResponse(Call<BaseResponse<UserProfile>> call, final Response<BaseResponse<UserProfile>> response) {
                                if (response.body().isSusccess()){
                                    Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();
                                    createCache(response.body().getData().getId());
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            NewStatusRequest nRequest = new NewStatusRequest();
                                            ((StartActivity)getActivity()).startSocialActivity(response.body().getData().getId());
                                        }
                                    },3000);
                                }else{
                                    Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<BaseResponse<UserProfile>> call, Throwable t) {
                                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.go_register:
                ((StartActivity)getActivity()).openRegisterFragment();

                break;
        }
    }

}
