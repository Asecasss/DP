/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.proveedorDAO;
import clasesprincipales.Proveedor;
import java.sql.Connection;   
import conexionsql.ConexionSingleton;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.util.List;
/**
 *
 * @author user
 */
public class proveedorAcces implements proveedorDAO{
      private final Connection conn;
      public proveedorAcces(Connection conn) {   // <<— AGREGAR
        this.conn = conn;
    }


    @Override
public Proveedor insertar(Proveedor nuevoProveedor) {
    String consulta = "{CALL insertar_proveedor(?, ?, ?)}"; // Solo 3 parámetros
    try (CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta)) {
      
        stmt.setString(1, nuevoProveedor.getNombre());
        stmt.setString(2, nuevoProveedor.getTelefono());
        stmt.setString(3, nuevoProveedor.getDireccion());

        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                
                nuevoProveedor.setIDproveedor(rs.getInt("IDproveedor"));
            }
        }

        System.out.println("Proveedor insertado correctamente: " + nuevoProveedor.getNombre());
    } catch (SQLException e) {
        System.err.println("Error al insertar el proveedor: " + e.getMessage());
    }
    return nuevoProveedor;
}


@Override
public void actualizar(Proveedor proveedor) {
    String consulta = "{CALL actualizar_proveedor(?, ?, ?, ?)}";
    try (CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta)) {
        stmt.setInt(1, proveedor.getIDproveedor());
        stmt.setString(2, proveedor.getNombre());
        stmt.setString(3, proveedor.getTelefono());
        stmt.setString(4, proveedor.getDireccion());
        stmt.execute();

        System.out.println("Proveedor actualizado correctamente: " + proveedor.getNombre());
    } catch (SQLException e) {
        if ("45000".equals(e.getSQLState())) {
            throw new RuntimeException("Error del procedimiento almacenado: " + e.getMessage());
        } else {
            throw new RuntimeException("Error al actualizar el proveedor: " + e.getMessage());
        }
    }
}

@Override
public void eliminar(int IDproveedor) {
    String consulta = "{CALL eliminar_proveedor(?)}";
    try (CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta)) {
        stmt.setInt(1, IDproveedor);
        stmt.execute();
        System.out.println("Proveedor eliminado correctamente.");
    } catch (SQLException e) {
        if ("45000".equals(e.getSQLState())) {
            System.err.println("Error del procedimiento almacenado: " + e.getMessage());
        } else {
            System.err.println("Error al eliminar el proveedor: " + e.getMessage());
        }
    }
}

@Override
public Proveedor seleccionar(int IDproveedor) {
    String consulta = "SELECT * FROM proveedor WHERE IDproveedor = ?";
    Proveedor proveedor = null;

    try (PreparedStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareStatement(consulta)) {
        stmt.setInt(1, IDproveedor);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setIDproveedor(rs.getInt("IDproveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setDireccion(rs.getString("direccion"));

                System.out.println("Proveedor encontrado: " + proveedor.getNombre());
            } else {
                System.out.println("No se encontró un proveedor con ID: " + IDproveedor);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al seleccionar el proveedor: " + e.getMessage());
    }

    return proveedor;
}

@Override
public List<Proveedor> listar() {
        final String consulta = "{CALL listar_proveedor()}";
        List<Proveedor> lista = new ArrayList<>();

        try (CallableStatement stmt = conn.prepareCall(consulta);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIDproveedor(rs.getInt("IDproveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();   
        }
        return lista;
    }
}
