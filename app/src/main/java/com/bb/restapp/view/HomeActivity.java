package com.bb.restapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bb.restapp.R;
import com.bb.restapp.model.GitResult;
import com.bb.restapp.model.TransportObj;
import com.bb.restapp.network.GitRetrofitInstance;
import com.bb.restapp.util.Constants;
import com.bb.restapp.util.DebugLogger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {

    private GitRetrofitInstance retrofitInstance = new GitRetrofitInstance();

    private GitRepoFragment gitRepoFragment = new GitRepoFragment();
    private GitUserFragment gitUserFragment = new GitUserFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRepositories();
    }

    private void getRepositories(){

        retrofitInstance.getRepos("ktrcampbell")
                .enqueue(new Callback<List<GitResult>>() {
                    @Override
                    public void onResponse(Call<List<GitResult>> call, Response<List<GitResult>> response) {

//                        DebugLogger.logDebug(response.body().toString());

                        showFragments(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<GitResult>> call, Throwable t) {
                        DebugLogger.logError(new Exception(t));
                    }
                });
    }

    private void showFragments(List<GitResult>gitResults){

        //use Bundle to pass data from one fragment to another (or activity) instead of Intent
        Bundle repositoryBundle = new Bundle();
        TransportObj transportObj = new TransportObj(gitResults);
        repositoryBundle.putParcelable(Constants.FRAG_KEY, transportObj);

        gitRepoFragment.setArguments(repositoryBundle);
        gitUserFragment.setArguments(repositoryBundle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.git_user_frame, gitUserFragment)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.git_repo_frame, gitRepoFragment)
                .commit();

    }
}
