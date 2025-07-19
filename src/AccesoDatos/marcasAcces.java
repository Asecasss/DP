/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.marcasDAO;
import clasesprincipales.Marcas;
import java.sql.*;
import java.util.ArrayList;

public class marcasAcces implements marcasDAO {

    private final Connection conn;

    public  marcasAcces(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Marcas insertar(Marcas nuevaMarca) {
        try (CallableStatement stmt = conn.prepareCall("{CALL insertar_marca(?, ?)}")) {
            stmt.setString(1, nuevaMarca.getNombre());
            stmt.setString(2, nuevaMarca.getPaisOrigen());
            stmt.executeUpdate();
            return nuevaMarca; 
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void actualizar(Marcas marca) {
        try (CallableStatement stmt = conn.prepareCall("{CALL actualizar_marca(?, ?, ?)}")) {
            stmt.setInt(1, marca.getIdMarca());
            stmt.setString(2, marca.getNombre());
            stmt.setString(3, marca.getPaisOrigen());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int idMarca) {
        try (CallableStatement stmt = conn.prepareCall("{CALL eliminar_marca(?)}")) {
            stmt.setInt(1, idMarca);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Marcas seleccionar(int idMarca) {
        String sql = "SELECT * FROM marca WHERE idMarca = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMarca);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Marcas(
                        rs.getInt("idMarca"),
                        rs.getString("nombre"),
                        rs.getString("paisOrigen")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Marcas> listar() {
        ArrayList<Marcas> lista = new ArrayList<>();
        try (CallableStatement stmt = conn.prepareCall("{CALL listar_marcas()}")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Marcas m = new Marcas(
                        rs.getInt("idMarca"),
                        rs.getString("nombre"),
                        rs.getString("paisOrigen")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

