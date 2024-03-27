package com.example;

public class ItemCard {
    private String dia;
    private String descricao;
    private double preco;

    public ItemCard(String dia, String descricao, double preco) {
        this.dia = dia;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getDia() {
        return dia;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }
}
