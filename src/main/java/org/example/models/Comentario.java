package org.example.models;

import javax.persistence.*;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;

    private int valoracion;

    @ManyToOne
    @JoinColumn(name = "usuario_correo")
    private Usuario usuario;

    // Constructor vacío
    public Comentario() {}

    // Constructor con parámetros
    public Comentario(String contenido, int valoracion) {
        this.contenido = contenido;
        this.valoracion = valoracion;
    }

    // Getters y Setters

    public String getContenido() {
        return contenido;
    }



    public int getValoracion() {
        return valoracion;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
