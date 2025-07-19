/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import AccesoDatos.stockbajoAcces;
import InterfacesDAO.stockbajoDAO;
import clasesprincipales.stockbajo;
import java.util.ArrayList;

/**
 *
 * @author user
 */
import java.util.ArrayList;

public class stockbajoControl {

    private final stockbajoDAO stockBajoDAO;

    
    public stockbajoControl(stockbajoDAO stockBajoDAO) {
        this.stockBajoDAO = stockBajoDAO;
    }
    public stockbajoControl() {
        this.stockBajoDAO = new stockbajoAcces();  
    }

    public ArrayList<stockbajo> listarStockBajo() {
        return stockBajoDAO.list();
    }
}


