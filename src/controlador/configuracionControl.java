/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import AccesoDatos.configuracionAcces;
import InterfacesDAO.configuracionDAO;
import clasesprincipales.Configuracion;

/**
 *
 * @author user
 */
public class configuracionControl {

    private final configuracionDAO configuracionDAO;

 
    public configuracionControl(configuracionDAO configuracionDAO) {
        this.configuracionDAO = configuracionDAO;
    }

    public void actualizarConfiguracion(Configuracion configuracion) {
        if (configuracion == null) {
            throw new IllegalArgumentException("El objeto Configuracion no puede ser null.");
        }

        configuracionDAO.actualizar(configuracion);
    }
}

    

