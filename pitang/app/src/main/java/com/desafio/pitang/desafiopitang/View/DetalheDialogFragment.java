package com.desafio.pitang.desafiopitang.View;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.desafio.pitang.desafiopitang.Interfaces.ButtonInterf;
import com.desafio.pitang.desafiopitang.Model.Filme;
import com.desafio.pitang.desafiopitang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wagner on 03/03/2018.
 */

public class DetalheDialogFragment extends DialogFragment {

    @BindView(R.id.dialog_tv_nome)
    TextView tv_nome;

    @BindView(R.id.dialog_tv_descricao)
    TextView tv_descricao;

    @BindView(R.id.dialog_iw_capa)
    ImageView iw_capa;

    @BindView(R.id.dialog_prprogress)
    ProgressBar progressBar;

    private ButtonInterf buttonInterfDialogAction;

    private static final double DIALOG_WINDOW_WIDTH = 1;

    public static DetalheDialogFragment newInstance(Filme filme) {


        DetalheDialogFragment df = new DetalheDialogFragment();

        Bundle args = new Bundle();
        args.putString("nome", filme.getNome());
        args.putString("descricao", filme.getDescricao());
        args.putString("url", filme.getUrl());
        df.setArguments(args);

        return df;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
        }

        View view = inflater.inflate(R.layout.detalhe_layout, container, false);
        ButterKnife.bind(this, view);

        getDialog().setCanceledOnTouchOutside(false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String nome = getArguments().getString("nome");
        String descricao = getArguments().getString("descricao");
        String urlImagem = getArguments().getString("url");

        tv_nome.setText(nome);
        tv_descricao.setText(descricao);

        Glide.with(getActivity().getBaseContext())
                .load(urlImagem)
                .override(150, 250)
                .centerCrop()
            .listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    iw_capa.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                    return false;
                }
        }).into(iw_capa);
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogWindowWidth(DIALOG_WINDOW_WIDTH);
    }

    private void setDialogWindowWidth(double width) {
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display;
        if (window != null) {
            display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);
            int maxWidth = size.x;
            window.setLayout((int) (maxWidth* width), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
        }
    }

    @OnClick(R.id.dialog_btn)
    public void onButtonClicked() {
        if (getDialog().isShowing()) {
            getDialog().dismiss();
        }
    }
}