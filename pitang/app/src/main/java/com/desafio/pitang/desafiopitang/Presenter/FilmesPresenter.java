package com.desafio.pitang.desafiopitang.Presenter;

import android.content.Context;
import android.net.ConnectivityManager;

import com.desafio.pitang.desafiopitang.Interfaces.Filmes;
import com.desafio.pitang.desafiopitang.Model.Filme;
import com.desafio.pitang.desafiopitang.Model.FilmesService;

import java.util.List;

/**
 * Created by Wagner on 02/03/2018.
 */

public class FilmesPresenter implements com.desafio.pitang.desafiopitang.Interfaces.Filmes.Presenter {

    private Filmes.View viewFilmes;
    private Filmes.Service serviceFilmes;

    public FilmesPresenter(com.desafio.pitang.desafiopitang.Interfaces.Filmes.View view) {
        this.serviceFilmes = new FilmesService(this);
        this.viewFilmes = view;
    }

    @Override
    public void getFilmes(int pagina) {
        if(verificaConexao(this.viewFilmes.getContexto())){
            serviceFilmes.getFilmes(pagina);
        }else{
            viewFilmes.exibirErroRequisicao("Sem conexão com a internet!");
        }
    }

    @Override
    public void getFilme(String id) {
        if(verificaConexao(this.viewFilmes.getContexto())){
            serviceFilmes.getFilme(id);
        }else{
            viewFilmes.exibirErroRequisicao("Sem conexão com a internet!");
        }
    }

    @Override
    public boolean verificaConexao(Context contexto) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo().isAvailable() && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    @Override
    public void adicionarFilmes(List<Filme> filmesNews){
        List<Filme> filmesOld =  viewFilmes.getFilmes();

        if(filmesOld == null && filmesNews.size() > 0){
            viewFilmes.initRecyclerView(filmesNews);
        }else if (filmesOld.size() > 0 || filmesNews.size() > 0 ){
            filmesOld.addAll(filmesNews);
            viewFilmes.atualizarListFilmes(filmesOld);
        }else{
            viewFilmes.exibirErroRequisicao("Nenhum filme foi encontrado");
        }
    }

    @Override
    public void enviarFilme(Filme filmeDetalhe) {
        viewFilmes.exibirDetalhes(filmeDetalhe);
    }

    @Override
    public void erroRequisicaoServidor(String msgErro){
        viewFilmes.exibirErroRequisicao(msgErro);
    }


}
