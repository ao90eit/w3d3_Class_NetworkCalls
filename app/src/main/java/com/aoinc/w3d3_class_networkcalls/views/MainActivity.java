package com.aoinc.w3d3_class_networkcalls.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.aoinc.w3d3_class_networkcalls.R;
import com.aoinc.w3d3_class_networkcalls.models.GitResponse;
import com.aoinc.w3d3_class_networkcalls.presenters.MainPresenter;
import com.aoinc.w3d3_class_networkcalls.presenters.MainViewPresenterContract.MainViewInterface;
import com.aoinc.w3d3_class_networkcalls.views.RepoListFragment.RepoListFragmentInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements MainViewInterface, RepoListFragmentInterface {

    MainPresenter mainPresenter;

    ProfileInfoFragment profileInfoFragment;
    RepoListFragment repoListFragment;
    public List<GitResponse> gitResponseList = new ArrayList<>();
    RepoDetailFragment repoDetailFragment = new RepoDetailFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        profileInfoFragment = (ProfileInfoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_profile_info_fragment);
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
                gitResponseList = newGitResponses;
                repoListFragment.updateRepoList(newGitResponses);
            }
        });
    }

    @Override
    public void updateProfilePic(String picURL) {
        profileInfoFragment.updateProfilePic(picURL);
    }

    @Override
    public void updateUserName(String userName) {
        profileInfoFragment.updateUserName(userName);
    }

    public static final String GIT_PARCELABLE = "git_parcelable";
    @Override
    public void displayDetailFragment(GitResponse gitResponseItem) {
        Bundle gitBundle = new Bundle();
        gitBundle.putParcelable(GIT_PARCELABLE, gitResponseItem);
        repoDetailFragment.setArguments(gitBundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_repos_list_fragment, repoDetailFragment)
                .addToBackStack(repoDetailFragment.getTag())
                .commit();
    }
}