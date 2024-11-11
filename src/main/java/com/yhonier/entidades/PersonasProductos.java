package com.yhonier.entidades;


import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="personas-productos")
public class PersonasProductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "persona_id", nullable = false)
    private Long personaId;
    @Column(name = "producto_id", nullable = false)
    private Long productoId;
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    @Column(name = "fecha_compra", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;

    public PersonasProductos() {
    }


    public PersonasProductos(Long personaId, Long productoId, Integer cantidad, Date fechaCompra) {
        this.personaId = personaId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }


    public Long getId() {
        return id;
    }
    public Long getPersonaId() {
        return personaId;
    }
    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }
    public Long getProductoId() {
        return productoId;
    }
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Date getFechaCompra() {
        return fechaCompra;
    }
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    @Override
    public String toString() {
        return "PersonasProductos [id=" + id + ", personaId=" + personaId + ", productoId=" + productoId +
                ", cantidad=" + cantidad + ", fechaCompra=" + fechaCompra + "]";
    }
}
