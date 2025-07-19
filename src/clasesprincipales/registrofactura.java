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
public class registrofactura {
    private int IDregistro;
    private int IDproveedor;
    private int IDproducto;
    private String DescripcionAdic;
    private Date Fecha;
    private String estadofactura;
    
    public registrofactura(){
    }

    public registrofactura(int IDregistro, int IDproveedor, int IDproducto, String DescripcionAdic, Date Fecha, String estadofactura) {
        this.IDregistro = IDregistro;
        this.IDproveedor = IDproveedor;
        this.IDproducto = IDproducto;
        this.DescripcionAdic = DescripcionAdic;
        this.Fecha = Fecha;
        this.estadofactura = estadofactura;
    }

    public int getIDregistro() {
        return IDregistro;
    }

    public void setIDregistro(int IDregistro) {
        this.IDregistro = IDregistro;
    }

    public int getIDproveedor() {
        return IDproveedor;
    }

    public void setIDproveedor(int IDproveedor) {
        this.IDproveedor = IDproveedor;
    }

    public int getIDproducto() {
        return IDproducto;
    }

    public void setIDproducto(int IDproducto) {
        this.IDproducto = IDproducto;
    }

    public String getDescripcionAdic() {
        return DescripcionAdic;
    }

    public void setDescripcionAdic(String DescripcionAdic) {
        this.DescripcionAdic = DescripcionAdic;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getEstadofactura() {
        return estadofactura;
    }

    public void setEstadofactura(String estadofactura) {
        this.estadofactura = estadofactura;
    }
}
   
   

    
