package com.example.Usuarios;

import java.util.List;
import com.example.Aplicações.Cardapio;
import com.example.Aplicações.Restaurante;

public class Usuario {

    private String login;
    private String senha;
    private int tipoUsuario; 
    protected static Cardapio cardapio;
    protected List<Usuario> usuarios;
    protected Usuario usuarioLogado;
    public Restaurante restaurante;

    public Usuario(String login, String senha, List<Usuario> usuarios2, Usuario usuarioLogado2, Restaurante restaurante2) {
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = 0; 
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setIdentificacao(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
