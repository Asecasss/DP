/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

/**
 *
 * @author user
 */
import clasesprincipales.Marcas;
import java.util.ArrayList;
import java.util.List;

public interface marcasDAO {
    Marcas insertar(Marcas nuevaMarca);
    void actualizar(Marcas marca);
    void eliminar(int idMarca);
    Marcas seleccionar(int idMarca);
    List<Marcas> listar();
}

    

