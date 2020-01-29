package sn.isi.gestionbanque;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tools.Outils;

import java.io.IOException;

public class MenuCompteController  {

    @FXML
    private Label params;

    @FXML
    void bloque(ActionEvent event) throws IOException {
    	String url="/sn/isi/gestionbanque/CompteBloque.fxml";
	      Outils.load(event,url,"Compte Bloque");
    }

    @FXML
    void courant(ActionEvent event) throws IOException {
    	 String url="/sn/isi/gestionbanque/CompteCourant.fxml";
	      Outils.load(event, url,"Compte Courant");
    }

    @FXML
    void deconnexion(ActionEvent event) throws IOException {
    	String url="/sn/isi/gestionbanque/menu.fxml";
	      Outils.load(event, url,"Accueil");
    }


}
