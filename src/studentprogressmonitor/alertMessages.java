package studentprogressmonitor;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author CHRIS
 */
public class alertMessages {
    private Alert alert;

    /**
     *
     * @param message
     */
    public void errorMessage(String message){
            alert=new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait(); 
        }

    /**
     *
     * @param message
     */
    public void successMessage(String message){
            alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait(); 
        }
    
}
