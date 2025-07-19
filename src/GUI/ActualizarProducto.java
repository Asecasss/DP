  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;
import clasesprincipales.*;
import conexionsql.ConexionSingleton;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import controlador.productoControl;
import javax.swing.JFileChooser;
import util.ComboUtils;
import InterfacesDAO.categoriaDAO;
import InterfacesDAO.proveedorDAO;
import InterfacesDAO.marcasDAO;
import InterfacesDAO.TipoDAO;      
import InterfacesDAO.SubtipoDAO;
import AccesoDatos.*;
import java.sql.Connection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;


/**
 *
 * @author user
 */
public class ActualizarProducto extends javax.swing.JDialog {
     private final Runnable onSuccess;
     private final productoControl control;
     private Producto producto;
     private String rutaImagen;
     private final categoriaDAO  categoriaDao;
     private final proveedorDAO  proveedorDao;
     private final marcasDAO     marcasDao;
     private final TipoDAO       tipoDao;
     private final SubtipoDAO    subtipoDao;
   
     


    /**
     * Creates new form AgregarProducto
     */
    public ActualizarProducto(Window owner, boolean modal, Producto producto,productoControl control, Runnable onSuccess) {
        super(owner, ModalityType.DOCUMENT_MODAL);
        this.producto = producto;
        this.control = control;
        this.onSuccess = onSuccess;

         initComponents();
         Connection conn = ConexionSingleton.getInstancia().getConexion();
         categoriaDao = new categoriaAcces(conn);
         proveedorDao = new proveedorAcces(conn);
         marcasDao    = new marcasAcces(conn);
         tipoDao      = new tipoAcces(conn);
         subtipoDao   = new subtipoAcces(conn);
         cargarCombosIniciales();
         configurarListeners(); 
         cargarDatos();
        
    }
    private void actualizarEtiquetaEstado() {
    if (tglEstado.isSelected()) {
        tglEstado.setText("Activo");
        tglEstado.setBackground(new Color(0, 165, 0));
    } else {
        tglEstado.setText("Inactivo");
        tglEstado.setBackground(new Color(180, 0, 0));
    }
}
    public void mostrarImagen(String ruta, JLabel destino) {
    try {
        ImageIcon icono = new ImageIcon(ruta);
        Image img = icono.getImage().getScaledInstance(destino.getWidth(), destino.getHeight(), Image.SCALE_SMOOTH);
        destino.setIcon(new ImageIcon(img));
        destino.setText(null);
    } catch (Exception e) {
        destino.setIcon(null);
        destino.setText("Error imagen");
        System.err.println("Error al cargar imagen: " + e.getMessage());
    }
}
    private void cargarCombosIniciales() {
    ComboUtils.cargar(boxproveedor, proveedorDao::listar, "proveedores");
        ComboUtils.cargar(boxmarca, marcasDao::listar, "marcas");
        ComboUtils.cargar(boxcategoria, categoriaDao::listar, "categorías");
    }

    private void configurarListeners() {
        boxcategoria.addActionListener(e -> {
            Categoria catSel = (Categoria) boxcategoria.getSelectedItem();
            ComboUtils.cargar(boxtipo,
                () -> tipoDao.listar(catSel != null ? catSel.getId() : 0),
                "tipos");
        });

        boxtipo.addActionListener(e -> actualizarSubtipos());
    }


private void actualizarSubtipos() {
    Tipo tipoSel = (Tipo) boxtipo.getSelectedItem();
    if (tipoSel == null) {
    boxsubtipo.setModel(new DefaultComboBoxModel<>());
    return;
}
ComboUtils.cargar(
    boxsubtipo,
    () -> subtipoDao.listar(tipoSel.getIdTipo()),
    "subtipos de " + tipoSel.getNombre());
}
    private void cargarDatos() {
    boolean activo = "ACTIVO".equalsIgnoreCase(producto.getEstado().trim());
    tglEstado.setSelected(activo);
    actualizarEtiquetaEstado(); 

    txtnombre.setText(producto.getNombre());
    txtmodelo.setText(producto.getModelo());
    txtaño.setYear(producto.getAnio());
    txtprecio.setText(String.valueOf(producto.getPrecio()));
    txtstock.setText(String.valueOf(producto.getStock()));

    // ✅ Reemplazamos setSelectedItem por selección por ID
    ComboUtils.seleccionarPorId(boxproveedor, producto.getProveedor().getIDproveedor(), Proveedor::getIDproveedor);
    ComboUtils.seleccionarPorId(boxmarca, producto.getMarca().getIdMarca(), Marcas::getIdMarca);
    ComboUtils.seleccionarPorId(boxcategoria, producto.getCategoria().getId(), Categoria::getId);

    // ✅ Después de que se disparen los listeners y se carguen los tipos/subtipos
    SwingUtilities.invokeLater(() -> {
        ComboUtils.seleccionarPorId(boxtipo, producto.getTipo().getIdTipo(), Tipo::getIdTipo);
        ComboUtils.seleccionarPorId(boxsubtipo, producto.getSubtipo().getIdSubtipo(), Subtipo::getIdSubtipo);
    });

    if (producto.getImagen() != null && !producto.getImagen().isBlank()) {
        mostrarImagen(producto.getImagen(), lblFotoProducto);
        rutaImagen = producto.getImagen();
    }
}

      
     

   
   

  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblFotoProducto = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btnactualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btneditarImagen = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        boxmarca = new javax.swing.JComboBox<>();
        boxproveedor = new javax.swing.JComboBox<>();
        boxcategoria = new javax.swing.JComboBox<>();
        boxtipo = new javax.swing.JComboBox<>();
        boxsubtipo = new javax.swing.JComboBox<>();
        txtID = new javax.swing.JTextField();
        txtmodelo = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtprecio = new javax.swing.JTextField();
        txtstock = new javax.swing.JTextField();
        tglEstado = new javax.swing.JToggleButton();
        txtaño = new com.toedter.calendar.JYearChooser();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel10.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel10.setText("Proveedor");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel14.setText("?");
        jLabel14.setToolTipText("Algunos tipos no tienen subtipos asociados.");

        jLabel11.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel11.setText("Categoria");

        jLabel12.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel12.setText("Tipo de Categoria");

        jLabel7.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel7.setText("Stock");

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel2.setText("ID");

        jLabel8.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel8.setText("Estado");

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel3.setText("Nombre");

        jLabel5.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel5.setText("Precio");

        jLabel6.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel6.setText("Año");

        jLabel13.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel13.setText("Subtipo de Categoria");

        jSeparator3.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator3.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblFotoProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFotoProducto.setText("foto referencial");
        lblFotoProducto.setToolTipText("");
        lblFotoProducto.setPreferredSize(new java.awt.Dimension(150, 16));

        jLabel9.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel9.setText("Detalles");

        jLabel15.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel15.setText("Actualizar Producto");

        jSeparator4.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator4.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnactualizar.setBackground(new java.awt.Color(19, 25, 113));
        btnactualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnactualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnactualizar.setText("Actualizar ");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(213, 52, 32));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btneditarImagen.setText("Editar foto ");
        btneditarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarImagenActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel16.setText("Marca");

        boxmarca.setModel(new javax.swing.DefaultComboBoxModel<>());
        boxmarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxmarcaActionPerformed(evt);
            }
        });

        boxproveedor.setModel(new javax.swing.DefaultComboBoxModel<>());

        boxcategoria.setModel(new javax.swing.DefaultComboBoxModel<>());

        boxtipo.setModel(new javax.swing.DefaultComboBoxModel<>());

        boxsubtipo.setModel(new javax.swing.DefaultComboBoxModel<>());

        txtID.setText("\"El ID no es actualizable\"");
        txtID.setEnabled(false);

        txtmodelo.setText("jTextField1");

        txtnombre.setText("jTextField1");

        txtprecio.setText("jTextField1");

        txtstock.setText("jTextField1");

        tglEstado.setSelected(true);
        tglEstado.setText("\"Activo o Inactivo\"");
        tglEstado.setFocusPainted(false);
        tglEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglEstadoActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel17.setText("Modelo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addComponent(jSeparator4)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel9)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(boxcategoria, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                                .addComponent(boxproveedor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(boxtipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(boxsubtipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnactualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFotoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneditarImagen))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(txtaño, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(jLabel16)
                        .addComponent(txtprecio)
                        .addComponent(txtstock)
                        .addComponent(tglEstado))
                    .addComponent(boxmarca, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFotoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btneditarImagen))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tglEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(5, 5, 5)
                        .addComponent(boxproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxsubtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnactualizar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed

     try {
        /* ─ 1. Crear el nuevo objeto con los datos del formulario ─ */
        Producto actualizado = new Producto.Builder()
            .setIDProducto(producto.getIDProducto())   // conserva el ID original
            .setNombre(txtnombre.getText())
            .setModelo(txtmodelo.getText())
            .setAnio(txtaño.getYear())
            .setEstado(tglEstado.isSelected() ? "Activo" : "Inactivo")
            .setPrecio(Double.parseDouble(txtprecio.getText()))
            .setStock(Integer.parseInt(txtstock.getText()))
            .setImagen(rutaImagen)
            .setProveedor((Proveedor) boxproveedor.getSelectedItem())
            .setCategoria((Categoria) boxcategoria.getSelectedItem())
            .setTipo((Tipo) boxtipo.getSelectedItem())
            .setSubtipo((Subtipo) boxsubtipo.getSelectedItem())
            .setMarca((Marcas) boxmarca.getSelectedItem())
            .build();

        boolean ok = control.actualizar(actualizado);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Producto actualizado.");
            if (onSuccess != null) onSuccess.run();  // refresca la JTable en ProductosGui
            dispose();                               // cierra el diálogo
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo actualizar el producto.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this,
                "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
 
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btneditarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarImagenActionPerformed
    JFileChooser chooser = new JFileChooser();
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        rutaImagen = chooser.getSelectedFile().getAbsolutePath();
        mostrarImagen(rutaImagen, lblFotoProducto);
    }    
    }//GEN-LAST:event_btneditarImagenActionPerformed

    private void tglEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglEstadoActionPerformed
    actualizarEtiquetaEstado();
        // TODO add your handling code here:
    }//GEN-LAST:event_tglEstadoActionPerformed

    private void boxmarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxmarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxmarcaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        java.awt.EventQueue.invokeLater(() -> {
        /* ① owner de prueba, ② modal */
        AgregarProducto dlg = new AgregarProducto(new javax.swing.JFrame(), true);
        dlg.setLocationRelativeTo(null);
        dlg.setVisible(true);
    });//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<clasesprincipales.Categoria> boxcategoria;
    private javax.swing.JComboBox<clasesprincipales.Marcas> boxmarca;
    private javax.swing.JComboBox<clasesprincipales.Proveedor> boxproveedor;
    private javax.swing.JComboBox<clasesprincipales.Subtipo> boxsubtipo;
    private javax.swing.JComboBox<clasesprincipales.Tipo> boxtipo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btneditarImagen;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblFotoProducto;
    private javax.swing.JToggleButton tglEstado;
    private javax.swing.JTextField txtID;
    private com.toedter.calendar.JYearChooser txtaño;
    private javax.swing.JTextField txtmodelo;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtstock;
    // End of variables declaration//GEN-END:variables
}
