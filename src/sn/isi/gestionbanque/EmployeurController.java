package sn.isi.gestionbanque;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sn.isi.controller.IEmployeur;
import sn.isi.entities.Employeur;
import tools.Factory;
import tools.Outils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeurController  implements Initializable{

    @FXML
    private JFXTextField txtnom;

    @FXML
    private JFXTextField txtadress;

    @FXML
    private JFXTextField txtraison;

    @FXML
    private JFXButton btn_valider;

    @FXML
    private JFXButton btn_modifier;

    @FXML
    private JFXButton btn_supprimer;

    @FXML
    private TableView<Employeur> tableemployeur;

    @FXML
    private TableColumn<Employeur, Integer> numerocolumn;

    @FXML
    private TableColumn<Employeur, String> nomcolumn;

    @FXML
    private TableColumn<Employeur, String> adressecolumn;

    @FXML
    private TableColumn<Employeur, String> raisoncolumn;

    @FXML
    private JFXButton btn_actualiser;

    Factory factory = new Factory();

    @FXML
    void actualiser(ActionEvent event) {
    	txtnom.setText("");
	    txtraison.setText("");
	    txtadress.setText("");
	 	btn_valider.setDisable(false);
    }

    @FXML
    void modifier(ActionEvent event) {
    	int ok = 0 ;
    	try
  	    {
			  Employeur  co =new   Employeur();
			  co.setNumero(numero);
			  co.setAdresse(txtadress.getText());
			  co.setNomemployeur(txtnom.getText());
			  co.setRaisonsocial(txtraison.getText());

			  ok = factory.getEmployeurdao().update(co);

  	    if(ok!=0)
  	    {
  	   		tools.Notification.NotifSucces("INFO","serveur mis a jour");
  			 loadTable();
  	    }else
    	  {
    		  tools.Notification.NotifError("erreur","veuillez ressayer");
    	  }
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
    }

    @FXML
    void retour(ActionEvent event) throws IOException
    {
    	 String url="/sn/isi/gestionbanque/menu.fxml";
	      Outils.load(event, url,"Menu");
    }

    @FXML
    void supprimer(ActionEvent event) {
    	int ok = 0;
    	try {
		    ok =iem.delete(numero);
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
private int numero;
    @FXML
    void tableclick(MouseEvent event)
    {
    	Employeur cl=tableemployeur.getSelectionModel().getSelectedItem();
    	numero=cl.getNumero();
	    txtnom.setText(cl.getNomemployeur());
	    txtadress.setText(cl.getAdresse());
	    txtraison.setText(cl.getRaisonsocial());

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
    void valider(ActionEvent event)
    {
    	if (txtnom.getText().isEmpty() || txtadress.getText().isEmpty() || txtraison.getText().isEmpty() ) {
    		  tools.Notification.NotifError("error", "donnees  non  ajoutes!!!");
    	} else {
    		try {
    			  Employeur  co =new   Employeur();
    			  co.setAdresse(txtadress.getText());
    			  co.setNomemployeur(txtnom.getText());
    			  co.setRaisonsocial(txtraison.getText());
    			   int ok=iem.add(co);
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
    private IEmployeur iem= factory.getEmployeurdao();
    private void loadTable(){

    	numerocolumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
		nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nomemployeur"));
		adressecolumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		raisoncolumn.setCellValueFactory(new PropertyValueFactory<>("raisonsocial"));

		ObservableList<Employeur> em = FXCollections.observableArrayList();
		try {
			iem.liste().stream().forEach(v-> em.add(v));
		}catch (Exception ex){
			ex.printStackTrace();
		}

        tableemployeur.setItems(em);

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	loadTable();
	}

}
