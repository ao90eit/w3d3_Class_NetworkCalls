package com.aoinc.w3d3_class_networkcalls.views;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.aoinc.w3d3_class_networkcalls.views.adapters.RecyclerViewAdapter.OnRepoClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListFragment extends Fragment
        implements OnRepoClickListener {

    @BindView(R.id.repos_list_recyclerView)
    public RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>(), this);


    private RepoListFragmentInterface mainView;
    public interface RepoListFragmentInterface {
        void displayDetailFragment(GitResponse gitResponseItem);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainView = (RepoListFragmentInterface) context;
    }

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

    @Override
    public void onRepoItemClick(int position) {
        GitResponse response = ((MainActivity) mainView).gitResponseList.get(position);
//        Log.d("repo_listener", response.getName());
        mainView.displayDetailFragment(response);
    }
}
