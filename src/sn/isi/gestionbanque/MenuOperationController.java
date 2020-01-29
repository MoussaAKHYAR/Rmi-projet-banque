package sn.isi.gestionbanque;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tools.Outils;

import java.io.IOException;


public class MenuOperationController {

    @FXML
    void operation(ActionEvent event) throws IOException {
    	String url="/sn/isi/gestionbanque/Operation.fxml";
	      Outils.load(event, url,"Operation");
    }

    @FXML
    void virement(ActionEvent event) throws IOException {
    	String url="/sn/isi/gestionbanque/Virement.fxml";
	      Outils.load(event, url,"Virement");
    }

}
