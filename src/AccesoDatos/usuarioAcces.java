/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.usuarioDAO;
import clasesprincipales.Usuario;
import conexionsql.ConexionSingleton;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import seguridad.Rol;

/**
 *
 * @author user
 */
public class usuarioAcces implements usuarioDAO{

   @Override
public Usuario insertar(Usuario nuevoUsuario) {
    String consulta = "{CALL insertar_usuario(?, ?, ?)}";   // username, IDrol, contraseña

    try (CallableStatement stmt =
            ConexionSingleton.getInstancia()
                             .getConexion()
                             .prepareCall(consulta)) {

        // 1. Username
        stmt.setString(1, nuevoUsuario.getUsername());

        // 2. Rol (convertido de enum Rol a IDrol)
        int idRol = obtenerIdRolDesdeNombre(nuevoUsuario.getRol());
        if (idRol == -1) {
            System.err.println("No se pudo encontrar el ID del rol.");
            return null;
        }
        stmt.setInt(2, idRol);

        // 3. Contraseña
        stmt.setString(3, nuevoUsuario.getContrasenia());

        // Ejecutar
        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Usuario registrado correctamente: " + nuevoUsuario.getUsername());
        } else {
            System.err.println("No se pudo registrar el usuario.");
        }

    } catch (SQLException e) {
        if ("45000".equals(e.getSQLState())) {
            System.err.println("Error del procedimiento almacenado: " + e.getMessage());
        } else {
            System.err.println("Error al registrar el usuario: " + e.getMessage());
        }
    }

    return nuevoUsuario;
}

   @Override
public void actualizar(Usuario usuario) {
    String consulta = "{CALL actualizar_usuario(?, ?, ?, ?)}"; // IDusuario, username, IDrol, contrasena

    try (CallableStatement stmt = 
         ConexionSingleton.getInstancia().getConexion().prepareCall(consulta)) {

        // 1. IDusuario
        stmt.setInt(1, usuario.getIDusuario());

        // 2. Username
        stmt.setString(2, usuario.getUsername());

        // 3. IDrol (obtenido desde enum Rol)
        int idRol = obtenerIdRolDesdeNombre(usuario.getRol());
        if (idRol == -1) {
            System.err.println("Error: No se pudo obtener el ID del rol.");
            return;
        }
        stmt.setInt(3, idRol);

        // 4. Contraseña
        stmt.setString(4, usuario.getContrasenia());

        // Ejecutar actualización
        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Usuario actualizado correctamente: " + usuario.getUsername());
        } else {
            System.err.println("No se encontró el usuario con el ID proporcionado.");
        }

    } catch (SQLException e) {
        if ("45000".equals(e.getSQLState())) {
            System.err.println("Error del procedimiento almacenado: " + e.getMessage());
        } else {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }
}


   @Override
public void eliminar(int IDusuario) {
    String consulta = "{CALL eliminar_usuario(?)}";

    try (CallableStatement stmt =
         ConexionSingleton.getInstancia().getConexion().prepareCall(consulta)) {

        stmt.setInt(1, IDusuario);

        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Usuario eliminado correctamente, ID: " + IDusuario);
        } else {
            System.err.println("No se encontró el usuario con el ID proporcionado.");
        }

    } catch (SQLException e) {
        if ("45000".equals(e.getSQLState())) {
            System.err.println("Error del procedimiento almacenado: " + e.getMessage());
        } else {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}

    @Override
public Usuario seleccionar(int IDusuario) {
    String consulta = """
        SELECT 
            u.IDusuario, 
            u.Username, 
            u.contrasena, 
            r.nombre AS rol
        FROM 
            usuarios u
        JOIN 
            rol r ON u.IDrol = r.IDrol
        WHERE 
            u.IDusuario = ?
    """;

    Usuario usuario = null;

    try (PreparedStatement stmt = 
         ConexionSingleton.getInstancia().getConexion().prepareStatement(consulta)) {
        
        stmt.setInt(1, IDusuario);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIDusuario(rs.getInt("IDusuario"));
            usuario.setUsername(rs.getString("Username"));
            usuario.setContrasenia(rs.getString("contrasena"));

            // Convertir el nombre del rol a enum Rol
            String rolTexto = rs.getString("rol").toUpperCase(); // "ADMINISTRADOR"
            usuario.setRol(Rol.valueOf(rolTexto));
        } else {
            System.err.println("No se encontró el usuario con el ID proporcionado.");
        }

    } catch (SQLException e) {
        System.err.println("Error al seleccionar el usuario: " + e.getMessage());
    }

    return usuario;
}

  @Override
public ArrayList<Usuario> listar() {
    String consulta = "{CALL listar_usuarios()}"; 
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    try (CallableStatement stmt =
         ConexionSingleton.getInstancia().getConexion().prepareCall(consulta)) {

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIDusuario(rs.getInt("IDusuario"));
            usuario.setUsername(rs.getString("Username"));

            // 🔁 Convertimos el rol de texto a enum
            String rolTexto = rs.getString("rol").toUpperCase(); // "ADMINISTRADOR"
            usuario.setRol(Rol.valueOf(rolTexto));

            usuario.setContrasenia(rs.getString("contraseña"));

            listaUsuarios.add(usuario);
        }

        System.out.println("Se han listado " + listaUsuarios.size() + " usuarios.");

    } catch (SQLException e) {
        System.err.println("Error al listar los usuarios: " + e.getMessage());
    }

    return listaUsuarios;
}

public int obtenerIdRolDesdeNombre(Rol rol) {
    String consulta = "SELECT IDrol FROM rol WHERE UPPER(nombre) = ?";
    int idRol = -1;

    try (PreparedStatement stmt = 
         ConexionSingleton.getInstancia().getConexion().prepareStatement(consulta)) {
        
        stmt.setString(1, rol.name()); 
        
        ResultSet rs = stmt.executeQuery(); 

        if (rs.next()) {
            idRol = rs.getInt("IDrol");
        } else {
            System.err.println("No se encontró el rol: " + rol);
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener ID del rol: " + e.getMessage());
    }

    return idRol;
}
public Rol obtenerRolDesdeId(int idRol) {
    String consulta = "SELECT nombre FROM rol WHERE IDrol = ?";
    Rol rol = null;

    try (PreparedStatement stmt = 
         ConexionSingleton.getInstancia().getConexion().prepareStatement(consulta)) {
        
        stmt.setInt(1, idRol);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String nombreRol = rs.getString("nombre").toUpperCase(); // "ADMINISTRADOR"
            rol = Rol.valueOf(nombreRol); // Convierte a enum
        } else {
            System.err.println("No se encontró un rol con ID: " + idRol);
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener el rol desde ID: " + e.getMessage());
    }

    return rol;
    }

   @Override
   public Usuario verificarLogin(String username, String contrasena) {
    String sql = "SELECT u.IDusuario, u.Username, u.contrasena, r.nombre AS rol " +
                 "FROM usuarios u JOIN rol r ON u.IDrol = r.IDrol " +
                 "WHERE u.Username = ? AND u.contrasena = ?";

    try (PreparedStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, contrasena);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIDusuario(rs.getInt("IDusuario"));
            usuario.setUsername(rs.getString("Username"));
            usuario.setContrasenia(rs.getString("contrasena"));

            String rolTexto = rs.getString("rol").toUpperCase();
            usuario.setRol(Rol.valueOf(rolTexto));
            return usuario;
        }
    } catch (SQLException e) {
        System.err.println("Error al verificar login: " + e.getMessage());
    }
    return null;
    }
}
