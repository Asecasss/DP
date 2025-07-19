/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.categoriaDAO;
import clasesprincipales.*;
import conexionsql.ConexionSingleton;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class categoriaAcces implements categoriaDAO {
      private final Connection conn;
      public categoriaAcces(Connection conn) {
      this.conn = conn;
}

   @Override
   public Categoria insertar(Categoria nuevaCategoria, Integer idPadre) {
    final String sql = "{CALL insertar_categoria(?, ?, ?)}";

    try (Connection conn = ConexionSingleton.getInstancia().getConexion();
         CallableStatement st = conn.prepareCall(sql)) {

        st.setString(1, nuevaCategoria.getNombre());
        st.setBoolean(2, nuevaCategoria.isEsCompuesta());
        st.setString(3, nuevaCategoria.getTipo());

        boolean hasResultSet = st.execute();

        if (hasResultSet) {
            try (ResultSet rs = st.getResultSet()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt("id");

                    return nuevaCategoria.isEsCompuesta()
                            ? new Subtipo(idGenerado,nuevaCategoria.getNombre(), true, nuevaCategoria.getTipo())
                            : new Tipo(idGenerado,nuevaCategoria.getNombre(), false, nuevaCategoria.getTipo(),  idPadre != null ? idPadre : 0); 
                }
            }
        }

    } catch (SQLException e) {
        System.err.println("❌ Insertar categoría: " + e.getMessage());
    }
    return null;
}

    @Override
    public void actualizar(Categoria categoria) {
    final String sql = "{CALL actualizar_categoria(?, ?, ?, ?)}";

    try (Connection conn = ConexionSingleton.getInstancia().getConexion();
         CallableStatement st = conn.prepareCall(sql)) {

        
        st.setInt(1, categoria.getIDCategoria());
        st.setString(2, categoria.getNombre());
        st.setBoolean(3, categoria.isEsCompuesta());
        st.setString(4, categoria.getTipo());

        
        st.execute();

    } catch (SQLException e) {
        System.err.println("❌ Actualizar categoría: " + e.getMessage());
    }
}

    @Override
    public void eliminar(int idCategoria) {
    final String sql = "{CALL eliminar_categoria(?)}";
    try (Connection conn = ConexionSingleton.getInstancia().getConexion();
         CallableStatement st = conn.prepareCall(sql)) {

        st.setInt(1, idCategoria);
        st.execute();

    } catch (SQLException e) {
        System.err.println("❌ Eliminar categoría: " + e.getMessage());
    }
}

    @Override
    public Categoria seleccionar(int idCategoria) {
        final String sql = "SELECT IDcategoria, nombre, esCompuesta, tipo FROM categoria WHERE IDcategoria = ?";

    try (Connection conn = ConexionSingleton.getInstancia().getConexion();
         PreparedStatement st = conn.prepareStatement(sql)) {

        st.setInt(1, idCategoria);

        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                int id            = rs.getInt("IDcategoria");
                String nombre     = rs.getString("nombre");
                boolean compuesta = rs.getBoolean("esCompuesta");
                String tipo       = rs.getString("tipo");

                return compuesta
                        ? new Subtipo(id, nombre, true, tipo)
                        : new Tipo   (id, nombre, false, tipo, 0); // Puedes ajustar idPadre si es necesario
            }
        }

    } catch (SQLException e) {
        System.err.println("❌ Seleccionar categoría: " + e.getMessage());
    }

    return null; 
}

    @Override
    public List<Categoria> listar() {
    List<Categoria> lista = new ArrayList<>();
    final String sql = "{CALL listar_categoria()}";


    try (CallableStatement st = conn.prepareCall(sql);      
         ResultSet rs = st.executeQuery()) {

        while (rs.next()) {
            int     id        = rs.getInt("IDcategoria");
            String  nombre    = rs.getString("nombre");
            boolean compuesta = rs.getBoolean("esCompuesta");
            String  tipo      = rs.getString("tipo");

            Categoria cat = compuesta
                    ? new Subtipo(id, nombre, true, tipo)
                    : new Tipo(id, nombre, false, tipo, 0);   // ajusta idPadre si lo usas

            lista.add(cat);
        }

    } catch (SQLException e) {
        System.err.println("❌ Listar categorías: " + e.getMessage());
    }

    return lista;
    }
}


