package com.yhonier.dao;

import com.yhonier.aplicacion.JPAUtil;
import com.yhonier.entidades.Producto;
import jakarta.persistence.EntityManager;

import javax.swing.*;
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

    public void close(){
        entityManager.close();
        JPAUtil.shutdown();
    }

}
