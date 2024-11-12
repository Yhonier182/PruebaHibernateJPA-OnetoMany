package com.yhonier.clases;

import com.yhonier.dao.PersonaDao;
import com.yhonier.entidades.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class GestionPersonas {

    PersonaDao mipersonaDao = new PersonaDao();

    public GestionPersonas() {
        String menu  = "MENU DE OPCIONES - GESTION DE PERSONAS\n\n ";
        menu += "1. Registrar Persona\n";
        menu += "2. Consultar Persona\n";
        menu += "3. Consultar lista de Personas\n";
        menu += "4. Consultar Productos por persona\n";
        menu += "5. Actualizar Persona\n";
        menu += "6. Eliminar Persona\n";
        menu += "7. Salir\n";

        int opc = 0;
        while (opc != 7) {
            opc = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opc) {
                case 1: registrar(); break;
                case 2: consultar(); break;
                case 3: consultarLista(); break;
                case 4: consultarProductosPersona(); break;
                case 5: actualizarNombre(); break;
                case 6: eliminar(); break;
                case 7: mipersonaDao.close(); break;
            }
        }
    }





    private void registrar() {
        Persona miPersona = new Persona();
        miPersona.setIdPersona(Long.parseLong(JOptionPane.showInputDialog("Ingrese el documento de la persona")));
        miPersona.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la Persona"));
        miPersona.setTelefono(JOptionPane.showInputDialog("Ingrese el telefono de la Persona"));
        miPersona.setProfesion(JOptionPane.showInputDialog("Ingrese la profesion de la Persona"));
        miPersona.setTipo(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tipo")));
        miPersona.setNacimiento(obtenerDatosNacimiento());

        int opc = 0;
        boolean registrarMascota = true;
        while (registrarMascota) {
            opc = Integer.parseInt(JOptionPane.showInputDialog("Desea Registrar una Mascota? \n1. SI\n2. No"));
            if (opc == 1) {
                miPersona.getListaMascotas().add(obtenerDatosMascota(miPersona));
                System.out.println(mipersonaDao.registrarPersona(miPersona));
                System.out.println(miPersona);
                System.out.println();
            } else if (opc == 2) {
                registrarMascota = false;
            } else {
                JOptionPane.showMessageDialog(null, "Opción no válida. Ingrese 1 para SI o 2 para NO.");
            }
        }
    }




    private Nacimiento obtenerDatosNacimiento() {
        int dia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el dia de  nacimiento"));
        int mes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el mes de nacimiento"));
        int ano = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de nacimiento"));

        Nacimiento datosNacimiento = new Nacimiento();

        datosNacimiento.setIdNacimiento(null);
        datosNacimiento.setFechaNacimiento(LocalDate.of(ano,mes,dia));
        datosNacimiento.setCiudadNacimiento(JOptionPane.showInputDialog("Ingrese el ciudad de nacimiento"));
        datosNacimiento.setDepartamentoNacimiento(JOptionPane.showInputDialog("Ingrese el departamento de nacimiento"));
        datosNacimiento.setPaisNacimiento(JOptionPane.showInputDialog("Ingrese el pais de nacimiento"));

        return datosNacimiento;
    }


    private Mascota obtenerDatosMascota(Persona miPersona){
        Mascota miMascota = new Mascota();
        miMascota.setIdMascota(null);
        miMascota.setNombre(JOptionPane.showInputDialog("Ingrese nombre de la mascota"));
        miMascota.setRaza(JOptionPane.showInputDialog("Ingrese raza de la mascota"));
        miMascota.setColorMascota(JOptionPane.showInputDialog("Ingrese color de la mascota"));
        miMascota.setSexo(JOptionPane.showInputDialog("Ingrese sexo de la mascota"));
        miMascota.setDuenio(miPersona);
        return miMascota;
    }

    public void consultar() {
        Long idPersona = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona"));

        Persona miPersona = mipersonaDao.consultarPersona(idPersona);
        if (miPersona != null){
            System.out.println(miPersona);
            System.out.println();
        }else {
            System.out.println("No existe Persona");
        }
        System.out.println();
    }

    public void consultarLista() {
        System.out.println("Lista de personas");
        List<Persona> listapersonas = mipersonaDao.consultarListaPersonas();
        for (Persona persona : listapersonas) {
            System.out.println(persona);
        }
    }

    private void actualizarNombre() {
        Long idPersona = Long.parseLong(JOptionPane.
                showInputDialog("Ingrese el id de la Persona para"
                        + " actualizar su nombre"));
        Persona miPersona = mipersonaDao.consultarPersona(idPersona);
        if (miPersona != null) {
            System.out.println(miPersona);
            System.out.println();
            miPersona.setNombre(JOptionPane.
                    showInputDialog("Ingrese el nombre de la Persona"));
            //Ponemos la opción si se quiere agregar una Mascota
            int opc=0;
            do {
                opc=Integer.parseInt(JOptionPane.showInputDialog("Desea Registrar una Mascota?\n1. SI\n2.No"));
                if (opc==1) {
                    miPersona.getListaMascotas().add(obtenerDatosMascota(miPersona));//Agregamos la Mascota a la lista
                }
            } while (opc!=2);
            System.out.println(mipersonaDao.actualizarPersona(miPersona));
            System.out.println();
        } else {
            System.out.println();
            System.out.println("No se encontró la Persona");
        }
        System.out.println();
    }


    private  void eliminar (){
        Long idPersona =Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona"));
        Persona mipersona = mipersonaDao.consultarPersona(idPersona);

        if (mipersona != null){
            System.out.println(mipersona);
            System.out.println();

            System.out.println(mipersonaDao.eliminarPersona(mipersona.getIdPersona()));
            System.out.println();
        }
    }

    private void consultarProductosPersona() {
       Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona a consultar"));
       List<PersonasProductos> compras= mipersonaDao.obtenerProductosPorPersona(id);

        if (compras.isEmpty()){
            System.out.println("No se encontraron compras para la persona con el ID: "+ id);
        }else {
            for (PersonasProductos compra : compras) {
                System.out.println(compra);
            }
        }
    }



}
