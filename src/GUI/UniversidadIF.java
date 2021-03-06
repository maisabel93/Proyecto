/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import util.Conecta;
import clases.Universidad;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import util.Valida;

/**
 *
 * @author PabloAntonio
 */
public class UniversidadIF extends javax.swing.JInternalFrame {
    DefaultTableModel model;
    Conecta cnx = new Conecta();
    Valida va = new Valida();
    Statement stm;
    Universidad univer = new Universidad();

    /**
     * Creates new form UniversidadIF
     */
    public UniversidadIF() {
        initComponents();
        cnx.Conecta();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void limpiar(){
        txtUniversidad.setText("");
        txtSiglas.setText("");
    }
    
    private void Deshabilitar() {
        txtUniversidad.setEnabled(false);
        txtSiglas.setEnabled(false);
    }
    
    public void Habilitar(){
        txtUniversidad.setEnabled(true);
        va.SoloLetras(txtUniversidad);
        txtUniversidad.requestFocus();
        txtSiglas.setEnabled(true);
        va.SoloLetras(txtSiglas);
    }
    
    private void BotonesInicio(){
        btnNuevo.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    
    private void BotonesNuevo(){
        btnNuevo.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }
    
    private void BotonesClick(){
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
    
    private void LlenarTabla() {
        int[] anchos = {20, 250, 80};
        cnx.Conecta();
        try{
            String [] titulos ={"ID","Universidad","Siglas"};
            String SQL = "Select * from universidad";            
            
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            String [] fila = new String[3];
            while(rs.next()){
                fila[0] = rs.getString("iduniversidad");
                fila[1] = rs.getString("nombreU");
                fila[2] = rs.getString("siglas");
                model.addRow(fila);
            }           
            tblUniversidad.setModel(model);
            for(int i = 0; i < tblUniversidad.getColumnCount(); i++) {
                tblUniversidad.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            
            DefaultTableCellRenderer centraCelda = new DefaultTableCellRenderer();
            centraCelda.setHorizontalAlignment(SwingConstants.CENTER);
            tblUniversidad.getColumnModel().getColumn(0).setHeaderRenderer(centraCelda);
            tblUniversidad.getColumnModel().getColumn(2).setHeaderRenderer(centraCelda);
            tblUniversidad.getColumnModel().getColumn(0).setCellRenderer(centraCelda);
            tblUniversidad.getColumnModel().getColumn(2).setCellRenderer(centraCelda);            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla Universidad: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private boolean validar(){
        boolean val;
        if(txtUniversidad.getText().trim().length()==0){ //Valida campo Nombre
            JOptionPane.showMessageDialog(this, "El campo de texto Universidad está vacío,por favor llenarlo");
            val = false;
        } else if(txtSiglas.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(this, "El campo de texto Siglas está vacío,por favor llenarlo");
            val = false;
        } else {
            val=true;
        }       
        return val;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUniversidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSiglas = new javax.swing.JTextField();
        pImagen = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUniversidad = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Catálogo de Universidad");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Universidad"));

        jLabel1.setText("Universidad");

        jLabel2.setText("Siglas");

        pImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pImagenLayout = new javax.swing.GroupLayout(pImagen);
        pImagen.setLayout(pImagenLayout);
        pImagenLayout.setHorizontalGroup(
            pImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pImagenLayout.setVerticalGroup(
            pImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel3.setText("Escoger Logotipo:");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSiglas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtSiglas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jButton1))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        tblUniversidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUniversidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUniversidadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUniversidad);
        tblUniversidad.getColumnModel().getColumn(0).setResizable(false);
        tblUniversidad.getColumnModel().getColumn(0).setPreferredWidth(15);
        tblUniversidad.getColumnModel().getColumn(1).setResizable(false);
        tblUniversidad.getColumnModel().getColumn(1).setPreferredWidth(320);
        tblUniversidad.getColumnModel().getColumn(2).setResizable(false);
        tblUniversidad.getColumnModel().getColumn(2).setPreferredWidth(30);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalir)
                    .addComponent(btnActualizar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Habilitar();
        limpiar();
        BotonesNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (validar()==true){        
        int i = JOptionPane.showConfirmDialog(null, "Desea Actualizar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){    
            cnx.Conecta();
            try{
                univer.setnombreU(txtUniversidad.getText());
                String SQL ="update universidad set nombreU=?, siglas=?"
                + "where iduniversidad=?";
                int fila = tblUniversidad.getSelectedRow();
                String dato = (String)tblUniversidad.getValueAt(fila, 0);
                PreparedStatement ps = cnx.conn.prepareStatement(SQL);
                ps.setString(1, txtUniversidad.getText().trim());
                ps.setString(2, txtSiglas.getText().trim());
                ps.setString(3, dato);

                int n = ps.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");               
                }
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {
                LlenarTabla();
                limpiar();
                Deshabilitar();
                BotonesInicio();
                cnx.Desconecta();
            }
        }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblUniversidad.getSelectedRow();        
        int i = JOptionPane.showConfirmDialog(null, "Desea Eliminar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            cnx.Conecta();
            try {
                String SQL = "delete from universidad where iduniversidad= " + tblUniversidad.getValueAt(fila, 0);
                stm = cnx.conn.createStatement();            
                int n = stm.executeUpdate(SQL);
                if(n>0){                
                    JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Eliminar: " + e.getMessage());
            } finally {
                limpiar();
                Deshabilitar();
                LlenarTabla();
                BotonesInicio();
                cnx.Desconecta();
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar()==true){
        int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){    
            cnx.Conecta();
            try {
                String SQL = "insert into universidad(nombreU,siglas) "
                + "values(?,?)";
                PreparedStatement ps = cnx.conn.prepareStatement(SQL);
                ps.setString(1, txtUniversidad.getText().trim());
                ps.setString(2, txtSiglas.getText().trim());
                int n = ps.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {
                LlenarTabla();
                limpiar();
                Deshabilitar();
                BotonesInicio();
                cnx.Desconecta();
            }
        }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            this.doDefaultCloseAction();
        }
    }//GEN-LAST:event_btnSalirActionPerformed
    
    private void tblUniversidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUniversidadMouseClicked
        if (evt.getButton()==1){
            int fila = tblUniversidad.getSelectedRow();
            cnx.Conecta();
            try{
                Habilitar();                               
                String SQL = "Select * from universidad where iduniversidad = " + tblUniversidad.getValueAt(fila, 0);
                stm = cnx.conn.createStatement();
                ResultSet rs = stm.executeQuery(SQL);
                
                rs.next();
                txtUniversidad.setText(rs.getString("nombreU"));
                txtSiglas.setText(rs.getString("siglas"));                
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error Universidad Mouse Cliked: " + e.getMessage());
            } finally {
                BotonesClick();
                cnx.Desconecta();
            }
        }
    }//GEN-LAST:event_tblUniversidadMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser buscar = new JFileChooser();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pImagen;
    private javax.swing.JTable tblUniversidad;
    private javax.swing.JTextField txtSiglas;
    private javax.swing.JTextField txtUniversidad;
    // End of variables declaration//GEN-END:variables
}
