package com.yhonier.clases;

import com.yhonier.dao.ProductoDao;
import com.yhonier.entidades.Persona;
import com.yhonier.entidades.Producto;

import javax.swing.*;
import java.util.List;

public class GestionProductos {

    ProductoDao miProductoDao = new ProductoDao();

    public GestionProductos() {
        String menu = "MENU DE OPCIONES -GESTION PRODUCTO\n\n";
        menu += "1. Registrar Producto\n";
        menu += "2. Consultar Producto\n";
        menu += "3. Consultar Lista de Productos\n ";
        menu += "4. Consultar Personas por Producto\n ";
        menu += "4. Comprar productos\n";
        menu += "5. Actualizar Producto\n";
        menu += "6. Eliminar Producto\n";
        menu += "7. Salir\n";


        int opcion = 0;

        while (opcion != 8) {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    consultar();
                    break;
                case 3:
                    consultarLista();
                    break;
                case 4:
                      consultarPersonasPorProducto();
                      break;
                case 5:
                    comprarProductos();
                    break;
                case 6:
                    actualizar();
                    break;
                case 7:
                    eliminar();
                    break;
                case 8:
                    miProductoDao.close();
                    break;
            }
        }
    }


    public void registrar() {
        Producto miProducto = new Producto();
        miProducto.setIdProducto(null);
        miProducto.setNombreProducto(JOptionPane.showInputDialog("Ingrese el nombre del producto"));
        miProducto.setPrecioProducto(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto")));

        System.out.println(miProductoDao.registrarProducto(miProducto));
        System.out.println();
    }

    public void consultar() {
        int idProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id"));
        Producto miProducto = miProductoDao.consultarProducto(idProducto);

        if (miProducto != null) {
            System.out.println(miProducto);
            System.out.println();
        } else {
            System.out.println("No existe el producto");
        }
        System.out.println();
    }

    public void consultarLista() {
        System.out.println("Lista de Productos");
        List<Producto> listaProducto = miProductoDao.consultarListaProductos();

        for (Producto producto : listaProducto) {
            System.out.println(producto);
        }
    }

    public void actualizar() {
        int idProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del producto a modificar"));
        Producto miProducto = miProductoDao.consultarProducto(idProducto);

        if (miProducto != null) {
            System.out.println(miProducto);
            System.out.println();
            miProducto.setNombreProducto(JOptionPane.showInputDialog("Ingrese el nombre del producto"));
            miProducto.setPrecioProducto(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto")));
            System.out.println(miProductoDao.actualizarProducto(miProducto));
            System.out.println();
        } else {
            System.out.println();
            System.out.println("No existe el producto");
        }
        System.out.println();
    }


    public void eliminar() {
        int idProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del producto"));
        Producto miProducto = miProductoDao.consultarProducto(idProducto);
        if (miProducto != null) {
            System.out.println(miProducto);
            System.out.println();
            miProductoDao.eliminarProducto(idProducto);
            System.out.println();
        } else {
            System.out.println("No existe el producto");
        }
    }

    public void comprarProductos() {
        Long personaId;
        Long productoId;
        int opcion = 0;

        do {
            try {
                personaId = Long.parseLong(JOptionPane.showInputDialog("Ingrese el documento de la persona"));
                productoId = Long.parseLong(JOptionPane.showInputDialog("Ingrese el documento del producto"));

                String resultado = miProductoDao.registrarCompra(personaId, productoId);
                System.out.println(resultado);
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada invalida. por favor, ingrese valores" +
                        "numericos validos");
            } catch (Exception e) {
                System.out.println("Error al registrar la compra:  " + e.getMessage());
            }
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 1 si desea agregar producto"));
        }
        while (opcion == 1) ;
    }

    private void consultarPersonasPorProducto() {
        long productoId = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id del producto a consultar"));
        List<Persona> listaPersonas = miProductoDao.obtenerPersonasPorProducto(productoId);
        System.out.println("Lista de personas que tienen el producto con id " + productoId + ":");
        for (Persona persona : listaPersonas) {
            System.out.println(persona);
        }
    }


}

