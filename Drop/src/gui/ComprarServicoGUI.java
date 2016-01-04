package gui;

import controller.BuyDropPointServiceController;
import domain.DropPoint;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import persistence.OracleDb;
import ui.Main;

/**
 *
 * @author André
 */
public class ComprarServicoGUI extends JFrame {

    private JFrame parentFrame;
    private DropPoint dropChosen;
    private BuyDropPointServiceController controller;

    private DefaultComboBoxModel modelCMBTamanho;
    private DefaultComboBoxModel modelCMBTemperatura;

    public ComprarServicoGUI(JFrame parentFrame, DropPoint dp) throws SQLException {
        super("Group Epsilon - Buy Drop Point Service");
        this.parentFrame = parentFrame;
        this.dropChosen = dp;
        controller = new BuyDropPointServiceController(Main.CREDENTIALS_FILE);
        fecharJanela();
        initComponents();

        modelCMBTamanho = new DefaultComboBoxModel();
        modelCMBTemperatura = new DefaultComboBoxModel();
        cbTipoPrateleira.setModel(modelCMBTamanho);
        cbTipoTemperatura.setModel(modelCMBTemperatura);
        addElementsCBTamanho();
        addElementsCBTemperatura();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addElementsCBTemperatura() {
        List<String> infoTemp = controller.preferredTemperatureList();
        for (String it : infoTemp) {
            modelCMBTemperatura.addElement(it);
        }

    }

    private void addElementsCBTamanho() {
        List<String> infoTamanho = controller.preferredDimensionsList();
        for (String infoT : infoTamanho) {
            modelCMBTamanho.addElement(infoT);
        }

    }

    private void fecharJanela() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sair();
            }
        });
    }

    private void sair() {
        String[] options = new String[2];
        options[0] = "Sim";
        options[1] = "Não";
        int op = JOptionPane.showOptionDialog(ComprarServicoGUI.this, "Deseja cancelar a compra de DropPoint?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (op == 0) {
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbTipoPrateleira = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbTipoTemperatura = new javax.swing.JComboBox();
        seguinteBTN = new javax.swing.JButton();
        sairBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(360, 395));
        setMinimumSize(new java.awt.Dimension(360, 395));
        setPreferredSize(new java.awt.Dimension(360, 395));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Comprar Serviço");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(110, 10, 130, 40);

        jLabel2.setText("Tamanho da Prateleira:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 80, 130, 17);

        getContentPane().add(cbTipoPrateleira);
        cbTipoPrateleira.setBounds(30, 100, 290, 27);

        jLabel3.setText("Temperatura desejada:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 130, 130, 17);

        getContentPane().add(cbTipoTemperatura);
        cbTipoTemperatura.setBounds(30, 150, 290, 27);

        seguinteBTN.setText("Seguinte");
        seguinteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seguinteBTNActionPerformed(evt);
            }
        });
        getContentPane().add(seguinteBTN);
        seguinteBTN.setBounds(40, 220, 120, 29);

        sairBTN.setText("Sair");
        sairBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairBTNActionPerformed(evt);
            }
        });
        getContentPane().add(sairBTN);
        sairBTN.setBounds(190, 220, 130, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seguinteBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seguinteBTNActionPerformed
        if (cbTipoPrateleira.getSelectedItem() != null && cbTipoTemperatura.getSelectedItem() != null) {
            try {
                this.dispose();
                new InterfaceUtilizadorGUI(ComprarServicoGUI.this, dropChosen, cbTipoPrateleira.getSelectedIndex()+1, cbTipoTemperatura.getSelectedIndex()+1);
            } catch (SQLException ex) {
                Logger.getLogger(ComprarServicoGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(ComprarServicoGUI.this, "Tem campos por selecionar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_seguinteBTNActionPerformed

    private void sairBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairBTNActionPerformed
        sair();
    }//GEN-LAST:event_sairBTNActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cbTipoPrateleira;
    private javax.swing.JComboBox cbTipoTemperatura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton sairBTN;
    private javax.swing.JButton seguinteBTN;
    // End of variables declaration//GEN-END:variables

}
