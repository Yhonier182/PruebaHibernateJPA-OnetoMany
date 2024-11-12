package com.yhonier.dao;

import com.yhonier.aplicacion.JPAUtil;
import com.yhonier.entidades.Mascota;
import com.yhonier.entidades.Persona;
import com.yhonier.entidades.PersonasProductos;
import com.yhonier.entidades.Producto;
import jakarta.persistence.EntityManager;
import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class PersonaDao {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();


    public String registrarPersona(Persona miPersona) {
        entityManager.getTransaction().begin();
        entityManager.persist(miPersona);
        entityManager.getTransaction().commit();
        return "Registro exitoso";
    }

    public Persona consultarPersona(Long idPersona) {
        Persona miPersona = entityManager.find(Persona.class, idPersona);

        if (miPersona != null) {
            return miPersona;
        } else {
            return null;
        }
    }


    public List<Persona> consultarListaPersonas() {
        String jpql = "SELECT p FROM Persona p LEFT JOIN FETCH p.nacimiento";
        return entityManager.createQuery(jpql, Persona.class).getResultList();
    } // LET JOIN FETCH podrás realizar una consulta específica que traiga tanto Persona
    // como Nacimiento una sola vez, y solo cuando realmente necesitas ambos datos.


    public String actualizarPersona(Persona miPersona) {
        entityManager.getTransaction().begin();
        entityManager.merge(miPersona);
        entityManager.getTransaction().commit();
        return "Actualizacion exitosa";
    }

    public String eliminarPersona(Long idPersona) {
        try {
            entityManager.getTransaction().begin();
            Persona miPersona = entityManager.find(Persona.class, idPersona);

            //si tiene relacion con una mascota eliminarla para que haga el efecto cascada
            for (Mascota mascota : miPersona.getListaMascotas()) {
                entityManager.remove(mascota);
            }
            entityManager.remove(miPersona);
            entityManager.getTransaction().commit();

            return "Eliminación exitosa";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar la persona. "
                    + "Verifique que no tenga registros pendientes.");

            return "";
        }
    }


    public List<PersonasProductos> obtenerProductosPorPersona(Long personaId) {
        String jpql = "SELECT pp FROM PersonasProductos pp WHERE pp.personaId = :personaId";
        List<PersonasProductos> listaProductosPersona = entityManager.createQuery(jpql, PersonasProductos.class)
                .setParameter("personaId", personaId)
                .getResultList();
        return listaProductosPersona;
    }




    public void close() {
        entityManager.close();
        JPAUtil.shutdown();
    }
}
