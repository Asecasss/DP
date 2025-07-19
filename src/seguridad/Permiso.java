/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguridad;

/**
 *
 * @author user
 */
public enum Permiso {
    // === USUARIOS ===
    USUARIOS_VER,
    USUARIOS_GESTIONAR,

    // === PRODUCTOS ===
    PRODUCTOS_VER,
    PRODUCTOS_GESTIONAR,  

    // === CATEGORÍAS ===
    CATEGORIAS_VER,
    CATEGORIAS_EDITAR,

    // === PROVEEDORES ===
    PROVEEDORES_VER,
    PROVEEDORES_GESTIONAR,

    // === FACTURA ===
    FACTURA_VER,
    FACTURA_GESTIONAR,
    
    // === STOCK ===
    STOCK_VER,
    STOCK_ALERTA,

    // === CONFIGURACIÓN ===
    CONFIGURACION_VER,
    CONFIGURACION_ACTUALIZAR,
    
    // === DEVOLUCIONES ===
    DEVOLUCIONES_VER,
    DEVOLUCIONES_GESTIONAR,
    
    // === HISTORIAL DE MOVIMIENTOS ===
    HISTORIAL_VER,
    
    // === MARCAS ===
    MARCA_VER,
    MARCA_GESTIONAR
    
}
