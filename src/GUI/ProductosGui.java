/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import AccesoDatos.productoAcces;
import clasesprincipales.Producto;
import conexionsql.ConexionSingleton;
import controlador.productoControl;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection; 
import java.util.List;
import javax.swing.SwingUtilities;
import java.sql.SQLException;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 *
 * @author user
 */
public class ProductosGui extends javax.swing.JPanel {
    
    private final String PLACEHOLDER = "Buscar…";
    private final Color  COLOR_PLACEHOLDER = Color.GRAY;
    private final Color  COLOR_TEXTO       = Color.BLACK;
    private TableRowSorter<DefaultTableModel> sorter;
    private void abrirVentana() {
    int fila = tablaProductos.getSelectedRow();

    if (fila != -1) {
        int idProducto = (int) tablaProductos.getValueAt(fila, 0);
        Producto producto = control.seleccionar(idProducto);

        if (producto != null) {
            VerProducto dialogo = new VerProducto(null, true); 
            dialogo.cargarDatos(producto); 
            dialogo.setLocationRelativeTo(this);
            dialogo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el producto.");
        }

    } else {
        JOptionPane.showMessageDialog(this, "Seleccione una fila primero.");
    }
}

    
    private DefaultTableModel modeloTabla;
    private productoControl control;
    
    public ProductosGui(){
        initComponents();
        colocarPlaceholder();
        Connection conn = ConexionSingleton.getInstancia().getConexion();         
        control = new productoControl( new productoAcces(conn));        
        modeloTabla = (DefaultTableModel) tablaProductos.getModel(); 
        sorter = new TableRowSorter<>(modeloTabla);
        tablaProductos.setRowSorter(sorter);
        cargarTablaProductos();
        barraBusqueda.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) { filtrar(); }
        @Override
        public void removeUpdate(DocumentEvent e) { filtrar(); }
        @Override
        public void changedUpdate(DocumentEvent e) { filtrar(); }
        private void filtrar() {
            barraBusquedaActionPerformed(null); 
        }
    });
}
    private void cargarTablaProductos() {
    System.out.println("→ recargando tabla");       // TRAZA 1
    modeloTabla.setRowCount(0);
    List<Producto> lista = control.listar();
    System.out.println("   Productos en lista: " + lista.size());  // TRAZA 2

    
    for (Producto p : lista) {
        modeloTabla.addRow(new Object[]{
            p.getIDProducto(),
            p.getNombre(),
            p.getMarca().getNombre(),
            p.getPrecio(),
            p.getStock(),
            p.getEstado(),
            (p.getProveedor() != null ? p.getProveedor().getNombre() : "N/A")
        });
    }

    
    System.out.println("   Filas en modelo: " + modeloTabla.getRowCount());  // TRAZA 3
    tablaProductos.repaint();

}
    private void colocarPlaceholder() {

    barraBusqueda.setText(PLACEHOLDER);
    barraBusqueda.setForeground(COLOR_PLACEHOLDER);
    barraBusqueda.addFocusListener(new FocusAdapter() {

        @Override
        public void focusGained(FocusEvent e) {
            // Al entrar: si aún está el placeholder, límpialo
            if (barraBusqueda.getText().equals(PLACEHOLDER)) {
                barraBusqueda.setText("");
                barraBusqueda.setForeground(COLOR_TEXTO);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (barraBusqueda.getText().trim().isEmpty()) {
                barraBusqueda.setText(PLACEHOLDER);
                barraBusqueda.setForeground(COLOR_PLACEHOLDER);
                sorter.setRowFilter(null);          
            }
        }
    });
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelRound1 = new GUI.JPanelRound();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        btnagregarProducto = new RSMaterialComponent.RSButtonMaterialIconDos();
        btnEliminarProducto = new RSMaterialComponent.RSButtonMaterialIconDos();
        btnactualizarProducto = new RSMaterialComponent.RSButtonMaterialIconDos();
        barraBusqueda = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(890, 682));

        jPanelRound1.setBackground(new java.awt.Color(102, 102, 102));
        jPanelRound1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Productos");

        tablaProductos.setBackground(new java.awt.Color(204, 204, 204));
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Nombre", "Marca", "Precio Unitario ", "Stock", "Estado ", "Proovedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductos);

        btnagregarProducto.setBackground(new java.awt.Color(29, 37, 172));
        btnagregarProducto.setText("Agregar");
        btnagregarProducto.setForegroundIcon(new java.awt.Color(253, 129, 4));
        btnagregarProducto.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnagregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setBackground(new java.awt.Color(29, 37, 172));
        btnEliminarProducto.setText("Eliminar");
        btnEliminarProducto.setForegroundIcon(new java.awt.Color(253, 129, 4));
        btnEliminarProducto.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnactualizarProducto.setBackground(new java.awt.Color(29, 37, 172));
        btnactualizarProducto.setText("Actualizar");
        btnactualizarProducto.setForegroundIcon(new java.awt.Color(253, 129, 4));
        btnactualizarProducto.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnactualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarProductoActionPerformed(evt);
            }
        });

        barraBusqueda.setForeground(new java.awt.Color(185, 182, 167));
        barraBusqueda.setText("Buscar...");
        barraBusqueda.setOpaque(true);
        barraBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barraBusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelRound1Layout = new javax.swing.GroupLayout(jPanelRound1);
        jPanelRound1.setLayout(jPanelRound1Layout);
        jPanelRound1Layout.setHorizontalGroup(
            jPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRound1Layout.createSequentialGroup()
                .addGroup(jPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRound1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(barraBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRound1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnactualizarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(btnagregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelRound1Layout.setVerticalGroup(
            jPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRound1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barraBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnactualizarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnagregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarProductoActionPerformed
    Runnable refresco = () -> {
    System.out.println("→ lambda: dentro de ProductosGui");   // TRAZA Λ
    cargarTablaProductos();
};
        AgregarProducto dialog = new AgregarProducto(
                (java.awt.Frame) SwingUtilities.getWindowAncestor(this), 
                true,                                                   
                this::cargarTablaProductos                              
        );
   
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);    
        
                         
    }//GEN-LAST:event_btnagregarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
      int fila = tablaProductos.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(this,
                "Selecciona un producto primero.",
                "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro de eliminar este producto?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (confirmacion == JOptionPane.YES_OPTION) {
        int idProducto = (int) tablaProductos.getValueAt(fila, 0);   

        try {
            boolean eliminado = control.eliminar(idProducto);        

            if (eliminado) {
                JOptionPane.showMessageDialog(this,
                        "Producto eliminado correctamente.");
                cargarTablaProductos();                              
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo eliminar el producto.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {                                   
            JOptionPane.showMessageDialog(this,
                    "Error al eliminar: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
      }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnactualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarProductoActionPerformed
       int fila = tablaProductos.getSelectedRow();
       if (fila == -1) {
       JOptionPane.showMessageDialog(this, "Selecciona un producto primero.");
       return;
       }

int id = (int) tablaProductos.getValueAt(fila, 0);

Producto productoSeleccionado = control.seleccionar(id); 

if (productoSeleccionado == null) {
    JOptionPane.showMessageDialog(this, "No se pudo encontrar el producto.");
    return;
}

// Abre el diálogo para editar
ActualizarProducto dialogo = new ActualizarProducto(
        SwingUtilities.getWindowAncestor(this),
        true,
        productoSeleccionado,
        control,                        // el mismo controlador ya creado
        this::cargarTablaProductos      // callback para refrescar la tabla
);
dialogo.setLocationRelativeTo(this);
dialogo.setVisible(true);
    }//GEN-LAST:event_btnactualizarProductoActionPerformed

    private void barraBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraBusquedaActionPerformed
    String texto = barraBusqueda.getText().trim();

    if (texto.isEmpty()) {
        
        sorter.setRowFilter(null);
    } else {
 
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(texto)));
    }

    }//GEN-LAST:event_barraBusquedaActionPerformed

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        if (evt.getClickCount() == 2) { // Doble clic
        abrirVentana();
    }

    }//GEN-LAST:event_tablaProductosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barraBusqueda;
    private RSMaterialComponent.RSButtonMaterialIconDos btnEliminarProducto;
    private RSMaterialComponent.RSButtonMaterialIconDos btnactualizarProducto;
    private RSMaterialComponent.RSButtonMaterialIconDos btnagregarProducto;
    private javax.swing.JLabel jLabel1;
    private GUI.JPanelRound jPanelRound1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
