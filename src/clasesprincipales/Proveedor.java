/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesprincipales;

public class Proveedor {
    private int IDproveedor;
    private String nombre;
    private String telefono;
    private String direccion;

    public Proveedor() {
    }

    public Proveedor(int IDproveedor, String nombre, String telefono, String direccion) {
        this.IDproveedor = IDproveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Proveedor(String nombre, String telefono, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // ✅ El método estándar para obtener el id
    public int getId() {
        return IDproveedor;
    }

    public void setId(int IDproveedor) {
        this.IDproveedor = IDproveedor;
    }

    public int getIDproveedor() {
        return IDproveedor;
    }

    public void setIDproveedor(int IDproveedor) {
        this.IDproveedor = IDproveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

    


    
    
    
   



   

