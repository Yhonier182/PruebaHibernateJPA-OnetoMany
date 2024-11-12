package com.yhonier.controlador;

import com.yhonier.dao.ProductoDao;
import com.yhonier.entidades.Producto;
import com.yhonier.entidades.Persona;
import java.time.LocalDate;
import java.util.List;

public class controladorProductos  {
    private final ProductoDao productoDao = new ProductoDao();

    // Método para registrar un producto
    public String registrarProducto(Producto producto) {
        return productoDao.registrarProducto(producto);
    }

    // Método para consultar un producto por ID
    public Producto consultarProducto(int idProducto) {
        return productoDao.consultarProducto(idProducto);
    }

    // Método para consultar la lista de productos
    public List<Producto> consultarListaProductos() {
        return productoDao.consultarListaProductos();
    }

    // Método para actualizar un producto
    public String actualizarProducto(Producto producto) {
        return productoDao.actualizarProducto(producto);
    }

    // Método para eliminar un producto
    public String eliminarProducto(int idProducto) {
        Producto producto = productoDao.consultarProducto(idProducto);
        if (producto != null) {
            return productoDao.eliminarProducto(idProducto);
        }
        return "Producto no encontrado.";
    }

    // Método para registrar una compra
    public String registrarCompra(Long personaId, Long productoId, int cantidad, LocalDate fechaCompra) {
        return productoDao.registrarCompra(personaId, productoId, cantidad, fechaCompra);
    }

    // Método para obtener las personas que compraron un producto
    public List<Persona> obtenerPersonasPorProducto(Long productoId) {
        return productoDao.obtenerPersonasPorProducto(productoId);
    }

    // Cerrar la conexión de la base de datos
    public void cerrar() {
        productoDao.close();
    }
}
