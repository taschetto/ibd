package gui;

import btree.BTree;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class BTreeSimUI extends javax.swing.JFrame {

  /**
   * Creates new form BTreeSimUI
   */
  public BTreeSimUI() {
    initComponents();
    this.setTitle("BTree Simulator");
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    buttonAdd = new javax.swing.JButton();
    buttonView = new javax.swing.JButton();
    this.integerFieldFormatter = NumberFormat.getIntegerInstance();
    this.integerFieldFormatter.setMaximumFractionDigits(0);
    this.integerFieldFormatter.setGroupingUsed(false);
    fieldValue = new javax.swing.JFormattedTextField(this.integerFieldFormatter);
    this.fieldValue.setColumns(5);
    buttonRemove = new javax.swing.JButton();
    buttonAddRange = new javax.swing.JButton();
    buttonAddRandom = new javax.swing.JButton();
    buttonClear = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    buttonAdd.setText("Add");
    buttonAdd.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonAddActionPerformed(evt);
      }
    });

    buttonView.setText("View");
    buttonView.setToolTipText("");
    buttonView.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonViewActionPerformed(evt);
      }
    });

    buttonRemove.setText("Remove");
    buttonRemove.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonRemoveActionPerformed(evt);
      }
    });

    buttonAddRange.setText("Add Range");
    buttonAddRange.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonAddRangeActionPerformed(evt);
      }
    });

    buttonAddRandom.setText("Add Random");
    buttonAddRandom.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonAddRandomActionPerformed(evt);
      }
    });

    buttonClear.setText("Clear");
    buttonClear.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonClearActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(buttonView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(fieldValue, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonAdd)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonAddRange)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonAddRandom)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonRemove))
          .addComponent(buttonClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(fieldValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(buttonAdd)
          .addComponent(buttonRemove)
          .addComponent(buttonAddRange)
          .addComponent(buttonAddRandom))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(buttonView)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(buttonClear)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
    this.tree.insert(Integer.parseInt(this.fieldValue.getText()));
    this.fieldValue.requestFocus();
  }//GEN-LAST:event_buttonAddActionPerformed

  private void buttonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewActionPerformed
    try {
      Desktop.getDesktop().open(new File(this.tree.toGraphViz()));
      this.fieldValue.requestFocus();
    } catch (IOException ex) {
      Logger.getLogger(BTreeSimUI.class.getName()).log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_buttonViewActionPerformed

  private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed
    this.tree.remove(Integer.parseInt(this.fieldValue.getText()));
    this.fieldValue.requestFocus();
  }//GEN-LAST:event_buttonRemoveActionPerformed

  private void buttonAddRangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddRangeActionPerformed
    int min = Integer.parseInt(JOptionPane.showInputDialog("What is the range start?"));
    int max = Integer.parseInt(JOptionPane.showInputDialog("What is the range end?"));
    
    for (int i = min; i <= max; i++)
      this.tree.insert(i);
  }//GEN-LAST:event_buttonAddRangeActionPerformed

  private void buttonAddRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddRandomActionPerformed
    int count = Integer.parseInt(JOptionPane.showInputDialog("How many random numbers do you want do insert?"));
    int min = Integer.parseInt(JOptionPane.showInputDialog("What is the random range start?"));
    int max = Integer.parseInt(JOptionPane.showInputDialog("What is the random range end?"));
    
    Random rand = new Random();
    for (int i = 0; i < count; i++)
      this.tree.insert(rand.nextInt((max - min) + 1) + min);
  }//GEN-LAST:event_buttonAddRandomActionPerformed

  private void buttonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearActionPerformed
    int order = Integer.parseInt(JOptionPane.showInputDialog("What is the Btree order?"));
    this.tree = new IntegerBTree(order);
  }//GEN-LAST:event_buttonClearActionPerformed

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
      java.util.logging.Logger.getLogger(BTreeSimUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(BTreeSimUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(BTreeSimUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(BTreeSimUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
        //</editor-fold>
    
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new BTreeSimUI().setVisible(true);
      }
    });
  }
  
  private IntegerBTree tree = new IntegerBTree(4);
  private NumberFormat integerFieldFormatter;

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonAdd;
  private javax.swing.JButton buttonAddRandom;
  private javax.swing.JButton buttonAddRange;
  private javax.swing.JButton buttonClear;
  private javax.swing.JButton buttonRemove;
  private javax.swing.JButton buttonView;
  private javax.swing.JFormattedTextField fieldValue;
  // End of variables declaration//GEN-END:variables
}

class IntegerBTree extends BTree<Integer, Integer> {

  public IntegerBTree(int order) {
    super(order);
  }
  
  public void insert(int key) {
      this.insert(key, key);
  }

  public void remove(int key) {
      this.delete(key);
  }
}