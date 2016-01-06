package gui;

import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SeeInfoDPGUI {

    Browser myBrowser;
    public static String lat;
    public static String lng;

    public SeeInfoDPGUI() {
    }
    
    public Browser newBrowser() {
        return new Browser();
    }

    class Browser extends Region {

        HBox toolbar;

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        public Browser() {
            webEngine.setJavaScriptEnabled(true);

            webEngine
                    .getLoadWorker()
                    .stateProperty()
                    .addListener(
                            (obs, oldValue, newValue) -> {
                                System.out.println(newValue);
                                if (newValue == State.SUCCEEDED) {
                                    System.out.println("finished loading");
                                    webEngine.executeScript("setCoor(" + lat + "," + lng + ");");
                                    // String html = (String) webEngine
                                    //.executeScript("document.documentElement.outerHTML");
                                    //System.out.println(html);
                                }
                            });

            webEngine.setOnError(new EventHandler<WebErrorEvent>() {

                @Override
                public void handle(WebErrorEvent event) {
                    System.out.println(event.getMessage());
                }
            });

            webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {

                @Override
                public void handle(WebEvent<String> event) {
                    System.out.println(event.getData());
                }
            });

            final URL urlGoogleMaps = getClass().getResource("google.html");
            webEngine.load(urlGoogleMaps.toExternalForm());
            getChildren().add(webView);

        }

    }

    private static void initAndShowGUI() {
        // This method is invoked on the EDT thread
        JFrame frame = new JFrame("DP Info!");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(600, 400);
        frame.setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
        });
    }

    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private static Scene createScene() {
        Group root = new Group();
        Scene scene = new Scene(root, Color.ALICEBLUE);
        SeeInfoDPGUI seeInfoDPGUI = new SeeInfoDPGUI();
        Browser browser = seeInfoDPGUI.newBrowser();
        root.getChildren().add(browser);

        return (scene);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
            }
        });
    }

}
