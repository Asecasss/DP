/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import AccesoDatos.productoAcces;
import clasesprincipales.Producto;
import java.sql.Connection;
import conexionsql.ConexionSingleton;
import controlador.productoControl;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class VerCategoria extends javax.swing.JDialog {
    private TableRowSorter<DefaultTableModel> sorter;
    private DefaultTableModel modeloTabla;
    private productoControl control;
    private String tipoFiltro;
    private int idFiltro; 
    private final String PLACEHOLDER = "Buscar…";
    private final Color  COLOR_PLACEHOLDER = Color.GRAY;
    private final Color  COLOR_TEXTO       = Color.BLACK;

    public VerCategoria(java.awt.Frame parent, boolean modal, String tipoFiltro, int idFiltro) {
        super(parent, modal);
        this.tipoFiltro = tipoFiltro;
        this.idFiltro = idFiltro;
        initComponents();
        tablaProductos.requestFocusInWindow();
        modeloTabla = (DefaultTableModel) tablaProductos.getModel(); 
        sorter = new TableRowSorter<>(modeloTabla);
        tablaProductos.setRowSorter(sorter);

        Connection conn = ConexionSingleton.getInstancia().getConexion(); 
        control = new productoControl(new productoAcces(conn));

        cargarTablaProductos(tipoFiltro, idFiltro);
        colocarPlaceholder();

        comboFiltros.addActionListener(e -> aplicarFiltro());

        barraBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                aplicarFiltroTexto();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aplicarFiltroTexto();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aplicarFiltroTexto();
            }
        });
    }
    private void cargarTablaProductos(String tipoFiltro, int id) {
    modeloTabla.setRowCount(0); 
    List<Producto> lista;

    if ("tipo".equalsIgnoreCase(tipoFiltro)) {
        lista = control.listarPorTipo(id);
    } else if ("subtipo".equalsIgnoreCase(tipoFiltro)) {
        lista = control.listarPorSubtipo(id);
    } else {
        JOptionPane.showMessageDialog(this, "Filtro desconocido: " + tipoFiltro);
        return;
    }

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
}
    private void aplicarFiltro() {
    String seleccion = (String) comboFiltros.getSelectedItem();

    
    sorter.setSortKeys(null); 

    switch (seleccion) {
        case "Precio de menor a mayor":
            sorter.setSortKeys(List.of(new RowSorter.SortKey(3, SortOrder.ASCENDING)));
            break;
        case "Precio de mayor a menor":
            sorter.setSortKeys(List.of(new RowSorter.SortKey(3, SortOrder.DESCENDING)));
            break;
        case "Stock de menor a mayor":
            sorter.setSortKeys(List.of(new RowSorter.SortKey(4, SortOrder.ASCENDING)));
            break;
        case "Stock de mayor a menor":
            sorter.setSortKeys(List.of(new RowSorter.SortKey(4, SortOrder.DESCENDING)));
            break;
        case "Ordenar de A - Z":
            sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.ASCENDING))); 
            break;
        case "Ordenar de Z - A":
            sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.DESCENDING))); 
            break;
        default:
            // No ordenar
            sorter.setSortKeys(null);
        }
    }
    private void aplicarFiltroTexto() {
        String texto = barraBusqueda.getText().trim();

        if (texto.equals(PLACEHOLDER) || texto.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        }
    }

    private void colocarPlaceholder() {
        barraBusqueda.setText(PLACEHOLDER);
        barraBusqueda.setForeground(COLOR_PLACEHOLDER);
        barraBusqueda.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        comboFiltros = new javax.swing.JComboBox<>();
        barraBusqueda = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(865, 520));

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre ", "Marca", "Precio", "Stock", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaProductos);

        jScrollPane2.setViewportView(jScrollPane1);

        jLabel9.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel9.setText("Detalles");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("Ordenar por :");

        comboFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Precio de menor a mayor", "Precio de mayor a menor", "Stock de menor a mayor", "Stock de mayor a menor", "Ordenar de A - Z", "Ordenar de Z - A" }));
        comboFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFiltrosActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(comboFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(barraBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(393, 393, 393)
                    .addComponent(jLabel9)
                    .addContainerGap(393, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(barraBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(244, 244, 244)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(244, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void barraBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraBusquedaActionPerformed
   
    }//GEN-LAST:event_barraBusquedaActionPerformed

    private void comboFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFiltrosActionPerformed
        aplicarFiltro();
    }//GEN-LAST:event_comboFiltrosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VerCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barraBusqueda;
    private javax.swing.JComboBox<String> comboFiltros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
