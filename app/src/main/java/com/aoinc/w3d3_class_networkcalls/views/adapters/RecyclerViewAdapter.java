package com.aoinc.w3d3_class_networkcalls.views.adapters;

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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RepoViewHolder> {

    private List<GitResponse> repoList;

    public RecyclerViewAdapter(List<GitResponse> repoList) {
        this.repoList = repoList;
    }

    public void updateList(List<GitResponse> newList) {
        repoList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RepoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        GitResponse item = repoList.get(position);
        holder.textView.setText(item.getFullName());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.repo_item);
        }
    }
}
