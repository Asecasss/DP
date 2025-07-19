/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesprincipales;

/**
 *
 * @author user
 */
public abstract class Categoria {
    protected int IDCategoria;
    protected String nombre;
    protected boolean esCompuesta;
    protected String tipo;

    public Categoria() {
    }

    public Categoria(int IDCategoria, String nombre, boolean esCompuesta, String tipo) {
        this.IDCategoria = IDCategoria;
        this.nombre = nombre;
        this.esCompuesta = esCompuesta;
        this.tipo = tipo;
    }

    // ✅ Método estándar para obtener el ID
    public int getId() {
        return IDCategoria;
    }

    public void setId(int IDCategoria) {
        this.IDCategoria = IDCategoria;
    }

    public int getIDCategoria() {
        return IDCategoria;
    }

    public void setIDCategoria(int IDCategoria) {
        this.IDCategoria = IDCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsCompuesta() {
        return esCompuesta;
    }

    public void setEsCompuesta(boolean esCompuesta) {
        this.esCompuesta = esCompuesta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    @Override
    public String toString() {
        return nombre;
    }
}

