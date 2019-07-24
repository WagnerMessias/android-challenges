package com.desafio.pitang.desafiopitang.Interfaces;

import android.content.Context;

import com.desafio.pitang.desafiopitang.Model.Filme;

import java.util.List;

/**
 * Created by Wagner on 02/03/2018.
 */

public interface Filmes {

    interface View{
        Context getContexto();
        List<Filme> getFilmes();
        void atualizarListFilmes(List<Filme> filmes);
        void exibirErroRequisicao(String msgErro);
        void initRecyclerView(List<Filme> filmes);
        void exibirDetalhes(Filme filmeDetalhe);
    }

    interface Presenter {
        void getFilmes(int page);
        void getFilme(String id);
        boolean verificaConexao(Context contexto);
        void adicionarFilmes(List<Filme> filmes);
        void erroRequisicaoServidor(String msgErro);
        void enviarFilme(Filme filmeDetalhe);
    }

    interface Service {
     void   getFilmes(int page);
     void   getFilme(String id);

    }
}
