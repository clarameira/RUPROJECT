package com.example.Aplicações;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.Usuarios.Admin;
import com.example.Usuarios.Aluno;
import com.example.Usuarios.Usuario;

public class Restaurante {

    private Scanner sc;
    public List<Usuario> usuarios;
    public Usuario usuarioLogado;
    public Admin admin;
    public Aluno aluno;

    public Restaurante() {
        this.sc = new Scanner(System.in);
        this.usuarios = new ArrayList<>();
        this.aluno = new Aluno(null, null, this);
        this.admin = new Admin(null, null, this);
    }

    public void iniciar() {
        carregarUsuarios(); 

        int opcao;
        do {
            System.out.println("\n-----------------------------------------------");
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
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    admin.loginAdmin();
                    break;
                case 2:
                    aluno.loginAluno();
                    break;
                case 3:
                    admin.cadastrarAdmin(this);
                    break;
                case 4:
                aluno.cadastrarAluno(this);
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


    private void carregarUsuarios() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String login = parts[0];
                    String senha = parts[1];
                    if (parts.length == 3) {
                        if (parts[2].equals("admin")) {
                            usuarios.add(new Admin(login, senha, this)); 
                        } else if (parts[2].equals("aluno")) {
                            usuarios.add(new Aluno(login, senha, this)); 
                        }
                    } else {
                        usuarios.add(new Usuario(login, senha)); 
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }
    
    public void salvarUsuarios() {
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