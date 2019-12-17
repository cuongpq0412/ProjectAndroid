package com.example.snclient.ui.adapter;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.snclient.R;
import com.example.snclient.model.ItemFriend;
import com.example.snclient.model.database.Friend;
import com.example.snclient.model.request.AddFriendRequest;
import com.example.snclient.model.request.FriendRequest;
import com.example.snclient.model.response.BaseResponse;
import com.example.snclient.retrofitservices.Services;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {
    private IFriend inter;
    private Services services;

    public FriendAdapter(IFriend inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend,parent,false);
        return new FriendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FriendHolder holder, int position) {
        final ItemFriend data = inter.getData(position);
        if (data==null){
            return;
        }
        holder.falname.setText(data.getFalname());
        holder.country.setText(data.getCountry());
        holder.info.setText(data.getUserInfo());
        if (data.getLinkAvatar()==null||data.getLinkAvatar().equals("")){
            Glide.with(holder.avatar)
                    .load(R.drawable.avatar)
                    .into(holder.avatar);
        }else{
            Glide.with(holder.avatar)
                    .load(data.getLinkAvatar())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.avatar);
        }
        holder.addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_user = data.getId_user();
                int id_friend = data.getId_friend();
                inter.addFriend(id_user,id_friend);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public interface IFriend{
        int getCount();
        ItemFriend getData(int position);
        void addFriend(int id_user, int id_friend);
        Services getServices();
    }
    static class FriendHolder extends RecyclerView.ViewHolder {
        private ImageView avatar;
        private TextView falname;
        private TextView country;
        private TextView info;
        private Button addFriend;

        public FriendHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.ci_avatar);
            falname = itemView.findViewById(R.id.tv_username);
            country = itemView.findViewById(R.id.tv_country);
            info = itemView.findViewById(R.id.tv_userinfo);
            addFriend = itemView.findViewById(R.id.btn_adduser);

        }
    }
}
