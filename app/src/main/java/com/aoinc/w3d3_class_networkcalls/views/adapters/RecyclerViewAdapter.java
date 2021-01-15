package com.aoinc.w3d3_class_networkcalls.views.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aoinc.w3d3_class_networkcalls.R;
import com.aoinc.w3d3_class_networkcalls.models.GitResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RepoViewHolder> {

    private List<GitResponse> repoList;
    private OnRepoClickListener onRepoClickListener;

    public RecyclerViewAdapter(List<GitResponse> repoList, OnRepoClickListener onRepoClickListener) {
        this.repoList = repoList;
        this.onRepoClickListener = onRepoClickListener;
    }

    public void updateList(List<GitResponse> newList) {
        repoList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RepoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item_layout, parent, false), onRepoClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        GitResponse response = repoList.get(position);
        holder.textView.setText(response.getName());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public interface OnRepoClickListener {
        void onRepoItemClick(int position);
    }

    class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.repo_item)
        public TextView textView;

        OnRepoClickListener onRepoClickListener;

        public RepoViewHolder(@NonNull View itemView, OnRepoClickListener onRepoClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.onRepoClickListener = onRepoClickListener;
        }

        @Override
        public void onClick(View v) {
            Log.d("repo_listener", "on click in holder");
            onRepoClickListener.onRepoItemClick(getAdapterPosition());
        }
    }
}
