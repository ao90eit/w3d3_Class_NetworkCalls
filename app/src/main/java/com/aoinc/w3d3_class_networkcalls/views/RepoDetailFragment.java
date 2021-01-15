package com.aoinc.w3d3_class_networkcalls.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aoinc.w3d3_class_networkcalls.R;
import com.aoinc.w3d3_class_networkcalls.models.GitResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepoDetailFragment extends Fragment {

    private GitResponse gitResponse;

    @BindView(R.id.repo_detail_title_textView)
    public TextView titleTextView;

    @BindView(R.id.repo_detail_created_textView)
    public TextView createdTextView;

    @BindView(R.id.repo_detail_updated_textView)
    public TextView updatedTextView;

    @BindView(R.id.repo_detail_stargazers_textView)
    public TextView stargazersTextView;

    @BindView(R.id.repo_detail_goto_button)
    public Button goToRepoButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.repo_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        gitResponse = getArguments().getParcelable(MainActivity.GIT_PARCELABLE);

        titleTextView.setText(gitResponse.getName());
        createdTextView.setText(gitResponse.getCreatedAt());
        updatedTextView.setText(gitResponse.getUpdatedAt());
        stargazersTextView.setText(String.valueOf(gitResponse.getStargazersCount()));
    }

    @OnClick(R.id.repo_detail_goto_button)
    public void onClickGoToRepo(View view) {
        goToRepoButton.setText("Not Yet Implemented.");
    }
}
