/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import clasesprincipales.*;
import InterfacesDAO.productoDAO;
import java.sql.SQLException;


/**
 *
 * @author user
 */
import java.util.List;

public class productoControl {

    private final productoDAO productoDAO;

    
    public productoControl(productoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    
    public Producto insertar(Producto nuevoProducto) {
        return productoDAO.insertar(nuevoProducto);
    }

    
    public boolean actualizar(Producto producto) throws SQLException {
    return productoDAO.actualizar(producto);  
    }

    public boolean eliminar(int idProducto) throws SQLException {
        return productoDAO.eliminar(idProducto);
    }

    public Producto seleccionar(int idProducto) {
        return productoDAO.seleccionar(idProducto);
    }

    
    public List<Producto> listar() {
        return productoDAO.listar();
    }
    
    public List<Producto> listarPorTipo(int idTipo) {
        return productoDAO.listarPorTipo(idTipo);
    }
    
    public List<Producto> listarPorSubtipo(int idSubtipo) {
       return productoDAO.listarPorSubtipo(idSubtipo);
    }
}

    

