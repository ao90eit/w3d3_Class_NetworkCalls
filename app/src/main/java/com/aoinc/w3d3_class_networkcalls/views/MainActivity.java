package com.aoinc.w3d3_class_networkcalls.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.aoinc.w3d3_class_networkcalls.R;
import com.aoinc.w3d3_class_networkcalls.models.GitResponse;
import com.aoinc.w3d3_class_networkcalls.presenters.MainPresenter;
import com.aoinc.w3d3_class_networkcalls.presenters.MainViewPresenterContract.MainViewInterface;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements MainViewInterface {

    MainPresenter mainPresenter;
    RepoListFragment repoListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        repoListFragment = (RepoListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_repos_list_fragment);

        mainPresenter = new MainPresenter(this);
        mainPresenter.requestUserRepos("aormsbyEIT");
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateRepoList(List<GitResponse> newGitResponses) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                recyclerViewAdapter.updateList(newGitResponses);
                repoListFragment.updateRepoList(newGitResponses);
            }
        });
    }

    @Override
    public void updateProfilePic(String picURL) {
        repoListFragment.updateProfilePic(picURL);
    }
}