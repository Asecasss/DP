/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import InterfacesDAO.categoriaDAO;
import clasesprincipales.Categoria;
import java.util.List;


public class categoriaControl {

    private final categoriaDAO categoriaDAO;   

    
    public categoriaControl(categoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public List<Categoria> listar() {
        return categoriaDAO.listar();
    }

    public Categoria insertar(Categoria nueva, Integer idPadre) {
        return categoriaDAO.insertar(nueva, idPadre);
    }

    public void actualizar(Categoria categoria) {
        categoriaDAO.actualizar(categoria);
    }

    public void eliminar(int idCategoria) {
        categoriaDAO.eliminar(idCategoria);
    }

    public Categoria seleccionar(int idCategoria) {
        return categoriaDAO.seleccionar(idCategoria);
    }
}


