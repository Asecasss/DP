/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesprincipales;

/**
 *
 * @author user
 */
public class Producto {

    private int IDProducto;

    private Proveedor proveedor;
    private Categoria categoria;
    private Tipo tipo;
    private Subtipo subtipo;
    private String imagen; 
    private String nombre;
    private Marcas marca;
    private String modelo;
    private int anio;
    private double precio;
    private int stock;
    private String estado;

    public void setAnio(int anio) {
    this.anio = anio;
    
    }

    public int getAnio() {
    return anio;
    
    }
    private Producto(Builder builder) {
        this.IDProducto = builder.IDProducto;
        this.proveedor = builder.proveedor;
        this.categoria = builder.categoria;
        this.tipo = builder.tipo;
        this.subtipo = builder.subtipo;
        this.imagen = builder.imagen; 
        this.nombre = builder.nombre;
        this.marca = builder.marca;
        this.modelo = builder.modelo;
        this.anio = builder.anio;
        this.precio = builder.precio;
        this.stock = builder.stock;
        this.estado = builder.estado;
    }

    public static class Builder {
        private int IDProducto;
        private Proveedor proveedor;
        private Categoria categoria;
        private Tipo tipo;
        private Subtipo subtipo;
        private String imagen;
        private String nombre;
        private Marcas marca;
        private String modelo;
        private int anio;
        private double precio;
        private int stock;
        private String estado;

        public Builder setIDProducto(int IDProducto) {
            this.IDProducto = IDProducto;
            return this;
        }

        public Builder setProveedor(Proveedor proveedor) {
            this.proveedor = proveedor;
            return this;
        }

        public Builder setCategoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public Builder setTipo(Tipo tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder setSubtipo(Subtipo subtipo) {
            this.subtipo = subtipo;
            return this;
        }

        public Builder setImagen(String imagen) { 
            this.imagen = imagen;
            return this;
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setMarca(Marcas marca) {
            this.marca = marca;
            return this;
        }

        public Builder setModelo(String modelo) {
            this.modelo = modelo;
            return this;
        }

        public Builder setAnio(int anio) {
            this.anio = anio;
            return this;
        }

        public Builder setPrecio(double precio) {
            this.precio = precio;
            return this;
        }

        public Builder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder setEstado(String estado) {
            this.estado = estado;
            return this;
        }

        public Producto build() {
            return new Producto(this);
        }
    
    }

    // Getters

    public int getIDProducto() { return IDProducto; }

    public Proveedor getProveedor() { return proveedor; }
    public Categoria getCategoria() { return categoria; }
    public Tipo getTipo()           { return tipo; }
    public Subtipo getSubtipo()     { return subtipo; }
    public String getImagen() { return imagen; } 
    public String getNombre() { return nombre; }
    public Marcas getMarca()  { return marca; }
    public String getModelo() { return modelo; }
    public double getPrecio() { return precio; }
    public int getStock()     { return stock; }
    public String getEstado() { return estado; }
}




   
    

    

