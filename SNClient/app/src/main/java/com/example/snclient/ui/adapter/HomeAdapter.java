package com.example.snclient.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.snclient.model.ItemNews;
import com.example.snclient.R;


import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {
    private ISocial inter;
    private boolean isLoadingMore;

    public HomeAdapter(ISocial inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new HomeHolder(view);
    }

    public void setLoadingMore(boolean loadingMore) {
        isLoadingMore = loadingMore;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
//        if (position == getItemCount() - 1 && !isLoadingMore) {
//            isLoadingMore = true;
//            return;
//        }
//        if (getItemCount()==0){
//            return;
//        }else{
        final ItemNews data = inter.getData(position);
        holder.nName.setText(data.getnName());
        holder.nContent.setText(data.getnContent());
        holder.nDate.setText(data.getTimeNews());
        holder.nNumLike.setText(data.getnNumLike());
        holder.nNumComment.setText(data.getnNumComment());
        if (data.getLinkAvatar() == null && data.getLinkAvatar().equals("")) {
            Glide.with(holder.avatar)
                    .load(R.drawable.ic_menu_grey_700_48dp)
                    .into(holder.avatar);
        } else {
            Glide.with(holder.avatar)
                    .load(data.getLinkAvatar())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.avatar);
        }
        if (data.getnImage() == null && data.getnImage().equals("")) {
            Glide.with(holder.nLinkImage)
                    .load(R.drawable.avatar)
                    .into(holder.nLinkImage);
        } else {
            Glide.with(holder.nLinkImage)
                    .load(data.getnImage())
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.avatar)
                    .into(holder.nLinkImage);
        }
        holder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.openNewDetailFragment(data.getId());
            }
        });
//        }

    }


    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public boolean isLoadingMore() {
        return isLoadingMore;
    }


    static class HomeHolder extends RecyclerView.ViewHolder {
        private CircleImageView avatar;
        private TextView nName, nDate, nContent, nNumLike, nNumComment;
        private ImageView nLinkImage;
        private LinearLayout btnComment;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            nName = itemView.findViewById(R.id.acount_name);
            nDate = itemView.findViewById(R.id.datetime);
            nLinkImage = itemView.findViewById(R.id.news_image);
            nContent = itemView.findViewById(R.id.news_content);
            nNumLike = itemView.findViewById(R.id.number_like);
            nNumComment = itemView.findViewById(R.id.number_comment);
            btnComment = itemView.findViewById(R.id.btn_comment);
        }
    }

    public interface ISocial {
        int getCount();

        ItemNews getData(int position);

        void onClickNews(int position);

        void openNewDetailFragment(int id);
    }
}
