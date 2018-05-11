package support;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import static javafx.scene.control.ButtonType.OK;
import manifest.*;

public class AlertMsg {

	public static String WARNING = "WARNING";
	public static String ERROR = "ERROR";
	public static String INFO = "INFO";
        public Alert alert;
	
	public String alertMsg(AlertType alertType, String title, String headerText, String contetText) {
		
		alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contetText);
                //alert.initOwner(Manifest.primaryStage);
                                
                alert.getButtonTypes().removeAll(ButtonType.OK);
                ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
                ButtonType cancelButton = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().addAll(okButton,cancelButton);
                Optional<ButtonType> result = alert.showAndWait();
                
                if(result.get() == okButton){
                    return okButton.getText();
                }else if(result.get() == cancelButton){
                    return cancelButton.getText();
                }else{
                    return cancelButton.getText();
                }
	}
        
}
