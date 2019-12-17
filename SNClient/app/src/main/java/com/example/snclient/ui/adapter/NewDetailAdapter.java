package com.example.snclient.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.snclient.R;
import com.example.snclient.comment.ItemComment;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewDetailAdapter extends RecyclerView.Adapter<NewDetailAdapter.NewDetailHolder> {
    private INewDetail inter;

    public NewDetailAdapter(INewDetail inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public NewDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        return new NewDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewDetailHolder holder, int position) {
        ItemComment data= inter.getData(position);
        holder.userName.setText(data.getName());
        holder.content.setText(data.getContent());
        holder.time.setText(data.getTimeUp());
        if (data.getAvatar()==null||data.getAvatar().equals("")){
            Glide.with(holder.linkAvata)
                    .load(R.drawable.avatar)
                    .into(holder.linkAvata);
        }else{
            Glide.with(holder.linkAvata)
                    .load(data.getAvatar())
                    .error(R.drawable.ic_account_circle_grey_700_48dp)
                    .placeholder(R.drawable.ic_account_circle_grey_700_48dp)
                    .into(holder.linkAvata);
        }
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public interface INewDetail{
        int getCount();
        ItemComment getData(int position);
        void onClickItem();
    }
    static class NewDetailHolder extends RecyclerView.ViewHolder {
    private TextView userName,content, time;
//    private LinearLayout btnLike,btnComment,btnShare;
    private CircleImageView linkAvata;
        public NewDetailHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.acount_name);
            content = itemView.findViewById(R.id.comment_content);
//            btnLike = itemView.findViewById(R.id.btn_like);
//            btnComment = itemView.findViewById(R.id.btn_comment);
//            btnShare = itemView.findViewById(R.id.btn_share);
            time = itemView.findViewById(R.id.cmt_time);
            linkAvata = itemView.findViewById(R.id.cm_avatar);
        }
    }
}
