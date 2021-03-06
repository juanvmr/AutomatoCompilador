/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomatoSwing;

import FileManager.FileManager;

/**
 *
 * @author juanvmr
 */
public class ArquivoJInternalFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form AbrirArquivoJInternalFrame
     */
    public ArquivoJInternalFrame() {
        initComponents();
    }
    
    public void setComponentsText(String arquivoLabel, String OKJButton, String cancelarJButton){
        this.ArquivoLabel.setText(arquivoLabel);
        this.OKJButton.setText(OKJButton);
        this.cancelarJButton.setText(cancelarJButton);
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public String getContent(){
        return content;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ArquivoLabel = new javax.swing.JLabel();
        pathTextField = new javax.swing.JTextField();
        cancelarJButton = new javax.swing.JButton();
        OKJButton = new javax.swing.JButton();

        ArquivoLabel.setText("Digite a path do arquivo:");

        cancelarJButton.setText("Cancelar");
        cancelarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarJButtonActionPerformed(evt);
            }
        });

        OKJButton.setText("Abrir");
        OKJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKJButtonActionPerformed(evt);
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
                        .addComponent(ArquivoLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pathTextField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 226, Short.MAX_VALUE)
                        .addComponent(OKJButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelarJButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ArquivoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarJButton)
                    .addComponent(OKJButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OKJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKJButtonActionPerformed
        fileManager = null;
        if(OKJButton.getText().compareTo("Salvar") == 1){
            fileManager = new FileManager(pathTextField.getText(), false);
            if(!content.isEmpty()){
                fileManager.fileWrite(content);
            }
        }
        else if(OKJButton.getText().compareTo("Abrir") == 1){
            fileManager = new FileManager(pathTextField.getText(), true);
        }
    }//GEN-LAST:event_OKJButtonActionPerformed

    private void cancelarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarJButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarJButtonActionPerformed

    private FileManager fileManager;
    private String content;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ArquivoLabel;
    private javax.swing.JButton OKJButton;
    private javax.swing.JButton cancelarJButton;
    private javax.swing.JTextField pathTextField;
    // End of variables declaration//GEN-END:variables
}
