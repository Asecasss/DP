/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import InterfacesDAO.FacturaDAO;
import clasesprincipales.Factura;
import clasesprincipales.Producto;
import clasesprincipales.Proveedor;
import conexionsql.ConexionSingleton;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class FacturaAcces implements FacturaDAO {
     private final Connection conn;
      public FacturaAcces(Connection conn) {
      this.conn = conn;
}

    @Override
public Factura insertar(Factura nuevaFactura) {
    final String sqlCabecera = "{CALL insertar_factura(?, ?)}";
    final String sqlLinea = "{CALL insertar_detalle_factura(?,?,?,?,?)}";
    final String sqlRecalculo = "{CALL sp_recalc_totales(?)}"; 

    try {
        Connection conn = ConexionSingleton.getInstancia().getConexion();
        conn.setAutoCommit(false);

        try (CallableStatement stmtCab = conn.prepareCall(sqlCabecera)) {
            stmtCab.setInt(1, nuevaFactura.getProveedor().getId());
            stmtCab.registerOutParameter(2, java.sql.Types.INTEGER);
            stmtCab.execute();

            int idFactura = stmtCab.getInt(2);
            nuevaFactura.setId(idFactura);
        }

        try (CallableStatement stmtLinea = conn.prepareCall(sqlLinea)) {
            for (Factura.Linea l : nuevaFactura.getLineas()) {
                stmtLinea.setInt(1, nuevaFactura.getId());
                stmtLinea.setInt(2, l.getProducto().getIDProducto());
                stmtLinea.setInt(3, l.getCantidad());
                stmtLinea.setBigDecimal(4, l.getPrecioUnit());
                stmtLinea.setString(5, l.getDescripcion());
                stmtLinea.execute();
            }
        }

        try (CallableStatement stmtCalc = conn.prepareCall(sqlRecalculo)) {
            stmtCalc.setInt(1, nuevaFactura.getId());
            stmtCalc.execute();
        }

        conn.commit();
        System.out.println("✅ Factura registrada correctamente. ID: " + nuevaFactura.getId());
        return nuevaFactura;

    } catch (SQLException e) {
        try {
            ConexionSingleton.getInstancia().getConexion().rollback();
        } catch (SQLException ex) {
            System.err.println("⚠️ Error al hacer rollback: " + ex.getMessage());
        }
        System.err.println("❌ Error al insertar factura: " + e.getMessage());
        return null;

    } finally {
        try {
            ConexionSingleton.getInstancia().getConexion().setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("⚠️ Error al restaurar autoCommit: " + e.getMessage());
        }
    }
}


    @Override
    public boolean actualizar(Factura factura) {
    final String sql = "{CALL actualizar_factura(?, ?, ?, ?)}";

    try (CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(sql)) {

        stmt.setInt(1, factura.getId());
        stmt.setInt(2, factura.getProveedor().getId());
        stmt.setTimestamp(3, Timestamp.valueOf(factura.getFecha()));
        stmt.registerOutParameter(4, java.sql.Types.INTEGER);

        stmt.execute();

        int resultado = stmt.getInt(4);

        switch (resultado) {
            case 1:
                System.out.println("Factura actualizada correctamente. ID: " + factura.getId());
                break;
            case 2:
                System.err.println("No se pudo actualizar: La factura no está en estado pendiente.");
                break;
            case 3:
                System.err.println("No se pudo actualizar: La factura no existe.");
                break;
            default:
                System.err.println("Resultado inesperado.");
        }

        return resultado;

    } catch (SQLException e) {
        System.err.println("Error al actualizar factura: " + e.getMessage());
        return -1; // Error de SQL
    }
}

 

    @Override
    public boolean eliminar(int idFactura) {
    final String sql = "{CALL eliminar_factura(?)}";

    try (CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(sql)) {

        stmt.setInt(1, idFactura);
        stmt.execute();

        System.out.println(" Factura eliminada correctamente. ID: " + idFactura);
        return true;

    } catch (SQLException e) {
        System.err.println(" Error al eliminar factura (ID: " + idFactura + "): " + e.getMessage());
        return false;
    }
}

    @Override
public Factura seleccionar(int idFactura) {
    final String sqlCabecera = "SELECT * FROM factura WHERE IDfactura = ?";
    final String sqlLineas   = "SELECT * FROM detalle_factura WHERE IDfactura = ?";

    try {
        Connection conn = ConexionSingleton.getInstancia().getConexion();
        Factura factura = null;

     
        try (PreparedStatement stmtCab = conn.prepareStatement(sqlCabecera)) {
            stmtCab.setInt(1, idFactura);
            ResultSet rs = stmtCab.executeQuery();

            if (rs.next()) {
                Proveedor proveedor = buscarProveedorPorId(rs.getInt("IDproveedor"));
                factura = new Factura(
                    rs.getInt("IDfactura"),
                    proveedor
                );
                factura.setFecha(rs.getTimestamp("Fecha").toLocalDateTime());
                factura.setEstado(Factura.Estado.valueOf(rs.getString("Estado").toUpperCase()));
                factura.setSubtotal(rs.getBigDecimal("Subtotal"));
                factura.setIGV(rs.getBigDecimal("IGV"));
                factura.setTotal(rs.getBigDecimal("Total"));
            } else {
                return null;
            }
        }

        // 2. Obtener detalles
        try (PreparedStatement stmtDet = conn.prepareStatement(sqlLineas)) {
            stmtDet.setInt(1, idFactura);
            ResultSet rs = stmtDet.executeQuery();

            while (rs.next()) {
                Factura.Linea linea = new Factura.Linea();
                linea.setIdDetalle(rs.getInt("IDdetalle"));
                linea.setProducto(buscarProductoPorId(rs.getInt("IDproducto")));
                linea.setCantidad(rs.getInt("Cantidad"));
                linea.setPrecioUnit(rs.getBigDecimal("PrecioUnit"));
                linea.setDescripcion(rs.getString("DescripcionAdicional"));

     
                if (rs.getBigDecimal("SubtotalLinea") != null) {
                    linea.setSubtotalLinea(rs.getBigDecimal("SubtotalLinea"));
                }

                factura.agregarLinea(linea);
            }
        }

        return factura;

    } catch (SQLException e) {
        System.err.println("❌ Error al seleccionar factura: " + e.getMessage());
        return null;
    }
}

    @Override
public List<Factura> listar() {
    final String sql = "{CALL sp_listar_facturas_con_detalles()}";
    Map<Integer, Factura> facturaMap = new LinkedHashMap<>();

    try (CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(sql)) {
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int idFactura = rs.getInt("IDfactura");

            Factura factura = facturaMap.get(idFactura);
            if (factura == null) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("IDproveedor"));
                proveedor.setNombre(rs.getString("NombreProveedor"));

                factura = new Factura(idFactura, proveedor);
                factura.setFecha(rs.getTimestamp("Fecha").toLocalDateTime());
                factura.setEstado(Factura.Estado.valueOf(rs.getString("Estado").toUpperCase()));

                facturaMap.put(idFactura, factura);
            }

           
            Factura.Linea linea = new Factura.Linea();
            linea.setIdDetalle(rs.getInt("IDdetalle"));

            Producto producto = new Producto.Builder()
                    .setIDProducto(rs.getInt("IDproducto"))
                    .setNombre(rs.getString("NombreProducto"))
                    .build();

            linea.setProducto(producto);
            linea.setCantidad(rs.getInt("Cantidad"));
            linea.setPrecioUnit(rs.getBigDecimal("PrecioUnit"));
            linea.setDescripcion(rs.getString("DescripcionAdicional"));

            factura.agregarLinea(linea);
        }

    } catch (SQLException e) {
        System.err.println(" Error al listar facturas con detalles: " + e.getMessage());
    }

    return new ArrayList<>(facturaMap.values());
}

    @Override
    public List<Factura> listarPorProveedor(int idProveedor) {
    final String sql = "{CALL sp_listar_facturas_por_proveedor(?)}";
    Map<Integer, Factura> facturaMap = new LinkedHashMap<>();

    try (CallableStatement stmt = conn.prepareCall(sql)) {
        stmt.setInt(1, idProveedor);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int idFactura = rs.getInt("IDfactura");

            Factura factura = facturaMap.get(idFactura);
            if (factura == null) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("IDproveedor"));
                proveedor.setNombre(rs.getString("NombreProveedor"));

                factura = new Factura(idFactura, proveedor);
                factura.setFecha(rs.getTimestamp("Fecha").toLocalDateTime());
                factura.setEstado(Factura.Estado.valueOf(rs.getString("Estado").toUpperCase()));

                facturaMap.put(idFactura, factura);
            }

            Producto producto = new Producto.Builder()
                .setIDProducto(rs.getInt("IDproducto"))
                .setNombre(rs.getString("NombreProducto"))
                .build();

            Factura.Linea linea = new Factura.Linea();
            linea.setIdDetalle(rs.getInt("IDdetalle"));
            linea.setProducto(producto);
            linea.setCantidad(rs.getInt("Cantidad"));
            linea.setPrecioUnit(rs.getBigDecimal("PrecioUnit"));
            linea.setDescripcion(rs.getString("DescripcionAdicional"));

            factura.agregarLinea(linea);
        }

    } catch (SQLException e) {
        System.err.println(" Error al listar facturas por proveedor: " + e.getMessage());
    }

    return new ArrayList<>(facturaMap.values());
}

    @Override
    public List<Factura> listarPorRangoFecha(LocalDateTime desde, LocalDateTime hasta) {
    final String sql = "{CALL sp_listar_facturas_por_rango_fecha(?, ?)}";
    Map<Integer, Factura> facturaMap = new LinkedHashMap<>();

    try (CallableStatement stmt = conn.prepareCall(sql)) {
        stmt.setTimestamp(1, Timestamp.valueOf(desde));
        stmt.setTimestamp(2, Timestamp.valueOf(hasta));

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int idFactura = rs.getInt("IDfactura");

            Factura factura = facturaMap.get(idFactura);
            if (factura == null) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("IDproveedor"));
                proveedor.setNombre(rs.getString("NombreProveedor"));

                factura = new Factura(idFactura, proveedor);
                factura.setFecha(rs.getTimestamp("Fecha").toLocalDateTime());
                factura.setEstado(Factura.Estado.valueOf(rs.getString("Estado").toUpperCase()));

                facturaMap.put(idFactura, factura);
            }

            Producto producto = new Producto.Builder()
                .setIDProducto(rs.getInt("IDproducto"))
                .setNombre(rs.getString("NombreProducto"))
                .build();

            Factura.Linea linea = new Factura.Linea();
            linea.setIdDetalle(rs.getInt("IDdetalle"));
            linea.setProducto(producto);
            linea.setCantidad(rs.getInt("Cantidad"));
            linea.setPrecioUnit(rs.getBigDecimal("PrecioUnit"));
            linea.setDescripcion(rs.getString("DescripcionAdicional"));

            factura.agregarLinea(linea);
        }

    } catch (SQLException e) {
        System.err.println("Error al listar facturas por rango de fechas: " + e.getMessage());
    }

    return new ArrayList<>(facturaMap.values());
}

    @Override
public List<Factura> listarPorEstado(Factura.Estado estado) {
    final String sql = "{CALL sp_listar_facturas_por_estado(?)}";
    Map<Integer, Factura> facturaMap = new LinkedHashMap<>();

    try (CallableStatement stmt = conn.prepareCall(sql)) {
        stmt.setString(1, estado.name().toLowerCase());

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int idFactura = rs.getInt("IDfactura");

            Factura factura = facturaMap.get(idFactura);
            if (factura == null) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("IDproveedor"));
                proveedor.setNombre(rs.getString("NombreProveedor"));

                factura = new Factura(idFactura, proveedor);
                factura.setFecha(rs.getTimestamp("Fecha").toLocalDateTime());
                factura.setEstado(Factura.Estado.valueOf(rs.getString("Estado").toUpperCase()));

                facturaMap.put(idFactura, factura);
            }

            Producto producto = new Producto.Builder()
                .setIDProducto(rs.getInt("IDproducto"))
                .setNombre(rs.getString("NombreProducto"))
                .build();

            Factura.Linea linea = new Factura.Linea();
            linea.setIdDetalle(rs.getInt("IDdetalle"));
            linea.setProducto(producto);
            linea.setCantidad(rs.getInt("Cantidad"));
            linea.setPrecioUnit(rs.getBigDecimal("PrecioUnit"));
            linea.setDescripcion(rs.getString("DescripcionAdicional"));

            factura.agregarLinea(linea);
        }

    } catch (SQLException e) {
        System.err.println(" Error al listar facturas por estado: " + e.getMessage());
    }

    return new ArrayList<>(facturaMap.values());
}

    @Override
public boolean cambiarEstado(int idFactura, Factura.Estado nuevoEstado) {
    final String sql = "{CALL sp_cambiar_estado_factura(?, ?)}";

    try (CallableStatement stmt = conn.prepareCall(sql)) {
        stmt.setInt(1, idFactura);
        stmt.setString(2, nuevoEstado.name().toLowerCase());

        int filas = stmt.executeUpdate();

        if (filas > 0) {
            System.out.println("Estado actualizado correctamente. ID: " + idFactura);
            return true;
        } else {
            System.err.println("️ No se encontró la factura o no se actualizó el estado.");
            return false;
        }

    } catch (SQLException e) {
        System.err.println("Error al cambiar estado de factura: " + e.getMessage());
        return false;
    }
}

    @Override
public BigDecimal obtenerTotalFacturadoProveedor(int idProveedor, LocalDateTime desde, LocalDateTime hasta) {
    final String sql = "{CALL sp_total_facturado_proveedor(?, ?, ?)}";

    try (CallableStatement stmt = conn.prepareCall(sql)) {
        stmt.setInt(1, idProveedor);
        stmt.setTimestamp(2, Timestamp.valueOf(desde));
        stmt.setTimestamp(3, Timestamp.valueOf(hasta));

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getBigDecimal("TotalFacturado");
        }

    } catch (SQLException e) {
        System.err.println("❌ Error al obtener total facturado por proveedor: " + e.getMessage());
    }

    return BigDecimal.ZERO;
}
     private Proveedor buscarProveedorPorId(int id) {
        return new proveedorAcces(ConexionSingleton.getInstancia().getConexion()).seleccionar(id);
    }
    private Producto buscarProductoPorId(int id) {
        return new productoAcces(ConexionSingleton.getInstancia().getConexion()).seleccionar(id);
    }
}

     
    
    
 

   
    

