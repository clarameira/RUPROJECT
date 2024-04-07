package com.example;

public class RestauranteFacade {
    
        private Restaurante restaurante;
    
        public RestauranteFacade() {
            this.restaurante = new Restaurante();
        }
    
        public void iniciar() {
            restaurante.iniciar();
        }
    
        public void cadastrarAdmin(String login, String senha) {
            restaurante.cadastrarAdmin();
        }
    
        public void cadastrarAluno(String login, String senha) {
            restaurante.cadastrarAluno();
        }
    
        public void loginAdmin(String login, String senha) {
            restaurante.loginAdmin();
        }
    
        public void loginAluno(String login, String senha) {
            restaurante.loginAluno();
        }
    
        public void exibirMenuAdmin() {
            restaurante.exibirMenuAdmin();
        }
    
        public void exibirMenuAluno() {
            restaurante.exibirMenuAluno();
        }
    
        public void avaliarCardapio() {
            restaurante.avaliarCardapio();
        }
}

