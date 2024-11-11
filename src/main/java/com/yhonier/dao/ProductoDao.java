package com.yhonier.dao;

import com.yhonier.aplicacion.JPAUtil;
import com.yhonier.entidades.Persona;
import com.yhonier.entidades.PersonasProductos;
import com.yhonier.entidades.Producto;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


public class ProductoDao {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();


    public String registrarProducto(Producto producto) {
        String  resp="";
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(producto);
            entityManager.getTransaction().commit();
            resp = "El producto se ha registrado correctamente";
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"No se pudo registrar el producto"+
                    "Error","Error",JOptionPane.ERROR_MESSAGE);
        }
        return resp;
    }

    public Producto consultarProducto(int idProducto) {
        Producto miProducto = entityManager.find(Producto.class, idProducto);
        if (miProducto != null) {
            return miProducto;
        }else {
            return null;
        }
    }

    public List<Producto> consultarListaProductos() {
        List<Producto> listaProductos = entityManager.createQuery("select p from Producto p").getResultList();
        return listaProductos;
    }

    public String actualizarProducto(Producto miProducto) {
        String  resp="";
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(miProducto);
            entityManager.getTransaction().commit();
            resp = "El producto se ha actualizado correctamente";
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"No se pudo actualizar el producto",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        return resp;
    }

    public String eliminarProducto(int miProducto) {
        String  resp="";
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(miProducto);
            entityManager.getTransaction().commit();
            resp = "El producto se ha eliminado correctamente";
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"No se pudo eliminar el producto","Error",JOptionPane.ERROR_MESSAGE);
        }return  resp;
    }

    public String registrarCompra(Long personaId, Long productoId, int cantidad , LocalDate fechaCompra){
        String  resp="";
        try {
            entityManager.getTransaction().begin();

            Persona persona = entityManager.find(Persona.class, personaId);
            Producto producto = entityManager.find(Producto.class, productoId);

            if (persona == null || producto == null) {
                throw new Exception("persona o producto no encontrados.");
            }
            PersonasProductos compra = new PersonasProductos();
            compra.setPersonaId(personaId);
            compra.setProductoId(productoId);
            compra.setCantidad(cantidad);
            compra.setFechaCompra(java.sql.Date.valueOf(fechaCompra));

            //persistir la compra en la base de datos
            entityManager.persist(compra);

            entityManager.getTransaction().commit();
            resp = "Se realizo Compra del producto¡ ";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null,"No se puede registrar la compra del producto",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }

        return resp;
    }

    public List<Persona> obtenerPersonasPorProducto(Long productoId) {
        String jpql = "SELECT p FROM Persona p JOIN p.listaProductos prod WHERE prod.id = :productoId";
        List<Persona> listaPersonas = entityManager.createQuery(jpql, Persona.class)
                .setParameter("productoId", productoId)
                .getResultList();
        return listaPersonas;
    }


    public void close(){
        entityManager.close();
        JPAUtil.shutdown();
    }

}
