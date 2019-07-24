package com.desafio.pitang.desafiopitang.View;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.desafio.pitang.desafiopitang.Interfaces.ButtonInterf;
import com.desafio.pitang.desafiopitang.Interfaces.Filmes;
import com.desafio.pitang.desafiopitang.Model.Filme;
import com.desafio.pitang.desafiopitang.Presenter.FilmesPresenter;
import com.desafio.pitang.desafiopitang.R;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilmesActivity extends AppCompatActivity implements Filmes.View, FilmesAdapter.ItemClickListener, ButtonInterf {

    private static final int SIMULADOR_LOADING_TEMPO_EM_MS = 1500;

    private Context contexto;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.btn_tentar_novamente)
    Button btnTentarNovamente;

    public LinearLayoutManager layoutManager;

    private FilmesAdapter adapter;

    private Filmes.Presenter presenter;

    private int page;

    private List<Filme> filmes;

    private DialogFragment dialogDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes);


        contexto = this;
        presenter = new FilmesPresenter(this);

        ButterKnife.bind(this);

        presenter.getFilmes(page);
    }

    public void initRecyclerView(List<Filme> filmes){
        btnTentarNovamente.setVisibility(View.GONE);
        this.filmes = filmes;

        layoutManager  =   new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new FilmesAdapter(this, this.filmes);
        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(createInfiniteScrollListener());
    }

    @Override
    public void onItemClick(View view, int position) {
        presenter.getFilme(filmes.get(position).getId());
    }

    @Override
    public Context getContexto() {
        return this.contexto;
    }

    @Override
    public List<Filme> getFilmes() {
        return this.filmes;
    }

    @Override
    public void atualizarListFilmes(List<Filme> filmes) {
        btnTentarNovamente.setVisibility(View.GONE);
        this.filmes = filmes;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void exibirDetalhes(Filme filmeDetalhe) {
        if(dialogDetalhe != null && !dialogDetalhe.isVisible()){
        dialogDetalhe =  DetalheDialogFragment.newInstance(filmeDetalhe);
        dialogDetalhe.show(getSupportFragmentManager(), "missiles");
        }else if(dialogDetalhe == null){
            dialogDetalhe =  DetalheDialogFragment.newInstance(filmeDetalhe);
            dialogDetalhe.show(getSupportFragmentManager(), "missiles");
        }

    }

    @OnClick(R.id.btn_tentar_novamente)
    public void onButtonClicked() {
        simularLoading();
        presenter.getFilmes(page);
    }

    @Override
    public void exibirErroRequisicao(String msgErro){

        Toast.makeText(this, msgErro, Toast.LENGTH_SHORT).show();

            if (filmes == null || filmes.size() < 1) {
                btnTentarNovamente.setVisibility(View.VISIBLE);
            } else {
                btnTentarNovamente.setVisibility(View.GONE);
            }

    }

    @NonNull private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(4,layoutManager) {
            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
                simularLoading();
                ++page;
                presenter.getFilmes(page);

            }
        };
    }

    private void simularLoading() {
        new AsyncTask<Void, Void, Void>() {
            @Override protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(SIMULADOR_LOADING_TEMPO_EM_MS);
                } catch (InterruptedException e) {
                    Log.e("MainActivity", e.getMessage());
                }
                return null;
            }

            @Override protected void onPostExecute(Void param) {
                progressBar.setVisibility(View.GONE);
            }
        }.execute();
    }
}