/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesprincipales;

import java.util.Date;

/**
 *
 * @author user
 */
public class historialmovimientos {
    private int IDmovimiento;
    private String entidad;
    private int IDentidad;
    private String operacion;
    private Date fechaoperacion;
    private String detalleactualizacion;
    private String detalleanterior;
    
    public historialmovimientos (){
  
    }

    public historialmovimientos(int IDmovimiento, String entidad, int IDentidad, String operacion, Date fechaoperacion, String detalleactualizacion, String detalleanterior) {
        this.IDmovimiento = IDmovimiento;
        this.entidad = entidad;
        this.IDentidad = IDentidad;
        this.operacion = operacion;
        this.fechaoperacion = fechaoperacion;
        this.detalleactualizacion = detalleactualizacion;
        this.detalleanterior = detalleanterior;
    }

    public int getIDmovimiento() {
        return IDmovimiento;
    }

    public void setIDmovimiento(int IDmovimiento) {
        this.IDmovimiento = IDmovimiento;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public int getIDentidad() {
        return IDentidad;
    }

    public void setIDentidad(int IDentidad) {
        this.IDentidad = IDentidad;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Date getFechaoperacion() {
        return fechaoperacion;
    }

    public void setFechaoperacion(Date fechaoperacion) {
        this.fechaoperacion = fechaoperacion;
    }

    public String getDetalleactualizacion() {
        return detalleactualizacion;
    }

    public void setDetalleactualizacion(String detalleactualizacion) {
        this.detalleactualizacion = detalleactualizacion;
    }

    public String getDetalleanterior() {
        return detalleanterior;
    }

    public void setDetalleanterior(String detalleanterior) {
        this.detalleanterior = detalleanterior;
    }
    
}
