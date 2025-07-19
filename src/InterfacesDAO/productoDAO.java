/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import clasesprincipales.Producto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @Antoni Montenegro Sanchez
 */
public interface productoDAO {
    Producto insertar (Producto nuevoproducto);
    boolean actualizar (Producto producto)throws SQLException ;
    boolean eliminar (int IDproducto) throws SQLException;
    Producto seleccionar(int IDproducto);
    List<Producto> listar();
    List<Producto> listarPorTipo(int idTipo);
    List<Producto> listarPorSubtipo(int idSubtipo);
    List<Producto> obtenerProductosConStockBajo();
    
   
}
