/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import AccesoDatos.registrofacturaAcces;
import InterfacesDAO.registroDAO;
import clasesprincipales.registrofactura;
import java.util.ArrayList;

/**
 *
 * @author user
 */
import java.util.ArrayList;

public class registrofacturaControl {

    private final registroDAO registroFacturaDAO;

   
    public registrofacturaControl(registroDAO registroFacturaDAO) {
        this.registroFacturaDAO = registroFacturaDAO;
    }

    
    public registrofacturaControl() {
        this.registroFacturaDAO = new registrofacturaAcces();  
    }

    public registrofactura insertar(registrofactura nuevoRegistro) {
        return registroFacturaDAO.insertar(nuevoRegistro);
    }

    public void actualizar(registrofactura registro) {
        registroFacturaDAO.actualizar(registro);
    }

    public void eliminar(int idRegistro) {
        registroFacturaDAO.eliminar(idRegistro);
    }

    public registrofactura seleccionar(int idRegistro) {
        return registroFacturaDAO.seleccionar(idRegistro);
    }

    public ArrayList<registrofactura> listar() {
        return registroFacturaDAO.listar();
    }
}

