package com.example.Aplicações;

import com.example.Usuarios.Aluno;

public class Avaliacao {

    private Aluno aluno;
    private ItemCard itemCard;
    private int classificacao;
    private String comentario;

    public Avaliacao(Aluno aluno, ItemCard itemCard, int classificacao, String comentario) {
        this.aluno = aluno;
        this.itemCard = itemCard;
        this.classificacao = classificacao;
        this.comentario = comentario;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public String getComentario() {
        return comentario;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public ItemCard getItemCard() {
        return itemCard;
    }
}