/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author user
 */

import InterfacesDAO.marcasDAO;
import clasesprincipales.Marcas;
import java.util.List;

public class marcasControl {

    private final marcasDAO marcasDAO;

  
    public marcasControl(marcasDAO marcasDAO) {
        this.marcasDAO = marcasDAO;
    }

    public Marcas insertar(Marcas nuevaMarca) {
        return marcasDAO.insertar(nuevaMarca);
    }

    public void actualizar(Marcas marca) {
        marcasDAO.actualizar(marca);
    }

    public void eliminar(int idMarca) {
        marcasDAO.eliminar(idMarca);
    }

    public Marcas seleccionar(int idMarca) {
        return marcasDAO.seleccionar(idMarca);
    }

    public List<Marcas> listar() {
        return marcasDAO.listar();
    }
}
