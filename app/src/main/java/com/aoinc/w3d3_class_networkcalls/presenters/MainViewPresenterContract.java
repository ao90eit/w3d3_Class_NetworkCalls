package com.aoinc.w3d3_class_networkcalls.presenters;

import android.content.Context;

import com.aoinc.w3d3_class_networkcalls.models.GitResponse;

import java.util.List;

public interface MainViewPresenterContract {
    public interface MainPresenterInterface {
        void requestUserRepos(String username);
    }

    public interface MainViewInterface {
        Context getContext();
        void updateRepoList(List<GitResponse> gitResponses);
        void updateProfilePic(String picURL);
        void updateUserName(String userName);
    }
}
