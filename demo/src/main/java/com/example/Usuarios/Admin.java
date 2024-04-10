package com.example.Usuarios;

import java.util.List;
import java.util.Scanner;

import com.example.Aplicações.Cardapio;
import com.example.Aplicações.ItemCard;
import com.example.Aplicações.Restaurante;

public class Admin extends Usuario {

    Scanner sc = new Scanner(System.in);

    public Admin(String login, String senha, Cardapio cardapio,
            List<Usuario> usuarios, Usuario usuarioLogado, Restaurante restaurante) {
        super(login, senha, usuarios, usuarioLogado, restaurante);
    }

    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Override
    public String getSenha() {
        return super.getSenha();
    }

    public void cadastrarAdmin() {
        System.out.println("\n-----------------------------------------------");
        System.out.println("Cadastro de Administrador:");
        System.out.println("-----------------------------------------------\n");

        System.out.print("Insira o login: ");
        String login = sc.nextLine();
        System.out.print("Insira a senha: ");
        String senha = sc.nextLine();

        Admin admin = new Admin(login, senha, cardapio, usuarios, usuarioLogado, restaurante);
        usuarios.add(admin);

        System.out.println("Administrador cadastrado com sucesso!");
        System.out.println("-----------------------------------------------\n");

        restaurante.iniciar();
    }

    public void loginAdmin() {
        System.out.print("Login do administrador: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        for (Usuario u : usuarios) {
            if (u instanceof Admin && u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                System.out.println("Login bem-sucedido!");
                usuarioLogado = u;
                exibirMenuAdmin();
                return;
            }
        }

        System.out.println("Credenciais inválidas!");
    }

    public void exibirMenuAdmin() {
        int opcao;
        do {
            System.out.println("\nMenu Administrador:");
            System.out.println("1. Exibir cardápio");
            System.out.println("2. Adicionar item ao cardápio");
            System.out.println("3. Editar item do cardápio");
            System.out.println("4. Remover item do cardápio");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cardapio.exibir();
                    break;
                case 2:
                    adicionarItemCardapio();
                    break;
                case 3:
                    editarItemCardapio();
                    break;
                case 4:
                    removerItemCardapio();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    public void adicionarItemCardapio() {
        System.out.print("Insira o dia da semana: ");
        String dia = sc.nextLine();
        System.out.print("Insira a descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Insira o preço: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        ItemCard novoItem = new ItemCard(dia, descricao, preco);
        cardapio.adicionarItem(novoItem);
        System.out.println("Item adicionado ao cardápio com sucesso!");
    }

    private void editarItemCardapio() {
        System.out.print("Insira o dia do item que deseja editar: ");
        String dia = sc.nextLine();
        ItemCard item = cardapio.buscarItem(dia);

        if (item != null) {
            System.out.println("Item encontrado: " + item);
            System.out.print("Insira a nova descrição: ");
            String novaDescricao = sc.nextLine();
            System.out.print("Insira o novo preço: ");
            double novoPreco = sc.nextDouble();
            sc.nextLine();

            item.setDescricao(novaDescricao);
            item.setPreco(novoPreco);
            System.out.println("Item do cardápio editado com sucesso!");
        } else {
            System.out.println("Item não encontrado no cardápio.");
        }
    }

    private void removerItemCardapio() {
        System.out.print("Insira o dia do item que deseja remover: ");
        String dia = sc.nextLine();
        ItemCard item = cardapio.buscarItem(dia);

        if (item != null) {
            cardapio.removerItem(item);
            System.out.println("Item do cardápio removido com sucesso!");
        } else {
            System.out.println("Item não encontrado no cardápio.");
        }
    }

}