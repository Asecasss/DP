/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.stockbajoDAO;
import conexionsql.ConexionSingleton;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.CallableStatement;

/**
 *
 * @author user
 */
public class stockbajoAcces implements stockbajoDAO {

    @Override
public ArrayList<clasesprincipales.stockbajo> list() {
    String consulta = "{CALL listar_stockbajo()}"; 
    ArrayList<clasesprincipales.stockbajo> listaStockBajo = new ArrayList<>();

    try {
        
        CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            clasesprincipales.stockbajo stockBajo = new clasesprincipales.stockbajo();
            stockBajo.setIDstockbajo(rs.getInt("ID Stock Bajo"));
            stockBajo.setIDproducto(rs.getInt("ID Producto"));
            stockBajo.setNombreproducto(rs.getString("Nombre del Producto"));
            stockBajo.setStock(rs.getInt("Stock Actual"));
            stockBajo.setFechaCreacion(rs.getDate("Fecha de Creación"));

            listaStockBajo.add(stockBajo);
        }

        System.out.println("Se han listado " + listaStockBajo.size() + " productos con stock bajo.");

    } catch (SQLException e) {
        System.err.println("Error al listar los productos con stock bajo: " + e.getMessage());
    }

    return listaStockBajo;
    }
    
}
