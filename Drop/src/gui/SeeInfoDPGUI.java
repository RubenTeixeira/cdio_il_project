package gui;

import controller.SeeInfoDPController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SeeInfoDPGUI {

    private static JFrame fatherFrame;
    private static SeeInfoDPController seeInfoDPController;
    private static String url;
    /* Create a JFrame with a JButton and a JFXPanel containing the WebView. */

    public static void initAndShowGUI(
            JFrame fatherFrame,
            SeeInfoDPController seeInfoDPController)
    {

        SeeInfoDPGUI.fatherFrame = fatherFrame;
        SeeInfoDPGUI.seeInfoDPController = seeInfoDPController;

        JFrame frame = new JFrame("Info DropPoint: " + SeeInfoDPGUI.seeInfoDPController.getDropPointName());
        SeeInfoDPGUI.fatherFrame.setVisible(false);
        frame.setLayout(new BorderLayout());

        String info = seeInfoDPController.getDropPointInfo();

        JTextArea textArea = new JTextArea(info);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(300, 400));

        String coordenadas = SeeInfoDPGUI.seeInfoDPController.getDropPointCoor();
        String[] temp = coordenadas.split(";");
        SeeInfoDPGUI.url = "https://www.google.pt/maps/@" + temp[0] + "," + temp[1] + "z";

        final JFXPanel fxPanel = new JFXPanel();

        frame.add(scrollPane, BorderLayout.WEST);
        frame.add(fxPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        //fxPanel.setSize(new Dimension(400, 600));
        //fxPanel.setLocation(new Point(0, 27));
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                SeeInfoDPGUI.fatherFrame.setVisible(true);
                frame.setVisible(false);
                Thread.currentThread().stop();
            }
        });
        Platform.runLater(new Runnable() { // this will run initFX as JavaFX-Thread
            @Override
            public void run()
            {
                initFX(fxPanel, url);
            }
        });
    }

    /* Creates a WebView and fires up google.com */
    private static void initFX(final JFXPanel fxPanel, String url)
    {
        Group group = new Group();
        Scene scene = new Scene(group);
        fxPanel.setScene(scene);

        WebView webView = new WebView();

        group.getChildren().add(webView);
        webView.setMinSize(500, 600);
        webView.setMaxSize(500, 600);

        // Obtain the webEngine to navigate
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url);
    }

}
