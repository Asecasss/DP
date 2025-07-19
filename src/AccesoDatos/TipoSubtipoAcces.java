/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import clasesprincipales.Subtipo;
import clasesprincipales.Tipo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import InterfacesDAO.JtreeDAO;
import java.sql.Connection; 
import java.util.LinkedHashMap;

/**
 *
 * @author user
 */
public class TipoSubtipoAcces implements JtreeDAO {

    private final Connection conn;             
    private final tipoAcces TipoDAO;               
    private final subtipoAcces SubtipoDAO;            

    public TipoSubtipoAcces(Connection conn) {  
        this.conn = conn;
        this.TipoDAO    = new tipoAcces(conn);      
        this.SubtipoDAO = new subtipoAcces(conn);   
    }

    @Override
    public Map<Tipo, List<Subtipo>> obtenerTiposYSubtipos(int idCategoria) {
        Map<Tipo, List<Subtipo>> jerarquia = new LinkedHashMap<>();

        List<Tipo> tipos = TipoDAO.listar(idCategoria);   

        for (Tipo tipo : tipos) {
            List<Subtipo> subtipos = SubtipoDAO.listar(tipo.getIdTipo());
            jerarquia.put(tipo, subtipos);
        }

        return jerarquia;
    }
}
