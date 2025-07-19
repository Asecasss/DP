/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionsql;
import GUI.JPanelRound;
import javax.swing.JFrame;
import java.awt.*;

 
public class Main {
    
    public  static  void  main(String[] args){
        ConexionSingleton conexionSingleton = ConexionSingleton.getInstancia();
        conexionSingleton.probarConexion();
        JFrame frame = new JFrame("Ejemplo con JPanelRound");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);
        JPanelRound panel = new JPanelRound(30, 30); 
        panel.setBackground(Color.CYAN); 
        panel.setBounds(50, 50, 200, 100);
         frame.add(panel);

        frame.setVisible(true);
        
    }
}





 




