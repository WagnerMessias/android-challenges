package com.desafio.pitang.desafiopitang.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Wagner on 01/03/2018.
 */

public class Filme {

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String nome;

    @SerializedName("description")
    private String descricao;

    @SerializedName("url")
    private String url;

    public Filme(String id, String nome, String descricao, String url) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
