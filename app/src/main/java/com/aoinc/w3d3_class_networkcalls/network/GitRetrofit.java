package com.aoinc.w3d3_class_networkcalls.network;

import com.aoinc.w3d3_class_networkcalls.models.GitResponse;
import com.aoinc.w3d3_class_networkcalls.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitRetrofit {
    private GitApi gitApi;

    public GitRetrofit() {
        gitApi = createGitApi(createRetrofit());
    }

    private GitApi createGitApi(Retrofit retrofit) {
        return retrofit.create(GitApi.class);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<List<GitResponse>> getUserRepos(String userName) {
        return gitApi.getGitResponse(userName);
    }
}
