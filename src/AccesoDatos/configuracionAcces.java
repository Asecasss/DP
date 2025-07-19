/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.configuracionDAO;
import clasesprincipales.Configuracion;
import java.sql.SQLException;
import java.sql.CallableStatement;
import conexionsql.*;

/**
 *
 * @author user
 */
public class configuracionAcces implements configuracionDAO {

@Override
public void actualizar(Configuracion configuracion) {
    String consulta = "{CALL actualizar_configuracion(?, ?, ?, ?)}"; 

    try {
        
        CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta);
        stmt.setString(1, configuracion.getNombreEmpresa());
        stmt.setInt(2, configuracion.getRuc());
        stmt.setString(3, configuracion.getTelefono());

        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Configuración actualizada correctamente.");
        } else {
            System.err.println("No se pudo actualizar la configuración.");
        }

    } catch (SQLException e) {
    
        if ("45000".equals(e.getSQLState())) {
            System.out.println("Error del procedimiento almacenado: " + e.getMessage());
        } else {
            System.out.println("Error al actualizar la configuración: " + e.getMessage());
        }
    }
  } 
}
