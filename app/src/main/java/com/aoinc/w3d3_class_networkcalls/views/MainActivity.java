package com.aoinc.w3d3_class_networkcalls.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.aoinc.w3d3_class_networkcalls.R;
import com.aoinc.w3d3_class_networkcalls.models.GitResponse;
import com.aoinc.w3d3_class_networkcalls.network.GitRetrofit;
import com.aoinc.w3d3_class_networkcalls.views.adapters.RecyclerViewAdapter;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GitRetrofit gitRetrofit = new GitRetrofit();
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.repos_info_recyclerView);

        new Thread() {
            @Override
            public void run() {
                super.run();

                gitRetrofit.getUserRepos("aormsbyEIT")
                        .enqueue(new Callback<List<GitResponse>>() {
                            @Override
                            public void onResponse(Call<List<GitResponse>> call, Response<List<GitResponse>> response) {
                                if(response.isSuccessful() && response.body() != null) {
                                    Log.d("TAG_X", "Response -> size = " + response.body().size() + " : " + response.body());
                                    recyclerView.setAdapter(new RecyclerViewAdapter(response.body()));
                                } else {
                                    Log.d("TAG_X", "Error -> something didn't work.");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<GitResponse>> call, Throwable t) {
                                Log.d("TAG_X", "Failure -> " + t + " " + call.request().url());
                            }
                        });
            }
        }.start();
    }
}