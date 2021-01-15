package com.aoinc.w3d3_class_networkcalls.network;

import com.aoinc.w3d3_class_networkcalls.models.GitResponse;
import com.aoinc.w3d3_class_networkcalls.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitApi {
    // BASE_URL = "https://api.github/com/ + URL_PATH
    // URL_PATH = "/users/{"+USER_NAME_PATH+"}/repos"
    @GET(Constants.URL_PATH)
    Call<List<GitResponse>> getGitResponse(@Path(Constants.USER_NAME_PATH) String userName);
}
