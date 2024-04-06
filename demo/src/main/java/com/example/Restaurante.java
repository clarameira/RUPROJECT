package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Restaurante {

    private Cardapio cardapio;
    private List<Avaliacao> avaliacoes;
    private Scanner sc;
    private List<Usuario> usuarios;
    private Usuario usuarioLogado;

    public Restaurante() {
        this.cardapio = new Cardapio();
        this.avaliacoes = new ArrayList<>();
        this.sc = new Scanner(System.in);
        this.usuarios = new ArrayList<>();
        carregarCardapio(); 
    }

    public void iniciar() {
        carregarUsuarios(); // Carregar dados de usuários do arquivo

        int opcao;
        do {
            System.out.println("-----------------------------------------------");
            System.out.println("Bem-vindo ao Restaurante Universitário UFERSA!");
            System.out.println("CAMPUS PAU DOS FERROS");
            System.out.println("-----------------------------------------------\n");
            System.out.println("1. Fazer login como administrador");
            System.out.println("2. Fazer login como aluno");
            System.out.println("3. Criar uma conta como administrador");
            System.out.println("4. Criar uma conta como aluno");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    loginAdmin();
                    break;
                case 2:
                    loginAluno();
                    break;
                case 3:
                    cadastrarAdmin();
                    break;
                case 4:
                    cadastrarAluno();
                    break;
                case 5:
                    System.out.println("Saindo.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4 && opcao != 5);
    }

    private void cadastrarAdmin() {
        System.out.println("Cadastro de Administrador:");

        System.out.print("Insira o login: ");
        String login = sc.nextLine();
        System.out.print("Insira a senha: ");
        String senha = sc.nextLine();

        Admin admin = new Admin(login, senha);
        usuarios.add(admin);
        salvarUsuarios();

        System.out.println("Administrador cadastrado com sucesso!");
    }

    private void cadastrarAluno() {
        System.out.println("Cadastro de Aluno:");

        System.out.print("Insira o login: ");
        String login = sc.nextLine();
        System.out.print("Insira a senha: ");
        String senha = sc.nextLine();

        Aluno aluno = new Aluno(login, senha);
        usuarios.add(aluno);
        salvarUsuarios();

        System.out.println("Aluno cadastrado com sucesso!");
    }

    private void loginAdmin() {
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

    private void loginAluno() {
        System.out.print("Login do aluno: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        for (Usuario u : usuarios) {
            if (u instanceof Aluno && u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                System.out.println("Login bem-sucedido!");
                usuarioLogado = u;
                exibirMenuAluno();
                return;
            }
        }

        System.out.println("Credenciais inválidas!");
    }

    private void exibirMenuAdmin() {
        int opcao;
        do {
            System.out.println("\nMenu Administrador:");
            System.out.println("1. Exibir cardápio");
            System.out.println("2. Adicionar item ao cardápio");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cardapio.exibir();
                    break;
                case 2:
                    adicionarItemCardapio();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }

    private void exibirMenuAluno() {
        int opcao;
        do {
            System.out.println("\nMenu Aluno:");
            System.out.println("1. Exibir cardápio");
            System.out.println("2. Avaliar cardápio");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cardapio.exibir();
                    break;
                case 2:
                    avaliarCardapio();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }

    private void adicionarItemCardapio() {
        System.out.print("Insira o dia da semana: ");
        String dia = sc.nextLine();
        System.out.print("Insira a descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Insira o preço: ");
        double preco = sc.nextDouble();
        sc.nextLine(); // Consumir a quebra de linha
    
        ItemCard novoItem = new ItemCard(dia, descricao, preco);
        cardapio.adicionarItem(novoItem);
        salvarItemCardapio(novoItem);
        System.out.println("Item adicionado ao cardápio com sucesso!");
    }
    
    private void salvarItemCardapio(ItemCard item) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("itenscard.txt", true));
            writer.write(item.getDia() + "," + item.getDescricao() + "," + item.getPreco());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar item do cardápio: " + e.getMessage());
        }
    }
    
    private void carregarCardapio() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("itenscard.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String dia = parts[0];
                    String descricao = parts[1];
                    double preco = Double.parseDouble(parts[2]);
                    cardapio.adicionarItem(new ItemCard(dia, descricao, preco));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar cardápio: " + e.getMessage());
        }
    }

    private void avaliarCardapio() {
        if (usuarioLogado != null) {
            System.out.print("Digite sua avaliação (de 1 a 5): ");
            int avaliacao = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha
    
            if (avaliacao >= 1 && avaliacao <= 5) {
                avaliacoes.add(new Avaliacao((Aluno) usuarioLogado, null, avaliacao, ""));
                System.out.println("Avaliação realizada com sucesso.");
            } else {
                System.out.println("Avaliação inválida. Por favor, digite uma avaliação entre 1 e 5.");
            }
        } else {
            System.out.println("Erro: Nenhum usuário logado.");
        }
    }

    private void carregarUsuarios() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    usuarios.add(new Usuario(parts[0], parts[1]));
                } else if (parts.length == 3) {
                    if (parts[2].equals("admin")) {
                        usuarios.add(new Admin(parts[0], parts[1]));
                    } else if (parts[2].equals("aluno")) {
                        usuarios.add(new Aluno(parts[0], parts[1]));
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    private void salvarUsuarios() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"));
            for (Usuario u : usuarios) {
                if (u instanceof Admin) {
                    writer.write(u.getLogin() + "," + u.getSenha() + ",admin");
                } else if (u instanceof Aluno) {
                    writer.write(u.getLogin() + "," + u.getSenha() + ",aluno");
                } else {
                    writer.write(u.getLogin() + "," + u.getSenha());
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    
}