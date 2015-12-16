package gui;

import controller.ExtendTokenController;
import controller.SeeInfoDPController;
import domain.DropPoint;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author 1140864
 */
public class DropGUI extends javax.swing.JFrame {

    private DropPoint dropChosen;
    private final SeeInfoDPController seeInfoDPController;
    private final ExtendTokenController extendTokenController;

    private DefaultListModel DropPointListModel;

    /**
     * Creates new form DropGUI
     */
    public DropGUI() {

        seeInfoDPController = new SeeInfoDPController();
        extendTokenController = new ExtendTokenController();
        initComponents();

        this.DropPointListModel = new DefaultListModel();
        this.listDropPoints.setModel(DropPointListModel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(btnInfo);

        run();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void run() {
        List<DropPoint> listDropPoint = seeInfoDPController.listDropPoints();

        for (DropPoint drop : listDropPoint) {
            this.DropPointListModel.addElement(drop);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listDropPoints = new javax.swing.JList();
        btnComprarServico = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();
        extensaoTokenBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Group Epsilon - Client Application");
        setMinimumSize(new java.awt.Dimension(670, 360));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Lista de DropPoints");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(240, 10, 190, 36);

        listDropPoints.setMaximumSize(new java.awt.Dimension(500, 500));
        listDropPoints.setMinimumSize(new java.awt.Dimension(500, 500));
        listDropPoints.setPreferredSize(new java.awt.Dimension(500, 500));
        jScrollPane3.setViewportView(listDropPoints);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(80, 50, 510, 160);

        btnComprarServico.setText("Comprar Serviço");
        btnComprarServico.setPreferredSize(new java.awt.Dimension(160, 23));
        btnComprarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarServicoActionPerformed(evt);
            }
        });
        getContentPane().add(btnComprarServico);
        btnComprarServico.setBounds(260, 220, 150, 30);

        btnInfo.setText("Ver informações");
        btnInfo.setMinimumSize(new java.awt.Dimension(160, 23));
        btnInfo.setPreferredSize(new java.awt.Dimension(170, 23));
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });
        getContentPane().add(btnInfo);
        btnInfo.setBounds(100, 220, 150, 30);

        extensaoTokenBTN.setText("Estender Token");
        extensaoTokenBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extensaoTokenBTNActionPerformed(evt);
            }
        });
        getContentPane().add(extensaoTokenBTN);
        extensaoTokenBTN.setBounds(420, 220, 150, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnComprarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarServicoActionPerformed
        dropChosen = (DropPoint) listDropPoints.getSelectedValue();
        if (dropChosen != null) {
            seeInfoDPController.selectDropPoint(dropChosen);
            new ComprarServicoGUI(this, dropChosen);
        } else {
            JOptionPane.showMessageDialog(this, "Tem de selecionar um DropPoint para poder comprar serviço", "Erro ao selecionar", JOptionPane.INFORMATION_MESSAGE, null);
        }
    }//GEN-LAST:event_btnComprarServicoActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed

        dropChosen = (DropPoint) listDropPoints.getSelectedValue();
        if (dropChosen != null) {
            seeInfoDPController.selectDropPoint(dropChosen);
            SeeInfoDPGUI.initAndShowGUI(this, seeInfoDPController);
        } else {
            JOptionPane.showMessageDialog(this, "Tem de selecionar um DropPoint para poder ver a sua informação", "Erro ao selecionar", JOptionPane.INFORMATION_MESSAGE, null);
        }
        /*
         String info = m_CSDPController.getInfoDropPoint(dropChoosed);
        
         JTextArea textArea = new JTextArea(info);
         textArea.setEditable(false);
         JScrollPane scrollPane = new JScrollPane(textArea);
         textArea.setLineWrap(true);
         textArea.setWrapStyleWord(true);
         scrollPane.setPreferredSize(new Dimension(450, 450));

         Object[] opcao = {"Sim", "Não"};

         if(dropChoosed != null) {
         int resposta = JOptionPane.showOptionDialog(
         this,
         scrollPane,
         "Deseja ver o mapa de " + dropChoosed + "?",
         0,
         JOptionPane.QUESTION_MESSAGE,
         null,
         opcao,
         opcao[1]);

         if(resposta == 0) {
         String coordenadas = m_CSDPController.getCoordenadasByDropPoint(dropChoosed);
         String[] temp = coordenadas.split(";");
         openWebpage(temp[0],temp[1]);
         }
         } else {
         JOptionPane.showMessageDialog(this, "Tem de selecionar um DropPoint para poder ver a sua informação", "Erro ao selecionar", JOptionPane.INFORMATION_MESSAGE, null);
         }*/
    }//GEN-LAST:event_btnInfoActionPerformed

    private void extensaoTokenBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extensaoTokenBTNActionPerformed
            new ExtendTokenGUI(this);
    }//GEN-LAST:event_extensaoTokenBTNActionPerformed
    /*
    public static void openWebpage(String latitude, String longitude) {
         try {
         Desktop.getDesktop().browse(new URL("https://www.google.pt/maps/@"+latitude+","+longitude+"z").toURI());
         } catch (Exception e) {
         e.printStackTrace();
         }
         
    }
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprarServico;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton extensaoTokenBTN;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList listDropPoints;
    // End of variables declaration//GEN-END:variables
}
