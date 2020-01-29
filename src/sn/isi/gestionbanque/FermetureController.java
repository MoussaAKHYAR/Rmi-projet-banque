package sn.isi.gestionbanque;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sn.isi.controller.ICompte;

import sn.isi.entities.*;
import tools.Factory;
import tools.Outils;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ResourceBundle;

public class FermetureController implements Initializable {

    @FXML
    private JFXButton btn_fermer;

    @FXML
    private JFXButton btn_activer;

    @FXML
    private JFXButton btn_supprimer;

    @FXML
    private JFXButton btn_actualiser;
    @FXML
    private JFXComboBox<Compte> compte;


    @FXML
    private TableView<Compte> tablecomptecourant;

    @FXML
    private TableColumn<Comptecourant, String> numcolumn;

    @FXML
    private TableColumn<Compte, Date> datecolumn;

    @FXML
    private TableColumn<Compte, String> soldecolumn;

    @FXML
    private TableColumn<Compte, String> typecolumn;

    @FXML
    private TableColumn<Compte, String> etatcolumn;

    @FXML
    private TableColumn<Compte, Agence> agencecolumn;

    @FXML
    private TableColumn<Compte, Client> clientcolumn;

    @FXML
    private TableColumn<Compte, Employe> employecolumn;
    Factory factory = new Factory();


    @FXML
    void activer(ActionEvent event) {
    	String numCpt=compte.getSelectionModel().getSelectedItem().getNumCpt().toString();
		int ok= 0;
		try {
			ok = factory.getComptedao().activer(numCpt);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if(ok!=0)
	   {
		   tools.Notification.NotifSucces("Sucess", "compte activé ");
		   loadTable();
	   }else
	   {
		   tools.Notification.NotifError("error", "compte non activé");
	    }
    }

    @FXML
    void actualiser(ActionEvent event) {

    }
    private void loadTable(){
  		numcolumn.setCellValueFactory(new PropertyValueFactory<>("numCpt"));
  		datecolumn.setCellValueFactory(new PropertyValueFactory<>("dateouverture"));
  		soldecolumn.setCellValueFactory(new PropertyValueFactory<>("solde"));
  		typecolumn.setCellValueFactory(new PropertyValueFactory<>("type"));
  		etatcolumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
  		agencecolumn.setCellValueFactory(new PropertyValueFactory<>("idA"));
  		clientcolumn.setCellValueFactory(new PropertyValueFactory<>("idclient"));
  		employecolumn.setCellValueFactory(new PropertyValueFactory<>("idemp"));


  		ObservableList<Compte> compt = FXCollections.observableArrayList();
  		try {
			ip.liste().stream().forEach(k-> compt.add(k));
		}catch (Exception ex){
  			ex.printStackTrace();
		}
  		tablecomptecourant.setItems(compt);
      }
private ICompte ip= factory.getComptedao();
    @FXML
    void fermer(ActionEvent event)
    {
    	int ok = 0;
    	String numCpt=compte.getSelectionModel().getSelectedItem().getNumCpt().toString();
    	try {
    		ok = ip.fermer(numCpt);
		}catch (Exception ex){
    		ex.printStackTrace();
		}

	   if(ok!=0)
	   {
		   tools.Notification.NotifSucces("Sucess", "compte fermé avec succes ");
		   loadTable();
	   }else
	   {
		   tools.Notification.NotifError("error", "compte non fermé ");
	    }

    }

    @FXML
    void retour(ActionEvent event) {
		String url="/sn/isi/gestionbanque/menu.fxml";
		try {
			Outils.load(event, url,"Menu");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    private String numCpt;
    @FXML
    void supprimer(ActionEvent event) {
    	try {

		    int ok =ip.delete(numCpt);
		 		   if(ok!=0)
		 		   {
		 			   tools.Notification.NotifSucces("Sucess", "donnees supprimée!!!");
		 		   }else
		 		   {
		 			   tools.Notification.NotifError("error", "donnees  non  supprimée!!");
		 		    }

    	}catch (Exception e) {
 	    		e.printStackTrace();
 	    	}
	  loadTable();
    }

    @FXML
    void tableclick(MouseEvent event) {
    	Compte cc=tablecomptecourant.getSelectionModel().getSelectedItem();

    	numCpt=cc.getNumCpt();
    	compte.getSelectionModel().select(cc);


    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadTable();
		 ObservableList<Compte> com= FXCollections.observableArrayList();
	        Compte ca=new Compte();
	        ca.setNumCpt("");
	    	ca.setEtat("");
	        ca.setType(" faites votre choix");

	    	com.add(ca);
	    	try {
				ip.liste().stream()
						.forEach(e->{
							com.add(e);
						});
			}catch (Exception ex){
	    		ex.printStackTrace();
			}
	    compte.setItems(com);
	    compte.getSelectionModel().selectFirst();


	}

}
