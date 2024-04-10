package com.example.Usuarios;

import java.util.List;
import java.util.Scanner;

import com.example.Aplicações.Avaliacao;
import com.example.Aplicações.Restaurante;

    public class Aluno extends Usuario {

        Scanner sc = new Scanner(System.in);

        private List<Avaliacao> avaliacoes;

    public Aluno(String login, String senha, List<Avaliacao> avaliacoes,
            List<Usuario> usuarios, Usuario usuarioLogado, Restaurante restaurante) {
        super(login, senha, usuarios, usuarioLogado, restaurante);
        this.avaliacoes = avaliacoes;
    }

    public void cadastrarAluno() {
        System.out.println("\n-----------------------------------------------");
        System.out.println("Cadastro de Aluno:");
        System.out.println("-----------------------------------------------\n");

        System.out.print("Insira o login: ");
        String login = sc.nextLine();
        System.out.print("Insira a senha: ");
        String senha = sc.nextLine();

        Aluno aluno = new Aluno(login, senha, avaliacoes, usuarios, usuarioLogado, restaurante);
        usuarios.add(aluno);
        
        System.out.println("Aluno cadastrado com sucesso!");
        System.out.println("-----------------------------------------------\n");

        restaurante.iniciar();
    }

    public void loginAluno() {
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

    public void exibirMenuAluno() {
        int opcao;
        do {
            System.out.println("\nMenu Aluno:");
            System.out.println("1. Exibir cardápio");
            System.out.println("2. Avaliar cardápio");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
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
        if (usuarioLogado != null && usuarioLogado instanceof Aluno) {
            System.out.print("Digite sua avaliação (de 1 a 5): ");
            int avaliacao = sc.nextInt();
            sc.nextLine(); 
    
            if (avaliacao >= 1 && avaliacao <= 5) {
                Aluno aluno = (Aluno) usuarioLogado;
                Avaliacao novaAvaliacao = new Avaliacao(aluno, null, avaliacao, "");
                avaliacoes.add(novaAvaliacao);
                System.out.println("Avaliação realizada com sucesso.");
            } else {
                System.out.println("Avaliação inválida. Por favor, digite uma avaliação entre 1 e 5.");
            }
        } else {
            System.out.println("Erro: Nenhum aluno logado.");
        }
    }

}
