package com.bb.restapp.view;

import android.os.Bundle;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bb.restapp.R;
import com.bb.restapp.adapter.RepositoryAdapter;
import com.bb.restapp.model.GitResult;
import com.bb.restapp.model.TransportObj;
import com.bb.restapp.util.Constants;
import com.bb.restapp.util.DebugLogger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GitRepoFragment extends Fragment {

    @BindView(R.id.git_recyclerview)
    RecyclerView gitRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.git_repo_frag_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        List<GitResult> results = ((TransportObj)getArguments().getParcelable(Constants.FRAG_KEY)).getGitResults();
        DebugLogger.logDebug("Results = " + results.size());

        RepositoryAdapter repositoryAdapter = new RepositoryAdapter(results);
        gitRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gitRecyclerView.setAdapter(repositoryAdapter);
    }
}
