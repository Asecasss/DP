/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesprincipales;
import java.util.Date;


public class Devolucion {
    private int IDdevolucion;
    private int IDproducto;
    private int IDproveedor;
    private String descripcion;    
    private Date fechadevolucion; 
    private String motivo;
    
    public Devolucion (){
        
    }

    public Devolucion(int IDdevolucion, int IDproducto, int IDproveedor, String descripcion, Date fechadevolucion, String motivo) {
        this.IDdevolucion = IDdevolucion;
        this.IDproducto = IDproducto;
        this.IDproveedor = IDproveedor;
        this.descripcion = descripcion;
        this.fechadevolucion = fechadevolucion;
        this.motivo = motivo;
    }

    public int getIDdevolucion() {
        return IDdevolucion;
    }

    public void setIDdevolucion(int IDdevolucion) {
        this.IDdevolucion = IDdevolucion;
    }

    public int getIDproducto() {
        return IDproducto;
    }

    public void setIDproducto(int IDproducto) {
        this.IDproducto = IDproducto;
    }

    public int getIDproveedor() {
        return IDproveedor;
    }

    public void setIDproveedor(int IDproveedor) {
        this.IDproveedor = IDproveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
   


