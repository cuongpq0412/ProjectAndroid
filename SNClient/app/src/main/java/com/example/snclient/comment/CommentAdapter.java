package com.example.snclient.comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.snclient.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private IComment inter;

    public CommentAdapter(IComment inter) {
        this.inter = inter;
    }
    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);

        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        ItemComment data = inter.getData(position);
        holder.name.setText(data.getName());
        holder.content.setText(data.getContent());
        holder.time.setText(data.getTimeUp());
        if (data.getAvatar() == null && data.getAvatar().equals("")) {
            Glide.with(holder.avatar)
                    .load(R.drawable.avatar)
                    .into(holder.avatar);
        }else{
            Glide.with(holder.avatar)
                    .load(data.getAvatar())
                    .error(R.drawable.avatar)
                    .placeholder(R.drawable.avatar)
                    .into(holder.avatar);
        }
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    static class CommentHolder extends RecyclerView.ViewHolder {
        private CircleImageView avatar;
        private TextView name, content, time;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.acount_name);
            content = itemView.findViewById(R.id.comment_content);
            time = itemView.findViewById(R.id.time);
            avatar = itemView.findViewById(R.id.acount_name);
        }
    }

    public interface IComment {
        int getCount();
        ItemComment getData(int position);
        void onClickItem(int position);
    }
}
