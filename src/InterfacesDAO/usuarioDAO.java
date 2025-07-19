/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import clasesprincipales.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public interface usuarioDAO {
    Usuario insertar (Usuario nuevousuario);
    void actualizar (Usuario usuario);
    void eliminar (int IDusuario);
    Usuario seleccionar(int IDusuario);
    List<Usuario> listar();
    Usuario verificarLogin(String username, String contrasena);
}
