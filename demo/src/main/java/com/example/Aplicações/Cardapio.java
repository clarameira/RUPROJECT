package com.example.Aplicações;

import java.util.ArrayList;
import java.util.List;

// Classe que representa um cardápio semanal
public class Cardapio {
    
    // Lista de itens do cardápio
    public List<ItemCard> itens;

    // Construtor padrão que inicializa a lista de itens
    public Cardapio() {
        this.itens = new ArrayList<>();
    }

    // Método para adicionar um item ao cardápio
    public void adicionarItem(ItemCard item) {
        itens.add(item);
    }

    // Método para remover um item do cardápio
    public void removerItem(ItemCard item) {
        itens.remove(item);
    }

    // Método para exibir o cardápio na tela
    public void exibir() {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("CARDÁPIO SEMANAL:");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (ItemCard item : itens) {
            System.out.println("- " + item.getDia() + ": " + item.getDescricao() + " - R$" + item.getPreco());
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    // Método para buscar um item pelo dia da semana
    public ItemCard buscarItem(String nome) {
        for (ItemCard item : itens) {
            if (item.getDia().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }
}
