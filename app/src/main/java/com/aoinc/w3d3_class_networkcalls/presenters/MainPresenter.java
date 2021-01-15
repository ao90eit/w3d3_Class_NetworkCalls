package com.aoinc.w3d3_class_networkcalls.presenters;

import android.util.Log;

import com.aoinc.w3d3_class_networkcalls.models.GitResponse;
import com.aoinc.w3d3_class_networkcalls.network.GitRetrofit;
import com.aoinc.w3d3_class_networkcalls.presenters.MainViewPresenterContract.*;
import com.aoinc.w3d3_class_networkcalls.views.adapters.RecyclerViewAdapter;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainPresenterInterface {
    private MainViewInterface mainView;
    private GitRetrofit gitRetrofit = new GitRetrofit();

    public MainPresenter(MainViewInterface mainView) {
        this.mainView = mainView;
    }

    @Override
    public void requestUserRepos(String username) {
        new Thread() {
            @Override
            public void run() {
                super.run();

                gitRetrofit.getUserRepos(username)
                        .enqueue(new Callback<List<GitResponse>>() {
                            @Override
                            public void onResponse(Call<List<GitResponse>> call, Response<List<GitResponse>> response) {
                                if(response.isSuccessful() && response.body() != null) {
                                    Log.d("REPO_REQUEST", "Response -> size = " + response.body().size() + " : " + response.body());

                                    List<GitResponse> gitResponses = response.body();
                                    mainView.updateRepoList(gitResponses);

                                    String picURL = gitResponses.get(0).getOwner().getAvatarUrl();
                                    mainView.updateProfilePic(picURL);
                                    
                                } else {
                                    Log.d("REPO_REQUEST", "Error -> something didn't work.");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<GitResponse>> call, Throwable t) {
                                Log.d("REPO_REQUEST", "Failure -> " + t + " " + call.request().url());
                            }
                        });
            }
        }.start();
    }
}
