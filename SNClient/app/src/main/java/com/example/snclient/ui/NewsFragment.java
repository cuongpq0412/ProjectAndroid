package com.example.snclient.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.snclient.comment.CommentAdapter;
import com.example.snclient.comment.ItemComment;
import com.example.snclient.databinding.FragmentNewsBinding;

import java.util.List;

public class NewsFragment extends Fragment implements CommentAdapter.IComment {
    private FragmentNewsBinding binding;
    private CommentAdapter adapter;
    private List<ItemComment> listComment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater,container,false);
//        binding.commentContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommentAdapter(this);
        //binding.commentContent.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public ItemComment getData(int position) {
        return null;
    }

    @Override
    public void onClickItem(int position) {

    }
}
