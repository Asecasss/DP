/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import clasesprincipales.Subtipo;
import clasesprincipales.Tipo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public interface JtreeDAO {
      Map<Tipo, List<Subtipo>> obtenerTiposYSubtipos(int idCategoria);
}
    
