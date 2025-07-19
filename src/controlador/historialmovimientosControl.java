/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import InterfacesDAO.movimientosDAO;
import clasesprincipales.historialmovimientos;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class historialmovimientosControl {

    private final movimientosDAO movimientosDAO;

    
    public historialmovimientosControl(movimientosDAO movimientosDAO) {
        this.movimientosDAO = movimientosDAO;
    }


    public List<historialmovimientos> listarMovimientos() {
        return movimientosDAO.list();
    }
}



    

