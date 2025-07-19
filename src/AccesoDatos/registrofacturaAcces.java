/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.registroDAO;
import clasesprincipales.registrofactura;
import conexionsql.ConexionSingleton;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class registrofacturaAcces implements registroDAO {

    @Override
    public registrofactura insertar(registrofactura nuevoregistro) {
        String consulta = "{CALL insertar_factura(?, ?, ?, ?, ?)}";

    try {
        
        CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta);
        
        stmt.setInt(1, nuevoregistro.getIDproveedor());
        stmt.setInt(2, nuevoregistro.getIDproducto());
        stmt.setString(3, nuevoregistro.getDescripcionAdic());
        
        java.util.Date utilDate = nuevoregistro.getFecha();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        stmt.setDate(4, sqlDate);
        stmt.setString(5, nuevoregistro.getEstadofactura());
        
        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Factura registrada correctamente con la descripción: " + nuevoregistro.getDescripcionAdic());
        } else {
            System.err.println("No se pudo registrar la factura.");
        }

    } catch (SQLException e) {
        System.err.println("Error al registrar la factura: " + e.getMessage());
    }

    return nuevoregistro;
}

    @Override
    public void actualizar(registrofactura registro) {
     String consulta = "{CALL actualizar_factura(?, ?, ?, ?, ?, ?)}"; 

    try {
     
        CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta);
        
       
        stmt.setInt(1, registro.getIDregistro()); 
        stmt.setInt(2, registro.getIDproveedor());
        stmt.setInt(3, registro.getIDproducto()); 
        stmt.setString(4, registro.getDescripcionAdic()); 

        
        java.util.Date utilDate = registro.getFecha();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        stmt.setDate(5, sqlDate); 

        stmt.setString(6, registro.getEstadofactura()); 
        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Factura actualizada correctamente. ID: " + registro.getIDregistro());
        } else {
            System.err.println("No se pudo actualizar la factura. Verifica si el ID existe.");
        }

    } catch (SQLException e) {
    
        if ("45000".equals(e.getSQLState())) {
            System.err.println("Error del procedimiento almacenado: " + e.getMessage());
        } else {
            System.err.println("Error al actualizar la factura: " + e.getMessage());
        }
    }
    
 }

    @Override
    public void eliminar(int IDregistro) {
       String consulta = "{CALL eliminar_factura(?)}"; 

    try {
        
        CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta);
        
        
        stmt.setInt(1, IDregistro); 

        
        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Factura eliminada correctamente. ID: " + IDregistro);
        } else {
            System.err.println("No se pudo eliminar la factura. Verifica si el ID existe.");
        }

    } catch (SQLException e) {
       
        if ("45000".equals(e.getSQLState())) {
            System.err.println("Error del procedimiento almacenado: " + e.getMessage());
        } else {
            System.err.println("Error al eliminar la factura: " + e.getMessage());
        }
    }

 }

    @Override
    public registrofactura seleccionar(int IDregistro) {
         String consulta = "SELECT IDregistro, IDproveedor, IDproducto, DescripcionAdicional, Fecha, estadofactura " +
                      "FROM registrofactura " +
                      "WHERE IDregistro = ?";

    registrofactura registro = null;

    try {
    
        PreparedStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareStatement(consulta);
       
        stmt.setInt(1, IDregistro);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            registro = new registrofactura();
            registro.setIDregistro(rs.getInt("IDfactura"));
            registro.setIDproveedor(rs.getInt("IDproveedor"));
            registro.setIDproducto(rs.getInt("IDproducto"));
            registro.setDescripcionAdic(rs.getString("DescripcionAdicional"));
            registro.setFecha(rs.getDate("Fecha"));
            registro.setEstadofactura(rs.getString("estadofactura"));
        } else {
            System.out.println("No se encontró un registro con el ID especificado: " + IDregistro);
        }
        
    } catch (SQLException e) {
        System.err.println("Error al seleccionar el registro de factura: " + e.getMessage());
    }

    return registro;
}
  

    @Override
    public ArrayList<registrofactura> listar() {
     String consulta = "CALL listar_facturas();";  

    ArrayList<registrofactura> listaFacturas = new ArrayList<>();

    try {
        
        PreparedStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareStatement(consulta);

        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            registrofactura factura = new registrofactura();
            factura.setIDregistro(rs.getInt("IDfactura"));
            factura.setIDproveedor(rs.getInt("IDproveedor"));
            factura.setIDproducto(rs.getInt("IDproducto"));
            factura.setDescripcionAdic(rs.getString("DescripcionAdicional"));
            factura.setFecha(rs.getDate("Fecha"));
            factura.setEstadofactura(rs.getString("estadofactura"));
        
            listaFacturas.add(factura);
        }
        
    } catch (SQLException e) {
        System.err.println("Error al listar las facturas: " + e.getMessage());
    }
 
    return listaFacturas;
    }
   
}    
    
    
 

   
    

