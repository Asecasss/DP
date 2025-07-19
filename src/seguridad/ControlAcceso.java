/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguridad;
import java.util.*;
/**
 *
 * @author user
 */
public final class ControlAcceso {

    private static final Map<Rol, EnumSet<Permiso>> MAPA;

    static {
        Map<Rol, EnumSet<Permiso>> tmp = new EnumMap<>(Rol.class);

        // Permisos del ADMINISTRADOR: acceso total
        tmp.put(Rol.ADMINISTRADOR, EnumSet.allOf(Permiso.class));

        // Permisos del EMPLEADO: acceso limitado
        tmp.put(Rol.EMPLEADO, EnumSet.of(
            Permiso.USUARIOS_VER,
            Permiso.PRODUCTOS_VER,
            Permiso.PRODUCTOS_GESTIONAR,
            Permiso.CATEGORIAS_VER,
            Permiso.PROVEEDORES_VER,
            Permiso.FACTURA_VER,
            Permiso.STOCK_VER,
            Permiso.MARCA_VER,
            Permiso.CONFIGURACION_VER,
            Permiso.DEVOLUCIONES_VER,
            Permiso.HISTORIAL_VER
        ));

        MAPA = Collections.unmodifiableMap(tmp);
    }


    public static boolean puede(Rol rol, Permiso permiso) {
        return MAPA.getOrDefault(rol, EnumSet.noneOf(Permiso.class)).contains(permiso);
    }

   
    public static Set<Permiso> permisosDe(Rol rol) {
        return MAPA.getOrDefault(rol, EnumSet.noneOf(Permiso.class));
    }

    private ControlAcceso() {}
}
