package com.aoinc.w3d3_class_networkcalls.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.aoinc.w3d3_class_networkcalls.R;
import com.aoinc.w3d3_class_networkcalls.models.GitResponse;
import com.aoinc.w3d3_class_networkcalls.views.adapters.RecyclerViewAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListFragment extends Fragment {

    @BindView(R.id.repos_list_recyclerView)
    public RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());

    @BindView(R.id.repos_profile_pic)
    ImageView profilePicImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.repo_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void updateRepoList(List<GitResponse> gitResponsesList){
        recyclerViewAdapter.updateList(gitResponsesList);
    }

    public void updateProfilePic(String picURL) {
        Glide.with(this)
                .load(picURL)
                .placeholder(R.drawable.ic_baseline_person_24)
                .circleCrop()
//                .transition(withCrossFade())
                .into(profilePicImageView);
    }
}
