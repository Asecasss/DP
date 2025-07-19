/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.TipoDAO;
import clasesprincipales.Tipo;
import conexionsql.ConexionSingleton;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;                          

public class tipoAcces implements TipoDAO {
      private final Connection conn;
      public tipoAcces(Connection conn) {
      this.conn = conn;
}

    /**
     *
     * @param idCategoria
     * @return
     */
    @Override
public List<Tipo> listar(int idCategoria) {   
    List<Tipo> lista = new ArrayList<>();
    String consulta = "{CALL listar_tipocategoria(?)}";

    try (CallableStatement stmt = conn.prepareCall(consulta)) {   
        stmt.setInt(1, idCategoria);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idTipo        = rs.getInt("idTipo");
                String nombreTipo = rs.getString("nombreTipo");
                int idCat         = rs.getInt("idCategoria");

                Tipo tipo = new Tipo(idTipo, nombreTipo, false, "simple", idCat);
                lista.add(tipo);
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al listar tipos por categoría: " + e.getMessage());
    }

    return lista;
    }
}


