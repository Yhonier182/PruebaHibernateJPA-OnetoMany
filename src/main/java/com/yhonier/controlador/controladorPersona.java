package com.yhonier.controlador;

import com.yhonier.dao.PersonaDao;
import com.yhonier.entidades.Persona;
import com.yhonier.entidades.PersonasProductos;
import java.util.List;

public class controladorPersona {

    private final PersonaDao personaDao = new PersonaDao();

    public String registrarPersona(Persona persona) {
        return personaDao.registrarPersona(persona);
    }

    public Persona consultarPersona(Long idPersona) {
        return personaDao.consultarPersona(idPersona);
    }

    public List<Persona> consultarListaPersonas() {
        return personaDao.consultarListaPersonas();
    }

    public String actualizarPersona(Persona persona) {
        return personaDao.actualizarPersona(persona);
    }

    public String eliminarPersona(Long idPersona) {
        Persona persona = personaDao.consultarPersona(idPersona);
        return personaDao.eliminarPersona(persona.getIdPersona());
    }

    public List<PersonasProductos> obtenerProductosPorPersona(Long personaId) {
        return personaDao.obtenerProductosPorPersona(personaId);
    }

    public void cerrar() {
        personaDao.close();
    }



}
