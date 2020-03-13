package com.bb.restapp.network;

import com.bb.restapp.model.GitResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitService {

    @GET("/users/{userName}/repos")
    Call<List<GitResult>> getRepo(@Path("userName") String userName);

}
