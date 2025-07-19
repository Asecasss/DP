 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import AccesoDatos.TipoSubtipoAcces;
import java.util.List;
import java.util.Map;
import InterfacesDAO.JtreeDAO;
import clasesprincipales.Subtipo;
import clasesprincipales.Tipo;
import conexionsql.ConexionSingleton;
import java.sql.Connection;


public class JtreeControl {

    private final JtreeDAO tipoSubtipoAcceso;

    public JtreeControl() {
        Connection conn = ConexionSingleton.getInstancia().getConexion();
        this.tipoSubtipoAcceso = new TipoSubtipoAcces(conn);
    }

    public Map<Tipo, List<Subtipo>> obtenerJerarquiaTiposYSubtipos(int idCategoria) {
        return tipoSubtipoAcceso.obtenerTiposYSubtipos(idCategoria);
    }
}

    

