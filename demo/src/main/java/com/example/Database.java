package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
    public static void main( String[] args ){
     try {
                  Connection conexcao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RUPROJECT",
                                 "postgres", "root");
                     if (conexcao != null) {
                         System.out.println("Banco de dados conectado com sucesso!");
                     } else {
                         System.out.println("Conexão falhou!");
                     }
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
}
