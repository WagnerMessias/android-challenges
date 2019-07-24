package com.desafio.pitang.desafiopitang.Interfaces;

import com.desafio.pitang.desafiopitang.Model.Filme;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Wagner on 02/03/2018.
 */

public interface ServiceAPI {

    @GET("/movies/list")
    Call<List<Filme>>getFilmes(@Query("page") int page, @Query("size") int size);

    @GET("/movies/detail/{id}")
    Call<Filme> getFilme(@Path("id") String id);

}