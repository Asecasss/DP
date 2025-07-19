/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesprincipales;

import java.util.Set;
import seguridad.ControlAcceso;
import seguridad.Permiso;
import seguridad.Rol;



public class Usuario {
    private int IDusuario;
    private String Username;
    private Rol rol;
    private String contrasenia;
    
    public Usuario (){
        
    }

    public Usuario(int IDusuario, String Username, Rol rol, String contrasenia) {
        this.IDusuario = IDusuario;
        this.Username = Username;
        this.rol = rol;
        this.contrasenia = contrasenia;
    }

    public int getIDusuario() {
        return IDusuario;
    }

    public void setIDusuario(int IDusuario) {
        this.IDusuario = IDusuario;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public Set<Permiso> getPermisos() {
    return ControlAcceso.permisosDe(this.rol);
    
    }
    
}


   

   
