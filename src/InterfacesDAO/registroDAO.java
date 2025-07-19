/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;
import clasesprincipales.registrofactura;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public interface registroDAO {
    registrofactura insertar (registrofactura  nuevoregistro);
    void actualizar (registrofactura registro);
    void eliminar (int IDregistro);
    registrofactura seleccionar(int IDregistro);
    ArrayList<registrofactura> listar();
}
    

