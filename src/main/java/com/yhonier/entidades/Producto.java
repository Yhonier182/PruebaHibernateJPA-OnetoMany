package com.yhonier.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "Productos")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre_producto", nullable = false, length = 45)
    private String nombreProducto;

    @Column(name = "precio_producto")
    private Double precioProducto;

    @ManyToMany(mappedBy = "listaProductos")
    private List<Persona> listaPersonas;




    public Producto() {

    }

    public Producto(Long idProducto, String nombreProducto, Double precioProducto) {
        super();
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public List<Persona> getListaPeronas() {
        return listaPersonas;
    }

    public void setListaPeronas(List<Persona> listaPeronas) {
        this.listaPersonas = listaPeronas;
    }

    @Override
    public String toString() {
        return "Producto [idProducto= " + idProducto + ","
                + " nombreProducto= " + nombreProducto + ","
                + " precioProducto= "+ precioProducto + "]";
    }

}


