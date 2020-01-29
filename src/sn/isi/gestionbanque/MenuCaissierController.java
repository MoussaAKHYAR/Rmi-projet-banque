package sn.isi.gestionbanque;

import sn.isi.entities.Employe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tools.Outils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuCaissierController implements Initializable{

	@FXML
    private Label params;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		params.setText(Employe.label_connexion);

	}
	 @FXML
	    void deconnexion(ActionEvent event) throws IOException {
	    	String url="/sn/isi/gestionbanque/Acceuil.fxml";
		      Outils.load(event,url,"Acceuil");
	    }
	 @FXML
	    void operation(ActionEvent event) throws IOException {
	    	String url="/sn/isi/Operation.fxml";
	    	Outils.load(event,url,"Menu Operation");
	    }

	    @FXML
	    void virement(ActionEvent event) throws IOException {
	    	String url="/sn/isi/gestionbanque/Virement.fxml";
		      Outils.load(event,url,"Virement");
	    }

}
