package com.example.Aplicações;
public class RestauranteFacade {

    private Restaurante restaurante;

    public RestauranteFacade() {
        this.restaurante = new Restaurante();
    }

    public void iniciar() {
        restaurante.iniciar();
    }
}
