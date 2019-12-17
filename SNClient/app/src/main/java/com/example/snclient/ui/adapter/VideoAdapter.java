package com.example.snclient.ui.adapter;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.snclient.model.ItemVideo;
import com.example.snclient.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {
    private IVideos inter;


    public VideoAdapter(IVideos inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        ItemVideo data = inter.getData(position);
        holder.nName.setText(data.getnName());

        holder.nDate.setText(data.getnDate());
        holder.nNumLike.setText(data.getnNumLike());
        holder.nNumComment.setText(data.getnNumComment());
        if (data.getLinkAvatar() == null && data.getLinkAvatar().equals("")) {
            Glide.with(holder.nLinkImage)
                    .load(R.drawable.ic_ondemand_video_red_500_24dp)
                    .into(holder.nLinkImage);
        } else {
//            Glide.with(holder.nLinkImage)
//                    .load(data.getnLinkVideo())
//                    .placeholder(R.drawable.ic_ondemand_video_red_500_24dp)
//                    .error(R.drawable.ic_ondemand_video_red_500_24dp)
//                    .into(holder.nLinkImage);
        }
        if (data.getnImage() == null && data.getLinkAvatar().equals("")) {
            Glide.with(holder.avatar)
                    .load(R.drawable.avatar)
                    .into(holder.avatar);
        } else {
            Glide.with(holder.avatar)
                    .load(data.getLinkAvatar())
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.avatar)
                    .into(holder.avatar);
        }
        if (data.getnLinkVideo()==null||data.getnLinkVideo().equals("")){
            return;
        }else{
            MediaController mediaController = new MediaController(inter.getContext());
            mediaController.setMediaPlayer( holder.VideoContent);
            holder.VideoContent.setMediaController(mediaController);
            holder.VideoContent.setVideoURI(Uri.parse(data.getnLinkVideo()));
//            holder.VideoContent.start();
        }
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public interface IVideos{
        int getCount();
        ItemVideo getData(int position);
        void onClickItem(int position);
        Context getContext();
    }
    static class VideoHolder extends RecyclerView.ViewHolder {
        private CircleImageView avatar;
        private VideoView VideoContent;
        private TextView nName, nDate, nNumLike, nNumComment;
        private ImageView nLinkImage;
        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            nName = itemView.findViewById(R.id.acount_name);
            nDate = itemView.findViewById(R.id.datetime);
            nLinkImage = itemView.findViewById(R.id.news_image);
            VideoContent = itemView.findViewById(R.id.vv_video);
            nNumLike = itemView.findViewById(R.id.number_like);
            nNumComment = itemView.findViewById(R.id.number_comment);
        }
    }
}
