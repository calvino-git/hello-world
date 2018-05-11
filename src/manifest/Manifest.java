/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manifest;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author CALVINO
 */
public class Manifest extends Application {    
    public static Stage primaryStage = new Stage();
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("manifest.fxml"));
        Parent root = (Parent)loader.load();
        final manifestController controller;
        controller = loader.<manifestController>getController();
        stage.getIcons().add(new Image(new File("logo.ico").toURI().toString()));
        Scene scene = new Scene(root);
        stage.setTitle("MANIFEST XML");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
