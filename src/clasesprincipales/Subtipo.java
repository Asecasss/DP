/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesprincipales;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Subtipo extends Categoria {
    private int idSubtipo;

    public Subtipo(int idSubtipo, String nombre) {
        super(0, nombre, false, null);
        this.idSubtipo = idSubtipo;
    }

    public Subtipo(int idSubtipo, String nombre, boolean esCompuesta, String tipo) {
        super(idSubtipo, nombre, esCompuesta, tipo);
        this.idSubtipo = idSubtipo;
    }

    public int getIdSubtipo() {
        return idSubtipo;
    }
    
    @Override
    public String toString() {
    return nombre;          
    }
}
