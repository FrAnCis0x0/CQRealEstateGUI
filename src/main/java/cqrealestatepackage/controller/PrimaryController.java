package cqrealestatepackage.controller;

import cqrealestatepackage.App;
import cqrealestatepackage.model.Buyer;
import cqrealestatepackage.model.Clients;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class PrimaryController implements Initializable{
    private Clients buyer;
    @FXML
    private Button primaryButton;
    @FXML
    private void switchToSecondary() throws IOException {
//        System.out.println(buyer.toString());
        App.setRoot("view/secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        buyer = new Buyer("Francis", "this street", "04131651212");
    }
}
