package com.bb.restapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bb.restapp.R;
import com.bb.restapp.model.GitResult;
import com.bb.restapp.model.TransportObj;
import com.bb.restapp.util.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GitUserFragment extends Fragment {

    List<GitResult> gitList;

    @BindView(R.id.user_imageview)
    ImageView userImageView;

    @BindView(R.id.user_textView)
    TextView userName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.git_user_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        gitList = ((TransportObj) getArguments().getParcelable(Constants.FRAG_KEY)).getGitResults();

        setUpViews();
    }

    private void setUpViews(){

        Glide.with(getContext())
                .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(gitList.get(0).getOwner().getAvatarUrl())
                .into(userImageView);

        userName.setText(gitList.get(0).getOwner().getLogin());
    }
}
