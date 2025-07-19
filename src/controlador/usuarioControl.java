/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import AccesoDatos.usuarioAcces;
import InterfacesDAO.usuarioDAO;
import clasesprincipales.Usuario;
import java.util.ArrayList;

/**
 *
 * @author user
 */
import java.util.ArrayList;
import java.util.List;

public class usuarioControl {

    private final usuarioDAO usuarioDAO;

    public usuarioControl(usuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public usuarioControl() {
        this.usuarioDAO = new usuarioAcces();  
    }

    public Usuario insertar(Usuario nuevoUsuario) {
        return usuarioDAO.insertar(nuevoUsuario);
    }

    public void actualizar(Usuario usuario) {
        usuarioDAO.actualizar(usuario);
    }

    public void eliminar(int idUsuario) {
        usuarioDAO.eliminar(idUsuario);
    }

    public Usuario seleccionar(int idUsuario) {
        return usuarioDAO.seleccionar(idUsuario);
    }

    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }
    public Usuario verificarLogin(String username, String contrasena) {
    return usuarioDAO.verificarLogin(username, contrasena);
    }
}

    

