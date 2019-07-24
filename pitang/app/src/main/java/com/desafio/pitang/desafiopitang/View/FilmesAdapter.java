package com.desafio.pitang.desafiopitang.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.desafio.pitang.desafiopitang.Model.Filme;
import com.desafio.pitang.desafiopitang.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wagner on 01/03/2018.
 */

public class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.ViewHolder> {

    public List<Filme> filmes;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContexto;

    FilmesAdapter(Context context, List<Filme> filmes) {
        this.mInflater = LayoutInflater.from(context);
        this.filmes = filmes;
        this.mContexto = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_filme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Filme filme = filmes.get(position);
        holder.tv_nome.setText(filme.getNome());

        Glide.with(mContexto)
                .load(filme.getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_nome_filme)
        TextView tv_nome;

        @BindView(R.id.iw_filme)
        ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    Filme getItem(int id) {
        return filmes.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}