/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import clasesprincipales.Tipo;
import java.util.List;

/**
 *
 * @author user
 */
public interface TipoDAO {
     List<Tipo> listar(int idCategoria);
}
