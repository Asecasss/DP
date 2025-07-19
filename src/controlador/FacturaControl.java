/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import InterfacesDAO.FacturaDAO;
import clasesprincipales.Factura;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class FacturaControl {

    private final FacturaDAO facturaDAO;

    public FacturaControl(FacturaDAO facturaDAO) {
        this.facturaDAO = facturaDAO;
    }

    public Factura insertar(Factura nuevaFactura) {
        return facturaDAO.insertar(nuevaFactura);
    }

    public boolean actualizar(Factura factura) {
        return facturaDAO.actualizar(factura);
    }

    public boolean eliminar(int idFactura) {
        return facturaDAO.eliminar(idFactura);
    }

    public Factura seleccionar(int idFactura) {
        return facturaDAO.seleccionar(idFactura);
    }

    public List<Factura> listar() {
        return facturaDAO.listar();
    }

    public List<Factura> listarPorProveedor(int idProveedor) {
        return facturaDAO.listarPorProveedor(idProveedor);
    }

    public List<Factura> listarPorRangoFecha(LocalDateTime desde, LocalDateTime hasta) {
        return facturaDAO.listarPorRangoFecha(desde, hasta);
    }

    public List<Factura> listarPorEstado(Factura.Estado estado) {
        return facturaDAO.listarPorEstado(estado);
    }

    public boolean cambiarEstado(int idFactura, Factura.Estado nuevoEstado) {
        return facturaDAO.cambiarEstado(idFactura, nuevoEstado);
    }

    public BigDecimal obtenerTotalFacturadoProveedor(int idProveedor, LocalDateTime desde, LocalDateTime hasta) {
        return facturaDAO.obtenerTotalFacturadoProveedor(idProveedor, desde, hasta);
    }
}

