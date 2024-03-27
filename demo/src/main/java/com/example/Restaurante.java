package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Restaurante {

    private Cardapio cardapio;
    private List<Avaliacao> avaliacoes;
    private Scanner sc;

    public Restaurante() {
        this.cardapio = new Cardapio();
        this.avaliacoes = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.print("Insira seu nome: ");
        String nomeAluno = sc.nextLine();
        System.out.print("Insira sua matrícula: ");
        String idAluno = sc.nextLine();

        int opcao;
        do {
            System.out.println("1. Exibir cardápio");
            System.out.println("2. Adicionar avaliação");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consumindo a quebra de linha

            switch (opcao) {
                case 1:
                    exibirCardapio();
                    break;
                case 2:
                    solicitarAvaliacao(nomeAluno, idAluno);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }

    public void exibirCardapio() {
        cardapio.exibir();
    }

    public void solicitarAvaliacao(String nomeAluno, String idAluno) {
        System.out.print("Insira um dia da semana para avaliar: ");
        String nomeItem = sc.nextLine();
        System.out.print("Insira a descrição da avaliação: ");
        String comentario = sc.nextLine();
        System.out.print("Digite uma classificação (de 1 a 5): ");
        int classificacao = sc.nextInt();
        sc.nextLine(); // Consumindo a quebra de linha

        Aluno aluno = new Aluno(nomeAluno, idAluno);
        ItemCard item = cardapio.buscarItem(nomeItem);

        if (item != null) {
            Avaliacao avaliacao = new Avaliacao(aluno, item, classificacao, comentario);
            avaliacoes.add(avaliacao);
            System.out.println("Avaliação adicionada com sucesso!");
        } else {
            System.out.println("Item do cardápio não encontrado.");
        }
    }
}
