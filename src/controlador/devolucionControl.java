/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import AccesoDatos.devolucionAcces;
import InterfacesDAO.devolucionDAO;
import clasesprincipales.Devolucion;
import java.util.ArrayList;

/**
 *
 * @author user
 */
import java.util.ArrayList;
import java.util.List;

public class devolucionControl {

    private final devolucionDAO devolucionDAO;

    public devolucionControl(devolucionDAO devolucionDAO) {
        this.devolucionDAO = devolucionDAO;
    }

    public Devolucion insertar(Devolucion nuevaDevolucion) {
        return devolucionDAO.insertar(nuevaDevolucion);
    }

    public void actualizar(Devolucion devolucion) {
        devolucionDAO.actualizar(devolucion);
    }

    public void eliminar(int idDevolucion) {
        devolucionDAO.eliminar(idDevolucion);
    }

    public Devolucion seleccionar(int idDevolucion) {
        return devolucionDAO.seleccionar(idDevolucion);
    }

    public List<Devolucion> listar() {
        return devolucionDAO.listar();
    }
}

    

