/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.SubtipoDAO;
import clasesprincipales.Subtipo;
import conexionsql.ConexionSingleton;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author user
 */
public class subtipoAcces implements SubtipoDAO {
     private final Connection conn;
      public subtipoAcces(Connection conn) {
      this.conn = conn;
}

    @Override
public ArrayList<Subtipo> listar(int idTipo) {            
    ArrayList<Subtipo> lista = new ArrayList<>();
    String consulta = "{CALL listar_subtipocategoria(?)}";

    try (CallableStatement stmt = conn.prepareCall(consulta)) {    
        stmt.setInt(1, idTipo);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int    idSubtipo      = rs.getInt("idSubtipo");
                String nombreSubtipo  = rs.getString("nombreSubtipo");
                int    idTipoFK       = rs.getInt("idTipo");   

                Subtipo subtipo = new Subtipo(
                        idSubtipo,
                        nombreSubtipo,
                        true,
                        "compuesta"
                );

                lista.add(subtipo);
            }
        }

    } catch (SQLException e) {
        System.err.println("❌ Error al listar subtipos por tipo: " + e.getMessage());
    }

    return lista;
    }
}
