/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;
import clasesprincipales.*;
import InterfacesDAO.productoDAO;
import clasesprincipales.Marcas;
import clasesprincipales.Producto;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import conexionsql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author user
 */
public class productoAcces implements productoDAO {
       private final Connection conn;
       public productoAcces(Connection conn) { 
       this.conn = conn;
 }


@Override
public Producto insertar(Producto nuevoProducto) {
    final String sql = "{CALL insertar_producto(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

    try (CallableStatement stmt =
            ConexionSingleton.getInstancia().getConexion().prepareCall(sql)) {
        stmt.setInt   (1,  nuevoProducto.getProveedor().getId());
        stmt.setInt   (2,  nuevoProducto.getCategoria().getId());
        stmt.setInt   (3,  nuevoProducto.getTipo().getId());
        if (nuevoProducto.getSubtipo() != null) {
            stmt.setInt(4, nuevoProducto.getSubtipo().getId());
        } else {
            stmt.setNull(4, java.sql.Types.INTEGER);      
        }
        stmt.setString(5,  nuevoProducto.getNombre());
        stmt.setInt   (6,  nuevoProducto.getMarca().getIdMarca());
        stmt.setString(7,  nuevoProducto.getModelo());
        stmt.setInt(8, nuevoProducto.getAnio());
        stmt.setDouble(9,  nuevoProducto.getPrecio());
        stmt.setInt   (10, nuevoProducto.getStock());
        stmt.setString(11, nuevoProducto.getEstado());
        stmt.setString(12, nuevoProducto.getImagen());
        stmt.registerOutParameter(13, java.sql.Types.INTEGER);
        stmt.execute();

        int idGenerado = stmt.getInt(13);   
        return new Producto.Builder()
                .setIDProducto(idGenerado)
                .setProveedor(nuevoProducto.getProveedor())
                .setCategoria(nuevoProducto.getCategoria())
                .setTipo(nuevoProducto.getTipo())
                .setSubtipo(nuevoProducto.getSubtipo())
                .setNombre(nuevoProducto.getNombre())
                .setMarca(nuevoProducto.getMarca())
                .setModelo(nuevoProducto.getModelo())
                .setAnio(nuevoProducto.getAnio())
                .setPrecio(nuevoProducto.getPrecio())
                .setStock(nuevoProducto.getStock())
                .setEstado(nuevoProducto.getEstado())
                .build();

    } catch (SQLException e) {
        System.err.println("❌ Error al insertar producto: " + e.getMessage());
        return null;
    }
}


    @Override
public boolean actualizar(Producto p) {

    final String sql = "{CALL actualizar_producto(?,?,?,?,?,?,?,?,?,?,?,?,?)}"; // 13 parámetros

    try (CallableStatement stmt =
            ConexionSingleton.getInstancia().getConexion().prepareCall(sql)) {
        stmt.setInt    (1,  p.getIDProducto());
        stmt.setInt    (2,  p.getProveedor().getId());
        stmt.setInt    (3,  p.getCategoria().getId());
        stmt.setInt    (4,  p.getTipo().getId());
        if (p.getSubtipo() != null) {
            stmt.setInt(5, p.getSubtipo().getId());
        } else {
            stmt.setNull(5, java.sql.Types.INTEGER);
        }
        stmt.setInt   (6,  p.getMarca().getIdMarca());
        stmt.setString(7,  p.getNombre()); 
        stmt.setString (8,  p.getModelo());
        stmt.setInt (9,  p.getAnio());
        stmt.setDouble (10, p.getPrecio());
        stmt.setInt    (11, p.getStock());
        stmt.setString (12, p.getEstado());
        stmt.setString (13, p.getImagen());          
  
        int filas = stmt.executeUpdate();           

        if (filas > 0) {
            System.out.println("✅ Producto actualizado. ID: " + p.getIDProducto());
            return true;
        } else {
            System.err.println("⚠️  No se actualizó ninguna fila (ID quizá inexistente).");
            return false;
        }

    } catch (SQLException e) {
        System.err.println("❌ Error al actualizar producto: " + e.getMessage());
        return false;
    }
}

   @Override
   public boolean eliminar(int IDProducto) {

    final String sql = "{CALL eliminar_producto(?)}";

    try (CallableStatement stmt =
             ConexionSingleton.getInstancia()
                              .getConexion()
                              .prepareCall(sql)) {

        stmt.setInt(1, IDProducto);
        stmt.execute();                     
        System.out.println("Producto eliminado correctamente. ID: " + IDProducto);
        return true;

    } catch (SQLException e) {
        if ("45000".equals(e.getSQLState())) {               
            System.err.println("El producto " + IDProducto + " no existe.");
        } else {
            System.err.println("Error al eliminar ID " + IDProducto + ": " + e.getMessage());
        }
        return false;
    }
}
@Override
public Producto seleccionar(int IDproducto) {
    String consulta = "{CALL obtener_producto_completo(?)}"; // Ahora llamas al procedimiento
    Producto producto = null;

    try {
        CallableStatement stmt = ConexionSingleton.getInstancia().getConexion().prepareCall(consulta);
        stmt.setInt(1, IDproducto);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            
            Proveedor proveedor = new Proveedor(
                rs.getInt("IDproveedor"),
                rs.getString("nombreProveedor"),
                rs.getString("telefonoProveedor"),
                rs.getString("direccionProveedor")
            );
            Categoria categoria = new Categoria(
                rs.getInt("IDcategoria"),
                rs.getString("nombreCategoria"),
                false, 
                    
                null
            ) {};
            
            Tipo tipo = new Tipo(rs.getInt("IDtipo"), rs.getString("nombreTipo"));
            
            Subtipo subtipo = new Subtipo(rs.getInt("IDsubtipo"), rs.getString("nombreSubtipo"));
            
            Marcas marca = new Marcas(
                rs.getInt("idMarca"),
                rs.getString("nombreMarca"),
                rs.getString("paisOrigen")
            );
            
            producto = new Producto.Builder()
                .setIDProducto(rs.getInt("IDproducto"))
                .setProveedor(proveedor)
                .setCategoria(categoria)
                .setTipo(tipo)
                .setSubtipo(subtipo)
                .setNombre(rs.getString("nombreProducto"))
                .setMarca(marca)  
                .setModelo(rs.getString("modelo"))
                .setAnio(rs.getInt("anio"))
                .setPrecio(rs.getDouble("precio"))
                .setStock(rs.getInt("stock"))
                .setEstado(rs.getString("estado"))
                .setImagen(rs.getNString("imagen"))
                .build();
        }
    } catch (SQLException e) {
        System.err.println("Error al seleccionar el producto con ID " + IDproducto + ": " + e.getMessage());
    }

    return producto;
}
@Override
public List<Producto> listar() {
    String consulta = "{CALL listar_productos()}";
    List<Producto> listaProductos = new ArrayList<>();

    // ► Usa la conexión inyectada (conn)
    try (CallableStatement stmt = conn.prepareCall(consulta);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Marcas marca = new Marcas(
                    rs.getInt("idMarca"),
                    rs.getString("nombreMarca"),
                    rs.getString("paisOrigen")
            );

            Proveedor proveedor = new Proveedor(
                    rs.getInt("IDproveedor"),
                    rs.getString("nombreProveedor"),
                    rs.getString("telefonoProveedor"),
                    rs.getString("direccionProveedor")
            );

            Categoria categoria = new Categoria(
                    rs.getInt("IDcategoria"),
                    rs.getString("nombreCategoria"),
                    false,
                    rs.getString("tipoCategoria")
            ) {};

            Tipo tipo = new Tipo(
                    rs.getInt("IDtipo"),
                    rs.getString("nombreTipo")
            );

            Subtipo subtipo = new Subtipo(
                    rs.getInt("IDsubtipo"),
                    rs.getString("nombreSubtipo")
            );

            Producto producto = new Producto.Builder()
                    .setIDProducto(rs.getInt("IDproducto"))
                    .setProveedor(proveedor)
                    .setCategoria(categoria)
                    .setTipo(tipo)
                    .setSubtipo(subtipo)
                    .setNombre(rs.getString("nombre"))
                    .setMarca(marca)
                    .setModelo(rs.getString("modelo"))
                    .setAnio(rs.getInt("anio"))
                    .setPrecio(rs.getDouble("precio"))
                    .setStock(rs.getInt("stock"))
                    .setEstado(rs.getString("estado"))
                    .setImagen(rs.getString("imagen")) 
                    .build();

            listaProductos.add(producto);
        }

    } catch (SQLException e) {
        System.err.println("Error al listar productos: " + e.getMessage());
    }

    return listaProductos;
    }

@Override
public List<Producto> listarPorTipo(int idTipo) {
    String consulta = "{CALL listar_productos_por_tipo(?)}";
    List<Producto> listaProductos = new ArrayList<>();

    try (CallableStatement stmt = conn.prepareCall(consulta)) {
        stmt.setInt(1, idTipo);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                Marcas marca = new Marcas(
                        rs.getInt("idMarca"),
                        rs.getString("marca"),
                        rs.getString("paisOrigen") // si lo incluyes en el SP
                );

                Proveedor proveedor = new Proveedor(
                        rs.getInt("IDproveedor"),
                        rs.getString("proveedor"),
                        rs.getString("telefonoProveedor"), // opcional, si lo traes
                        rs.getString("direccionProveedor") // opcional
                );

                Categoria categoria = new Categoria(
                        rs.getInt("IDcategoria"),
                        rs.getString("categoria"),
                        false, // ¿seCompuesta? solo si lo necesitas
                        rs.getString("tipoCategoria") // si se incluye
                ) {};

                Tipo tipo = new Tipo(
                        rs.getInt("IDtipo"),
                        rs.getString("tipo")
                );

                Subtipo subtipo = new Subtipo(
                        rs.getInt("IDsubtipo"),
                        rs.getString("subtipo")
                );

                Producto producto = new Producto.Builder()
                        .setIDProducto(rs.getInt("IDproducto"))
                        .setProveedor(proveedor)
                        .setCategoria(categoria)
                        .setTipo(tipo)
                        .setSubtipo(subtipo)
                        .setNombre(rs.getString("nombreProducto"))
                        .setMarca(marca)
                        .setModelo(rs.getString("modelo"))
                        .setAnio(rs.getInt("anio"))
                        .setPrecio(rs.getDouble("precio"))
                        .setStock(rs.getInt("stock"))
                        .setEstado(rs.getString("estado"))
                        .setImagen(rs.getString("imagen"))
                        .build();

                listaProductos.add(producto);
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al listar productos por tipo: " + e.getMessage());
    }

    return listaProductos;
    }

@Override
public List<Producto> listarPorSubtipo(int idSubtipo) {
    String consulta = "{CALL listar_productos_por_subtipo(?)}";
    List<Producto> listaProductos = new ArrayList<>();

    try (CallableStatement stmt = conn.prepareCall(consulta)) {
        stmt.setInt(1, idSubtipo);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Marcas marca = new Marcas(
                        rs.getInt("idMarca"),
                        rs.getString("marca"),
                        rs.getString("paisOrigen")
                );

                Proveedor proveedor = new Proveedor(
                        rs.getInt("IDproveedor"),
                        rs.getString("proveedor"),
                        rs.getString("telefonoProveedor"),
                        rs.getString("direccionProveedor")
                );

                Categoria categoria = new Categoria(
                        rs.getInt("IDcategoria"),
                        rs.getString("categoria"),
                        false,
                        rs.getString("tipoCategoria")
                ) {};

                Tipo tipo = new Tipo(
                        rs.getInt("IDtipo"),
                        rs.getString("tipo")
                );

                Subtipo subtipo = new Subtipo(
                        rs.getInt("IDsubtipo"),
                        rs.getString("subtipo")
                );

                Producto producto = new Producto.Builder()
                        .setIDProducto(rs.getInt("IDproducto"))
                        .setProveedor(proveedor)
                        .setCategoria(categoria)
                        .setTipo(tipo)
                        .setSubtipo(subtipo)
                        .setNombre(rs.getString("nombreProducto"))
                        .setMarca(marca)
                        .setModelo(rs.getString("modelo"))
                        .setAnio(rs.getInt("anio"))
                        .setPrecio(rs.getDouble("precio"))
                        .setStock(rs.getInt("stock"))
                        .setEstado(rs.getString("estado"))
                        .setImagen(rs.getString("imagen"))
                        .build();

                listaProductos.add(producto);
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al listar productos por subtipo: " + e.getMessage());
    }

    return listaProductos;
    }

    @Override
    public List<Producto> obtenerProductosConStockBajo() {
    List<Producto> listaProductos = new ArrayList<>();
    String consulta = "{CALL listar_StockBajo()}";

    try (CallableStatement stmt = conn.prepareCall(consulta);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Categoria categoria = new Categoria() {};
            categoria.setNombre(rs.getString("Categoria"));

            Producto producto = new Producto.Builder()
                .setIDProducto(rs.getInt("IDproducto"))
                .setNombre(rs.getString("NombreProducto"))
                .setCategoria(categoria)
                .setStock(rs.getInt("stock"))
                .setEstado(rs.getString("Estado"))
                .build();

            listaProductos.add(producto);
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener productos con stock bajo: " + e.getMessage());
    }

    return listaProductos;
    }
}

