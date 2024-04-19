package com.example.Usuarios;

import com.example.Aplicações.Restaurante;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.Aplicações.Avaliacao;
import com.example.Aplicações.Cardapio;
import com.example.Aplicações.ItemCard;

public class Aluno extends Usuario {

    private Cardapio cardapio; // Armazena o cardápio do restaurante
    private ArrayList<Object> avaliacoes; // Armazena as avaliações feitas pelo aluno
    private Restaurante restaurante; // Referência ao restaurante associado ao aluno

    public Aluno(String login, String senha, Restaurante restaurante) {
        super(login, senha);
        this.avaliacoes = new ArrayList<>(); // Inicializa a lista de avaliações como uma nova instância de ArrayList
        this.cardapio = new Cardapio(); // Inicializa o cardápio do restaurante como uma nova instância de Cardapio
        this.restaurante = restaurante;  // Armazena a referência do restaurante passada como parâmetro
        carregarCardapio(); // Este método é chamado para carregar o cardápio do restaurante
    }

    Scanner sc = new Scanner(System.in);

    public void cadastrarAluno(Restaurante restaurante) {
        System.out.println("\n-----------------------------------------------");
        System.out.println("             Cadastro Aluno:");
        System.out.println("-----------------------------------------------\n");
    
        System.out.print("Insira o login: ");
        String login = sc.nextLine();
        System.out.print("Insira a senha: ");
        String senha = sc.nextLine();
    
        Aluno aluno = new Aluno(login, senha, restaurante); 
        restaurante.usuarios.add(aluno);
        restaurante.salvarUsuarios();
    
        System.out.println("        Aluno cadastrado com sucesso!");
        System.out.println("-----------------------------------------------\n");
    
        restaurante.iniciar();
    }
    

    public void loginAluno() {
        System.out.println("\n--------------");
        System.out.println("Login Aluno:");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        for (Usuario u : restaurante.usuarios) {
            if (u instanceof Aluno && u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                System.out.println("Login bem sucedido!");
                System.out.println("-------------------");
                restaurante.usuarioLogado = u;
                exibirMenuAluno();
                return;
            }
        }
        System.out.println("Credenciais inválidas!");
        restaurante.iniciar();
    }

    public void exibirMenuAluno() {
        int opcao;
        do {
            System.out.println("     \nMenu Aluno:");
            System.out.println("   1. Exibir cardápio");
            System.out.println("   2. Avaliar cardápio");
            System.out.println("   3. Sair");
            System.out.print("     Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    cardapio.exibir();
                    break;
                case 2:
                    avaliarCardapio();
                    break;
                case 3:
                    System.out.println("Saindo.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }

    public void avaliarCardapio() {
        if (restaurante.usuarioLogado != null && restaurante.usuarioLogado instanceof Aluno) {
            System.out.print("Digite sua avaliação (de 1 a 5): ");
            int avaliacao = sc.nextInt();
            sc.nextLine(); 
    
            if (avaliacao >= 1 && avaliacao <= 5) {
                Aluno aluno = (Aluno) restaurante.usuarioLogado;
                Avaliacao novaAvaliacao = new Avaliacao(aluno, null, avaliacao, "");
                avaliacoes.add(novaAvaliacao);
                salvarAvaliacao(novaAvaliacao);
                System.out.println("Avaliação realizada com sucesso.");
            } else {
                System.out.println("Avaliação inválida. Por favor, digite uma avaliação entre 1 e 5.");
            }
        } else {
            System.out.println("Erro: Nenhum aluno logado.");
        }
    }
    
    private void salvarAvaliacao(Avaliacao avaliacao) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("avaliacoes.txt", true));
            writer.write(avaliacao.getAluno().getLogin() + "," + avaliacao.getClassificacao() + "," + avaliacao.getComentario());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar avaliação: " + e.getMessage());
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
}