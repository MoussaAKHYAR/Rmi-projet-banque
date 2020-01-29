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

public class MenuController implements Initializable{

    @FXML
    void ajoutagence(ActionEvent event) throws IOException {
    	String url="/sn/isi/gestionbanque/Agence.fxml";
    	Outils.load(event, url,"Agence");
    }

    @FXML
    void ajoutemploye(ActionEvent event) throws IOException {
    	String url="/sn/isi/gestionbanque/Employe.fxml";
    	Outils.load(event, url,"Employe");
    }

    @FXML
    void ajoutemployeur(ActionEvent event) throws IOException {
    	String url="/sn/isi/gestionbanque/Employeur.fxml";
	      Outils.load(event, url,"Employeur");
    }

    @FXML
    void ajoutclient(ActionEvent event) throws IOException {
    	String url="/sn/isi/gestionbanque/Client.fxml";
	      Outils.load(event, url,"Client");
    }

    @FXML
    void creationcompte(ActionEvent event) throws IOException {
    	String url="/sn/isi/gestionbanque/MenuCompte.fxml";
	      Outils.load(event, url,"Creation");
    }
    @FXML
    void deconnexion(ActionEvent event) throws IOException {
    	String url="/sn/isi/gestionbanque/Acceuil.fxml";
	      Outils.load(event, url,"Acceuil");
    }
    @FXML
    void operation(ActionEvent event) throws IOException {
    	String url="/sn/isi/gestionbanque/Menuoperation.fxml";
	      Outils.load(event, url,"Operation");
    }
    @FXML
    private Label params;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		params.setText(Employe.label_connexion);

	}

}
