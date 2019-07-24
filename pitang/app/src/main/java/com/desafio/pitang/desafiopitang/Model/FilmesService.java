package com.desafio.pitang.desafiopitang.Model;

import com.desafio.pitang.desafiopitang.Interfaces.Filmes;
import com.desafio.pitang.desafiopitang.Interfaces.ServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wagner on 02/03/2018.
 */

public class FilmesService implements Filmes.Service {

    Retrofit mRetrofit;
    public final static int MAX_FILMES_REQUISIZAO = 4;
    public final static String URL_BASE  = "https://desafio-android-pitang.herokuapp.com";
    public final static String SHARED_PREF_JSON_FILMES  = "cache_filmes";

    Filmes.Presenter presenter;


    public FilmesService(Filmes.Presenter presenter) {

        this.presenter = presenter;

        this.mRetrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getFilmes(int pagina) {
        ServiceAPI service = mRetrofit.create(ServiceAPI.class);

        Call<List<Filme>> call = service.getFilmes(pagina, MAX_FILMES_REQUISIZAO);

        call.enqueue(new Callback<List<Filme>>() {
            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                if (response.isSuccessful()) {
                    List<Filme> filmes = response.body();
                    presenter.adicionarFilmes(filmes);
                }
            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {
                presenter.erroRequisicaoServidor("Servidor indisponível no momento!");
            }
        });

    }

    @Override
    public void getFilme(String id) {
        ServiceAPI service = mRetrofit.create(ServiceAPI.class);

        Call<Filme> call = service.getFilme(id);

        call.enqueue(new Callback<Filme>() {
            @Override
            public void onResponse(Call<Filme> call, Response<Filme> response) {
                if (response.isSuccessful()) {
                    Filme filmeDetalhe = response.body();
                    presenter.enviarFilme(filmeDetalhe);
                }
            }

            @Override
            public void onFailure(Call<Filme> call, Throwable t) {
                presenter.erroRequisicaoServidor("Servidor indisponível no momento!");
            }
        });
    }
}




