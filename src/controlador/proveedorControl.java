/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import AccesoDatos.proveedorAcces;
import InterfacesDAO.proveedorDAO;
import clasesprincipales.Proveedor;
import conexionsql.ConexionSingleton;
import java.util.ArrayList;

/**
 *
 * @author user
 */
import java.util.ArrayList;
import java.util.List;

public class proveedorControl {

    private final proveedorDAO proveedorDAO;

    public proveedorControl(proveedorDAO proveedorDAO) {
        this.proveedorDAO = proveedorDAO;
    }

    public proveedorControl() {
        this.proveedorDAO = new proveedorAcces(
            ConexionSingleton.getInstancia().getConexion());  
    }
    public Proveedor insertar(Proveedor nuevoProveedor) {
        return proveedorDAO.insertar(nuevoProveedor);
    }

    public void actualizar(Proveedor proveedor) {
        proveedorDAO.actualizar(proveedor);
    }

    public void eliminar(int idProveedor) {
        proveedorDAO.eliminar(idProveedor);
    }

    public Proveedor seleccionar(int idProveedor) {
        return proveedorDAO.seleccionar(idProveedor);
    }

    public List<Proveedor> listar() {
        return proveedorDAO.listar();
    }
}
    

