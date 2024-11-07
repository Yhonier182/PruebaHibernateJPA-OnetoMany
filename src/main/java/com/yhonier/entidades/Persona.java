package com.yhonier.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name ="persona")
public class Persona implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_persona")
    private Long idPersona;

    @Column(name = "nombre_persona")
    private String nombre;

    @Column(name = "telefono_persona")
    private String telefono;

    @Column(name = "profesion_persona")
    private String profesion;

    @Column(name = "tipo_persona")
    private int tipo;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "nacimiento_id", referencedColumnName = "id_nacimiento")
    private Nacimiento nacimiento;



    @OneToMany (mappedBy = "duenio", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Mascota> listaMascotas;



    public Persona() {
        this.listaMascotas=new ArrayList<Mascota>();
    }
    public Persona(Long idPersona, String nombre, String telefono,
                   String profesion, int tipo, Nacimiento nacimiento) {
        super();
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.telefono = telefono;
        this.profesion = profesion;
        this.tipo = tipo;
        this.nacimiento = nacimiento;
        this.listaMascotas=new ArrayList<Mascota>();
    }


    public List<Mascota> getListaMascotas() {
        return listaMascotas;
    }
    public void setListaMascotas(List<Mascota> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    public Nacimiento getNacimiento() {
        return nacimiento;
    }


    public void setNacimiento(Nacimiento nacimiento) {
        this.nacimiento = nacimiento;
    }

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idpersona) {
        this.idPersona = idpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {

       String  mascotaInfo= " Sin Mascota";
       if (listaMascotas !=null && listaMascotas.size()>0) {
           mascotaInfo= listaMascotas.toString();
       }

        return " Persona {" +
                " idpersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", profesion='" + profesion + '\'' +
                ", tipo=" + tipo +
                ", nacimiento=" + nacimiento +
                ", ListaMascotas=" + mascotaInfo +
                '}';
    }
}

