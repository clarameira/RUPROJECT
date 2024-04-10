package com.example.Aplicações;

import java.util.Scanner;

import com.example.Usuarios.Admin;
import com.example.Usuarios.Aluno;

public class Restaurante {

    private Aluno aluno;
    private Admin admin;

    Scanner sc = new Scanner(System.in);
    
    public Restaurante() {
        this.aluno = new Aluno(null, null, null, null, admin, null);
        this.admin = new Admin(null, null, null, null, admin, null);
    }

    public void iniciar() {

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
                    admin.cadastrarAdmin();
                    break;
                case 4:
                    aluno.cadastrarAluno();
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
    
}