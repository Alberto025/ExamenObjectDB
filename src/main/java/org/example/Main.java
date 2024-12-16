package org.example;

import org.example.service.Service;

public class Main {

    public static void main(String[] args) {
        Service ds = new Service();
        ds.registrarUsuario("pepe@gmail.com", "Pepe Garcia");
        ds.registrarUsuario("jorge@gmail.com", "Jorge Lopez");
        ds.añadirComentario("pepe@gmail.com", "Peli buena", 10);
        ds.añadirComentario("pepe@gmail.com", "Decente", 5);
        ds.añadirComentario("jorge@gmail.com", "Lo mejor", 10);
        ds.comentariosusuarios("pepe@gmail.com");
        ds.comentariosvaloracionesaltas();
        ds.cerrarConexion();
    }
}
