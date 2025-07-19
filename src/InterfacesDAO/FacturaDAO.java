/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import clasesprincipales.Factura;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author user
 */
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface FacturaDAO {

    Factura insertar(Factura nuevaFactura); 
    boolean actualizar(Factura factura);    
    boolean eliminar(int idFactura);         
    Factura seleccionar(int idFactura);      
    List<Factura> listar();                  
    List<Factura> listarPorProveedor(int idProveedor);  
    List<Factura> listarPorRangoFecha(LocalDateTime desde, LocalDateTime hasta);  
    List<Factura> listarPorEstado(Factura.Estado estado);  
    boolean cambiarEstado(int idFactura, Factura.Estado nuevoEstado);  
    BigDecimal obtenerTotalFacturadoProveedor(int idProveedor, LocalDateTime desde, LocalDateTime hasta);  // SELECT SUM(Total) ...
}
    

