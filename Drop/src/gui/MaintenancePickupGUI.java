package gui;

import controller.MaintenancePickupController;
import domain.Cell;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.*;
import java.util.List;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author nuno
 */
public class MaintenancePickupGUI extends JFrame {

    private JList<Cell> jlCells;
    private JTextField txtToken;
    private JFileChooser fileChooser;
    private DefaultListModel<Cell> cellModel;
    private MaintenancePickupController controller;
    private JButton btnToken, btnOpen, btnClose, btnUplaod, btnExit;

    public MaintenancePickupGUI() {
        super("Recolhar Encomendas!");
        controller = new MaintenancePickupController("settings/settings.txt");
    }

    private void addComponentsToPane(final Container pane) {
        final JPanel compsToFrame = new JPanel();
        compsToFrame.setLayout(new BorderLayout(5, 5));
        compsToFrame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel dataHeader = new JPanel();
        dataHeader.setLayout(new GridLayout(2, 2, 10, 10));

        dataHeader.add(new JLabel("Insira Token:"));
        txtToken = new JTextField("", 20);
        dataHeader.add(txtToken);

        dataHeader.add(new JLabel());
        dataHeader.add(createSubmitTokenBtn());

        JPanel jPanel = new JPanel(new BorderLayout(10, 10));
        jPanel.add(createBtnExit(), BorderLayout.EAST);

        compsToFrame.add(dataHeader, BorderLayout.NORTH);
        compsToFrame.add(createCellPane(), BorderLayout.CENTER);
        compsToFrame.add(jPanel, BorderLayout.SOUTH);

        pane.add(compsToFrame, BorderLayout.NORTH);

    }

    private JButton createSubmitTokenBtn() {
        btnToken = new JButton("Submeter!");
        btnToken.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                List<Cell> cellList = controller.getListOfCellsWithDeliveriesOutOfDate(txtToken.getText().trim());
                if (cellList != null) {
                    cellList.stream().forEach((c) -> cellModel.addElement(c));
                } else {
                    JOptionPane.showMessageDialog(
                            MaintenancePickupGUI.this,
                            "Não existe encomendas expiradas.",
                            "Informação",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        return btnToken;
    }

    private JPanel createCellPane() {
        cellModel = new DefaultListModel();
        jlCells = new JList(cellModel);
        jlCells.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlCells.setLayoutOrientation(JList.VERTICAL);
        jlCells.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(jlCells);
        listScroller.setPreferredSize(new Dimension(250, 80));

        JPanel jPanel = new JPanel(new BorderLayout(10, 10));
        JPanel jPanelBtn = new JPanel(new GridLayout(1, 3, 5, 5));

        jPanelBtn.add(createBtnOpenCell());
        jPanelBtn.add(createBtnCloseCell());
        jPanelBtn.add(createBtnUplaodCellImage());

        jPanel.add(jlCells, BorderLayout.NORTH);
        jPanel.add(jPanelBtn, BorderLayout.CENTER);

        return jPanel;
    }
    
    private JButton createBtnOpenCell() {
        btnOpen = new JButton("Abrir");

        btnOpen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.selectCell(jlCells.getSelectedValue());
                controller.openCell();
            }
        });

        return btnOpen;
    }

    private JButton createBtnCloseCell() {
        btnClose = new JButton("Fechar");

        btnClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.selectCell(jlCells.getSelectedValue());
                controller.closeCell();
            }
        });

        return btnClose;
    }

    private JButton createBtnUplaodCellImage() {
        btnUplaod = new JButton("Upload");

        btnUplaod.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setApproveButtonText("Uplaod");
                FileFilter jpegFilter = new ExtensionFileFilter(null, new String[]{"JPG", "JPEG"});
                fileChooser.addChoosableFileFilter(jpegFilter);
                int reply = fileChooser.showOpenDialog(MaintenancePickupGUI.this);

                if (reply == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    InputStream inStream = null;
                    OutputStream outStream = null;

                    try {
                        File source = new File(file.getAbsolutePath());
                        File dest = new File(System.getProperty("user.dir"), file.getName());
                        inStream = new FileInputStream(source);
                        outStream = new FileOutputStream(dest);
                        System.out.println(dest.getAbsolutePath());
                        byte[] buffer = new byte[1024];

                        int length;
                        while ((length = inStream.read(buffer)) > 0) {
                            outStream.write(buffer, 0, length);
                        }

                        if (inStream != null) {
                            inStream.close();
                        }
                        if (outStream != null) {
                            outStream.close();
                        }
                        controller.savePhoto(dest.getAbsolutePath());
                        JOptionPane.showMessageDialog(
                                MaintenancePickupGUI.this,
                                "Gravado com sucesso.",
                                "Uplaod",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(
                                MaintenancePickupGUI.this,
                                "Impossível gravar: "
                                + file.getPath() + " !",
                                "Uplaod",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        return btnUplaod;
    }

    private JButton createBtnExit() {
        btnExit = new JButton("Sair");

        btnExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return btnExit;
    }

    class ExtensionFileFilter extends FileFilter {

        private String description;

        private String extensions[];

        public ExtensionFileFilter(String description, String extension) {
            this(description, new String[]{extension});
        }

        public ExtensionFileFilter(String description, String extensions[]) {
            if (description == null) {
                this.description = extensions[0] + "{" + extensions.length + "} ";
            } else {
                this.description = description;
            }
            this.extensions = (String[]) extensions.clone();
            toLower(this.extensions);
        }

        private void toLower(String array[]) {
            for (int i = 0, n = array.length; i < n; i++) {
                array[i] = array[i].toLowerCase();
            }
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            } else {
                String path = file.getAbsolutePath().toLowerCase();
                for (int i = 0, n = extensions.length; i < n; i++) {
                    String extension = extensions[i];
                    if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void createAndShowGUI() {
        //Create and set up the window.
        MaintenancePickupGUI frame = new MaintenancePickupGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
