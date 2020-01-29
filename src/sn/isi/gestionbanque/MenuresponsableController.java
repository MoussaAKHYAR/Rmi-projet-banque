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

public class MenuresponsableController implements Initializable{
	@FXML
    private Label params;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		params.setText(Employe.label_connexion);

	}
	 @FXML
	    void deconnexion(ActionEvent event) throws IOException {
	    	String url="/gestionbanque/Acceuil.fxml";
		      Outils.load(event, url,"Acceuil");
	    }
	 @FXML
	    void ajoutagence(ActionEvent event) throws IOException {
	    	String url="/sn/isi/gestionbanque/Agence.fxml";
		      Outils.load(event, url,"Agence");
	    }

	 @FXML
	    void virement(ActionEvent event) throws IOException {
	    	String url="/sn/isi/gestionbanque/Virement.fxml";
		      Outils.load(event, url,"Virement");
	    }
	    @FXML
	    void ajoutclient(ActionEvent event) throws IOException {
	    	String url="/sn/isi/gestionbanque/Client.fxml";
		      Outils.load(event, url,"Client");
	    }

	    @FXML
	    void fermercompte(ActionEvent event) throws IOException {
	    	String url="/sn/isi/gestionbanque/fermeturecompte.fxml";
		      Outils.load(event, url,"fermeture compte");
	    }

	    @FXML
	    void creationcompte(ActionEvent event) throws IOException {
	    	String url="/sn/isi/gestionbanque/MenuCompte.fxml";
		      Outils.load(event, url,"Menu Compte");
	    }
}
