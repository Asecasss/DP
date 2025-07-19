/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesprincipales;

/**
 *
 * @author user
 */
public class Configuracion {
    private int IDconfig;
    private String nombreEmpresa;
    private int ruc;
    private String telefono;

    public Configuracion(int IDconfig, String nombreEmpresa, int ruc, String telefono) {
        this.IDconfig = IDconfig;
        this.nombreEmpresa = nombreEmpresa;
        this.ruc = ruc;
        this.telefono = telefono;
    }

    public int getIDconfig() {
        return IDconfig;
    }

    public void setIDconfig(int IDconfig) {
        this.IDconfig = IDconfig;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
