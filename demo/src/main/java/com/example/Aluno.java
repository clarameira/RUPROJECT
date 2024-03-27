package com.example;

public class Aluno {

    private String nome;
    private String id;

    public Aluno(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }
}
