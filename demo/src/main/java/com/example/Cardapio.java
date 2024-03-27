package com.example;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    private List<ItemCard> itens;

    public Cardapio() {
        this.itens = new ArrayList<>();
        this.itens.add(new ItemCard("Segunda-feira:", "Arroz, Frango, Batata e Feijão", 2.50));
        this.itens.add(new ItemCard("Terça-feira:", "Paçoca, macarrão, feijoada", 2.50));
    }

    public void adicionarItem(ItemCard item) {
        itens.add(item);
    }

    public void removerItem(ItemCard item) {
        itens.remove(item);
    }

    public void exibir() {
        System.out.println("Cardápio:");
        for (ItemCard item : itens) {
            System.out.println("- " + item.getDia() + ": " + item.getDescricao() + " - R$" + item.getPreco());
        }
    }

    public ItemCard buscarItem(String nome) {
        for (ItemCard item : itens) {
            if (item.getDia().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }
}
