/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import clasesprincipales.Proveedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public interface proveedorDAO {
    Proveedor insertar (Proveedor nuevoproveedor);
    void actualizar (Proveedor proveedor);
    void eliminar (int IDproveedor);
    Proveedor seleccionar(int IDproveedor);
    List<Proveedor> listar();
    
}