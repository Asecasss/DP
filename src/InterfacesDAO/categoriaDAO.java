/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import clasesprincipales.Categoria;
import java.util.List;

/**
 *
 * @author user
 */
public interface categoriaDAO {
    Categoria insertar(Categoria nuevaCategoria, Integer idPadre);
    void actualizar(Categoria categoria);
    void eliminar(int idCategoria);
    Categoria seleccionar(int idCategoria); 
    List<Categoria> listar();
}

