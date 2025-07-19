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
public class stockbajo {
    private int IDstockbajo;
    private int IDproducto;
    private String nombreproducto;
    private int stock;
    private Date FechaCreacion;
    
    public stockbajo (){
        
    }

    public stockbajo(int IDstockbajo, int IDproducto, String nombreproducto, int stock, Date FechaCreacion) {
        this.IDstockbajo = IDstockbajo;
        this.IDproducto = IDproducto;
        this.nombreproducto = nombreproducto;
        this.stock = stock;
        this.FechaCreacion = FechaCreacion;
    }

    public int getIDstockbajo() {
        return IDstockbajo;
    }

    public void setIDstockbajo(int IDstockbajo) {
        this.IDstockbajo = IDstockbajo;
    }

    public int getIDproducto() {
        return IDproducto;
    }

    public void setIDproducto(int IDproducto) {
        this.IDproducto = IDproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

}
