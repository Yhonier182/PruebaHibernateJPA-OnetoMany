package com.yhonier.aplicacion;

import com.yhonier.clases.GestionMascotas;
import com.yhonier.clases.GestionPersonas;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {

        String menu = "MENU DE OPCIONES \n\n";

        menu += "1. Gestionar Personas\n";
        menu += "2. Gestionar Mascotas\n";
        menu += "3. Salir\n";

        int opc = 0;

        while (opc != 3) {
            opc = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opc) {
                case 1:
                    new GestionPersonas();
                    break;
                case 2:
                    new GestionMascotas();
                    break;
                case 3:
                    break;
            }

        }
    }
}