/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import clasesprincipales.Devolucion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public interface devolucionDAO {
    Devolucion insertar (Devolucion nuevadevolucion);
    void actualizar (Devolucion devolucion);
    void eliminar (int IDdevolucion);
    Devolucion seleccionar(int IDdevolucion);
    List<Devolucion> listar();
    
}
