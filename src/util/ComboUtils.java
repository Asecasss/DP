/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public final class ComboUtils {
    private ComboUtils() { }
    
    public static <T> void seleccionarPorId(JComboBox<T> combo, int idBuscado, Function<T, Integer> getId) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            T item = combo.getItemAt(i);
            if (item != null && getId.apply(item) == idBuscado) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }


    public static <T> void cargar(JComboBox<T> combo, Supplier<List<T>> proveedor, String mensajeError) {
        try {
            combo.removeAllItems();
            for (T item : proveedor.get()) {
                combo.addItem(item);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar " + mensajeError + ": " + e.getMessage());
        }
    }
}

