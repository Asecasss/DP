/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.movimientosDAO;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import conexionsql.*;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class historialAcces implements movimientosDAO {

    @Override
public ArrayList<clasesprincipales.historialmovimientos> list() {
    String consulta = "{CALL listar_movimientos()}"; 
    ArrayList<clasesprincipales.historialmovimientos> listaMovimientos = new ArrayList<>();

    try {
        
        CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            clasesprincipales.historialmovimientos movimiento = new clasesprincipales.historialmovimientos();
            movimiento.setIDmovimiento(rs.getInt("IDmovimiento"));
            movimiento.setEntidad(rs.getString("entidad"));
            movimiento.setIDentidad(rs.getInt("IDentiendad"));
            movimiento.setOperacion(rs.getString("operacion"));
            movimiento.setFechaoperacion(rs.getDate("fechaoperacion"));
            movimiento.setDetalleactualizacion(rs.getString("detalleactualizacion"));
            movimiento.setDetalleanterior(rs.getString("detalleanterior"));

            listaMovimientos.add(movimiento);
        }

        System.out.println("Se han listado " + listaMovimientos.size() + " movimientos.");

    } catch (SQLException e) {
        System.err.println("Error al listar los movimientos: " + e.getMessage());
    }

    return listaMovimientos;
    }
    
}
