/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.devolucionDAO;
import clasesprincipales.Devolucion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import conexionsql.*;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class devolucionAcces implements devolucionDAO{

@Override
public Devolucion insertar(Devolucion nuevadevolucion) {
    String consulta = "{CALL insertar_devoluciones(?, ?, ?, ?, ?)}"; 

    try {
        
        CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta);
        stmt.setInt(1, nuevadevolucion.getIDproducto());
        stmt.setInt(2, nuevadevolucion.getIDproveedor());
        stmt.setString(3, nuevadevolucion.getDescripcion());
        java.util.Date utilDate = nuevadevolucion.getFechadevolucion();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        stmt.setDate(4, sqlDate);
        stmt.setString(5, nuevadevolucion.getMotivo());
       
        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Devolución registrada correctamente: " + nuevadevolucion.getDescripcion());
        } else {
            System.err.println("No se pudo registrar la devolución.");
        }

    } catch (SQLException e) {
        if ("45000".equals(e.getSQLState())) {
            System.err.println("Error del procedimiento almacenado: " + e.getMessage());
        } else {
            System.err.println("Error al registrar la devolución: " + e.getMessage());
        }
    }

    return nuevadevolucion;
}
    @Override
public void actualizar(Devolucion devolucion) {
    String consulta = "{CALL actualizar_devolucion(?, ?, ?, ?, ?, ?)}"; 

    try {
       
        CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta);
        stmt.setInt(1, devolucion.getIDdevolucion());
        stmt.setInt(2, devolucion.getIDproducto());
        stmt.setInt(3, devolucion.getIDproveedor());
        stmt.setString(4, devolucion.getDescripcion());
        java.util.Date utilDate = devolucion.getFechadevolucion();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        stmt.setDate(5, sqlDate);

        stmt.setString(6, devolucion.getMotivo());
        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Devolución actualizada correctamente: " + devolucion.getDescripcion());
        } else {
            System.err.println("No se pudo actualizar la devolución.");
        }

    } catch (SQLException e) {
      
        if ("45000".equals(e.getSQLState())) {
            System.err.println("Error del procedimiento almacenado: " + e.getMessage());
        } else {
            System.err.println("Error al actualizar la devolución: " + e.getMessage());
        }
    }
}

    @Override
public void eliminar(int IDdevolucion) {
    String consulta = "{CALL eliminar_devolucion(?)}"; 

    try {
        
        CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta);
        stmt.setInt(1, IDdevolucion);
        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Devolución eliminada correctamente.");
        } else {
            System.err.println("No se pudo eliminar la devolución.");
        }

    } catch (SQLException e) {
        
        if ("45000".equals(e.getSQLState())) {
            System.err.println("Error del procedimiento almacenado: " + e.getMessage());
        } else {
            System.err.println("Error al eliminar la devolución: " + e.getMessage());
        }
    }
}

   @Override
public Devolucion seleccionar(int IDdevolucion) {
    String consulta = "SELECT IDdevolucion, IDproducto, IDproveedor, descripcion, fechadevolucion, motivo " +
                      "FROM devoluciones WHERE IDdevolucion = ?"; 

    Devolucion devolucion = null; 

    try {
      
        PreparedStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareStatement(consulta);
        
        
        stmt.setInt(1, IDdevolucion);

       
        ResultSet rs = stmt.executeQuery();

    
        if (rs.next()) {
            devolucion = new Devolucion();
            devolucion.setIDdevolucion(rs.getInt("IDdevolucion"));
            devolucion.setIDproducto(rs.getInt("IDproducto"));
            devolucion.setIDproveedor(rs.getInt("IDproveedor"));
            devolucion.setDescripcion(rs.getString("descripcion"));
            devolucion.setFechadevolucion(rs.getDate("fechadevolucion"));
            devolucion.setMotivo(rs.getString("motivo"));

            System.out.println("Devolución seleccionada: " + devolucion.getDescripcion());
        } else {
            System.err.println("No se encontró la devolución con ID: " + IDdevolucion);
        }

    } catch (SQLException e) {
        System.err.println("Error al seleccionar la devolución: " + e.getMessage());
    }

    return devolucion;
}

   @Override
public ArrayList<Devolucion> listar() {
    String consulta = "{CALL listar_devoluciones()}"; 
    ArrayList<Devolucion> listaDevoluciones = new ArrayList<>(); 

    try {
        
        CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta);

      
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Devolucion devolucion = new Devolucion();
            devolucion.setIDdevolucion(rs.getInt("IDdevolución"));
            devolucion.setIDproducto(rs.getInt("IDproducto"));
            devolucion.setDescripcion(rs.getString("descripción"));
            devolucion.setFechadevolucion(rs.getDate("fechadevolución"));
            devolucion.setMotivo(rs.getString("motivo"));

            listaDevoluciones.add(devolucion);
        }

        System.out.println("Se han listado " + listaDevoluciones.size() + " devoluciones.");

    } catch (SQLException e) {
        System.err.println("Error al listar las devoluciones: " + e.getMessage());
    }

    return listaDevoluciones;
    }
    
}
