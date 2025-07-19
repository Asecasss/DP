/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesprincipales;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

public class Factura {

    public enum Estado { PENDIENTE, PAGADA, ANULADA }

    private static final BigDecimal IGV_RATE = new BigDecimal("0.18");

    private Integer id;
    private Proveedor proveedor;
    private LocalDateTime fecha = LocalDateTime.now();
    private Estado estado = Estado.PENDIENTE;

    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal igv = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;

    private final List<Linea> lineas = new ArrayList<>();

    public Factura(Integer id, Proveedor proveedor) {
        this.id = id;
        this.proveedor = Objects.requireNonNull(proveedor, "Proveedor no puede ser null");
    }

    public void agregarLinea(Linea l) {
        validarLinea(l);
        lineas.add(l);
        recalcularTotales();
    }

    public void quitarLinea(int idx) {
        lineas.remove(idx);
        recalcularTotales();
    }

    public void recalcularTotales() {
        subtotal = lineas.stream().map(Linea::getSubtotalLinea).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
        igv = subtotal.multiply(IGV_RATE).setScale(2, RoundingMode.HALF_UP);
        total = subtotal.add(igv).setScale(2, RoundingMode.HALF_UP);
    }

    private void validarLinea(Linea l) {
        Objects.requireNonNull(l.getProducto(), "Producto no puede ser null");
        if (l.getCantidad() <= 0) throw new IllegalArgumentException("Cantidad debe ser mayor a cero");
        if (l.getPrecioUnit() == null || l.getPrecioUnit().signum() < 0)
            throw new IllegalArgumentException("Precio unitario inválido");
    }

    /** Clase interna Linea **/
    public static class Linea {
        private Integer idDetalle;
        private Producto producto;
        private int cantidad;
        private BigDecimal precioUnit;
        private String descripcion;
        private BigDecimal subtotalLinea = BigDecimal.ZERO;

        public BigDecimal getSubtotalLinea() {
            if (subtotalLinea != null && subtotalLinea.compareTo(BigDecimal.ZERO) > 0) {
                return subtotalLinea;
            }
            if (precioUnit != null) {
                return precioUnit.multiply(BigDecimal.valueOf(cantidad)).setScale(2, RoundingMode.HALF_UP);
            }
            return BigDecimal.ZERO;
        }

        public Integer getIdDetalle() {
            return idDetalle;
        }

        public void setIdDetalle(Integer idDetalle) {
            this.idDetalle = idDetalle;
        }

        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public BigDecimal getPrecioUnit() {
            return precioUnit;
        }

        public void setPrecioUnit(BigDecimal precioUnit) {
            this.precioUnit = precioUnit;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public void setSubtotalLinea(BigDecimal subtotalLinea) {
            this.subtotalLinea = subtotalLinea;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIGV(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<Linea> getLineas() {
        return Collections.unmodifiableList(lineas);
    }
}


    
   
   
   

    
