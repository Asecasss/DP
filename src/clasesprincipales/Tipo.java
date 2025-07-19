/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesprincipales;

/**
 *
 * @author user
 */
public class Tipo extends Categoria {
    private int idTipo;
    private int idCategoriaPadre;

   
    public Tipo(int idTipo, String nombre) {
        super(0, nombre, false, null); 
        this.idTipo = idTipo;
    }

    // Constructor completo para cuando tenemos todos los datos
    public Tipo(int idTipo, String nombre, boolean esCompuesta, String tipo, int idCategoriaPadre) {
        super(idTipo, nombre, esCompuesta, tipo);
        this.idTipo = idTipo;
        this.idCategoriaPadre = idCategoriaPadre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public int getIdCategoriaPadre() {
        return idCategoriaPadre;
    }
    
    @Override
    public String toString() {
    return nombre;          
    }
}

