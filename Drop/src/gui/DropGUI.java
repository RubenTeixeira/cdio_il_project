package gui;

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

    private DropPoint dropChoosed;
    private final SeeInfoDPController seeInfoDPController;
    
    private DefaultListModel DropPointListModel;

    /**
     * Creates new form DropGUI
     */
    public DropGUI()
    {

        seeInfoDPController = new SeeInfoDPController();
        initComponents();

        this.DropPointListModel = new DefaultListModel();
        this.listDropPoints.setModel(DropPointListModel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(btnInfo);

        run();

        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void run()
    {
        List<DropPoint> listDropPoint = seeInfoDPController.listDropPoints();

        for (DropPoint drop : listDropPoint)
        {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Group Epsilon - Client Application");
        setMinimumSize(new java.awt.Dimension(490, 307));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Lista de DropPoints");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(170, 10, 190, 36);

        listDropPoints.setMaximumSize(new java.awt.Dimension(500, 500));
        listDropPoints.setMinimumSize(new java.awt.Dimension(500, 500));
        listDropPoints.setPreferredSize(new java.awt.Dimension(500, 500));
        jScrollPane3.setViewportView(listDropPoints);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(80, 50, 340, 147);

        btnComprarServico.setText("Comprar Serviço");
        btnComprarServico.setPreferredSize(new java.awt.Dimension(160, 23));
        btnComprarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarServicoActionPerformed(evt);
            }
        });
        getContentPane().add(btnComprarServico);
        btnComprarServico.setBounds(260, 220, 130, 23);

        btnInfo.setText("Ver informações");
        btnInfo.setMinimumSize(new java.awt.Dimension(160, 23));
        btnInfo.setPreferredSize(new java.awt.Dimension(170, 23));
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });
        getContentPane().add(btnInfo);
        btnInfo.setBounds(100, 220, 130, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnComprarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarServicoActionPerformed

    }//GEN-LAST:event_btnComprarServicoActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed

        dropChoosed = (DropPoint) listDropPoints.getSelectedValue();
        if (dropChoosed != null)
        {
            seeInfoDPController.selectDropPoint(dropChoosed);
            SeeInfoDPGUI.initAndShowGUI(this, seeInfoDPController);
        } else
        {
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
         }
    }//GEN-LAST:event_btnInfoActionPerformed

    public static void openWebpage(String latitude, String longitude) {
         try {
         Desktop.getDesktop().browse(new URL("https://www.google.pt/maps/@"+latitude+","+longitude+"z").toURI());
         } catch (Exception e) {
         e.printStackTrace();
         }
         */
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprarServico;
    private javax.swing.JButton btnInfo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList listDropPoints;
    // End of variables declaration//GEN-END:variables
}
