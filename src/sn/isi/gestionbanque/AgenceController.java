package sn.isi.gestionbanque;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import sn.isi.entities.Agence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tools.Factory;
import tools.Outils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AgenceController implements Initializable{

    @FXML
    private JFXTextField txtagence;

    @FXML
    private JFXTextField txtadress;

    @FXML
    private JFXButton btn_valider;

    @FXML
    private JFXButton btn_modifier;

    @FXML
    private JFXButton btn_supprimer;

    @FXML
    private TableView<Agence> tableagence;

    @FXML
    private TableColumn<Agence, Integer> collumnid;

    @FXML
    private TableColumn<Agence, String> nomcolumn;

    @FXML
    private TableColumn<Agence, String> adressecolumn;

    @FXML
    private JFXButton btn_actualiser;

    Factory factory = new Factory();

    @FXML
    void actualiser(ActionEvent event) {
    	txtagence.setText("");
	    txtadress.setText("");
	 	btn_valider.setDisable(false);
    }

    @FXML
    void modifier(ActionEvent event) {
    	int ok = 0;
    	try
  	    {
    		Agence a=new Agence();
    		  a.setIdA(idA);
			  a.setNomAgence(txtagence.getText());
			  a.setAdress(txtadress.getText());

			  ok = factory.getAgencedao().update(a);

  	    if(ok!=0)
  	    {

  	   	Outils.showConfirmationMessage("INFO","serveur mis a jour");
  	 	loadTable();

  	    }else
    	  {
    		  Outils.showErrorMessage("erreur","veuillez ressayer");
    	  }
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
    	 String url="/sn/isi/gestionbanque/menu.fxml";
	      Outils.load(event, url,"Menu");
    }

    @FXML
    void supprimer(ActionEvent event) {
    	try {

		    int ok =0;
		    ok =factory.getAgencedao().delete(idA);
		 		   if(ok!=0)
		 		   {
		 			   tools.Notification.NotifSucces("Sucess", "donnees supprimées!!!");
		 		   }else
		 		   {
		 			   tools.Notification.NotifError("error", "donnees  non  supprimées!!");
		 		    }

    	}catch (Exception e) {
 	    		e.printStackTrace();
 	    	}
	  loadTable();
    }
private int idA;
//private IAgence ia = null;
    @FXML
    void tableclick(MouseEvent event) {

    	Agence a=tableagence.getSelectionModel().getSelectedItem();
    	idA=a.getIdA();
    	txtadress.setText(a.getAdress());
	    txtagence.setText(a.getNomAgence());
	    gestionButtunform();


    }

    private void gestionButtunform()
    {
    	btn_valider.setDisable(true);
    	//annuler_btn.setText("Actualiser");
    	btn_supprimer.setDisable(false);//activation
    	btn_modifier.setDisable(false);
    }
    @FXML
    void valider(ActionEvent event) {
    	if (txtadress.getText().isEmpty() || txtagence.getText().isEmpty() ) {
    		  tools.Notification.NotifError("error", "donnees  non  ajoutes!!!");
    	} else {
    		try {
    			  Agence a=new Agence();
    			  a.setNomAgence(txtagence.getText());
    			  a.setAdress(txtadress.getText());

    			   int ok= 0;
    			   ok = factory.getAgencedao().add(a);
    			   if(ok!=0)
    			   {
    				   tools.Notification.NotifSucces("Sucess", "donnees ajoutes!!!");
    				   loadTable();

    			   }else
    			   {
    				   tools.Notification.NotifError("error", "donnees  non  ajoutes!!!");

    			   }
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		}
    }
    private void loadTable(){
  		collumnid.setCellValueFactory(new PropertyValueFactory<>("idA"));
  		nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nomAgence"));
  		adressecolumn.setCellValueFactory(new PropertyValueFactory<>("adress"));

  		ObservableList<Agence> cli = FXCollections.observableArrayList();
		try {
			List<Agence> listAgences = factory.getAgencedao().liste();
			for (Agence agence : listAgences ) {

				cli.add(agence);
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
  	 	tableagence.setItems(cli);

      }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadTable();
	}

}
