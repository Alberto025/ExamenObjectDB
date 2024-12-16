package org.example.service;

import org.example.models.Comentario;
import org.example.models.Usuario;

import javax.persistence.*;
import java.util.List;

public class Service {

    private EntityManagerFactory emf;
    private EntityManager em;

    public Service() {
        this.emf = Persistence.createEntityManagerFactory("objectdb:db/usuarios.odb");
        this.em = emf.createEntityManager();
    }

    public void registrarUsuario(String correo, String nombre) {
        em.getTransaction().begin();
        Usuario usuario = new Usuario(correo, nombre);
        em.persist(usuario);
        em.getTransaction().commit();
        System.out.println("Usuario registrado: " + nombre);
    }
    public void comentariosusuarios(String correo) {
        TypedQuery<Comentario> query = em.createQuery(
                "SELECT c FROM Comentario c WHERE c.usuario.correo = :correo", Comentario.class);
        query.setParameter("correo", correo);
        List<Comentario> comentarios = query.getResultList();

        System.out.println("Comentarios del usuario con este correo: " + correo + ":");
        for (Comentario c : comentarios) {
            System.out.println("- " + c.getContenido() + " (Valoración: " + c.getValoracion() + ")");
        }
    }
    public void añadirComentario(String correo, String contenido, int valoracion) {
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, correo);
        if (usuario != null) {
            Comentario comentario = new Comentario(contenido, valoracion);
            usuario.anadirComentario(comentario);
            em.persist(comentario);
            em.getTransaction().commit();
            System.out.println("Comentario añadido al usuario: " + correo);
        } else {
            System.out.println("No se ha encontrado ningun usuario.");
            em.getTransaction().rollback();
        }
    }

    // 4. Listar los usuarios que han realizado comentarios con la valoración máxima
    public void comentariosvaloracionesaltas() {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT DISTINCT c.usuario FROM Comentario c WHERE c.valoracion = 10", Usuario.class);
        List<Usuario> usuarios = query.getResultList();

        System.out.println("Usuarios con comentarios de valoración de 10:");
        for (Usuario u : usuarios) {
            System.out.println("- " + u.getCorreo());
        }
    }

    // Cerrar EntityManager
    public void cerrarConexion() {
        em.close();
        emf.close();
    }
}
