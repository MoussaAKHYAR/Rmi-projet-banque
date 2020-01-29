package sn.isi.gestionbanque;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import sn.isi.entities.Agence;
import sn.isi.entities.Client;
import sn.isi.entities.Employeur;
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
import sn.isi.controller.IEmployeur;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;


public class ClientController implements Initializable{

    @FXML
    private JFXTextField txtnom;

    @FXML
    private JFXTextField txtprenom;

    @FXML
    private JFXTextField txtadress;

    @FXML
    private JFXTextField txtemail;

    @FXML
    private JFXTextField txttel;

    @FXML
    private JFXTextField txtprofession;

    @FXML
    private JFXButton btn_valider;

    @FXML
    private JFXButton btn_modifier;

    @FXML
    private JFXButton btn_supprimer;

    @FXML
    private TableView<Client> tableclient;

    @FXML
    private TableColumn<Client, Integer> idcolumn;

    @FXML
    private TableColumn<Client, String> nomcolumn;

    @FXML
    private TableColumn<Client, String> prenomcolumn;
    @FXML
    private JFXTextField txtsalaire;

    @FXML
    private JFXComboBox<Employeur> combo;
    @FXML
    private TableColumn<Client, String> adresscolumn;
    @FXML
    private TableColumn<Client, String>  salairecolumn;

    @FXML
    private TableColumn<Client, Employeur>  employeurcolumn;

    @FXML
    private TableColumn<Client, String> emailcolumn;

    @FXML
    private TableColumn<Client, String> telcolumn;

    @FXML
    private TableColumn<Client, String> professioncolumn;

    @FXML
    private JFXButton btn_actualiser;

    Factory factory = new Factory();

    @FXML
    void actualiser(ActionEvent event) {
    	txtnom.setText("");
	    txtprenom.setText("");
	    txtadress.setText("");
	    txttel.setText("");
	    txtemail.setText("");
	    txtprofession.setText("");
	 	btn_valider.setDisable(false);
	 	txtsalaire.setText("");;
	 	combo.getSelectionModel().selectFirst();
    }

    @FXML
    void modifier(ActionEvent event) {
    	int ok = 0;
    	try
  	    {
    		Client  co =new   Client();
    		co.setIdclient(id);
			  co.setNom(txtnom.getText());
			  co.setPrenom(txtprenom.getText());
			  co.setAdress(txtadress.getText());
			  co.setEmail(txtemail.getText());
			  co.setTelephone(txttel.getText());
			  co.setProfession(txtprofession.getText());
			  co.setSalaire(txtsalaire.getText());
			  co.setNumero(combo.getValue());

			  ok = factory.getClientdao().update(co);

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
    void retour(ActionEvent event) throws IOException {
    	 String url="/sn/isi/gestionbanque/menu.fxml";
	      Outils.load(event, url,"");
    }

    @FXML
    void supprimer(ActionEvent event) {
    	try {

		    int ok = 0;
		    ok = factory.getClientdao().delete(id);
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
private int id;
    @FXML
    void tableclick(MouseEvent event) {

    	Client cl=tableclient.getSelectionModel().getSelectedItem();
    	id=cl.getIdclient();
	    txtnom.setText(cl.getNom());
	    txtprenom.setText(cl.getPrenom());
	    txtadress.setText(cl.getAdress());
	    txttel.setText(cl.getTelephone());
	    txtemail.setText(cl.getEmail());
	    txtprofession.setText(cl.getProfession());
	    txtsalaire.setText(cl.getSalaire());
	    combo.getSelectionModel().select(cl.getNumero());

	    gestionButtunform();


    }
    @SuppressWarnings("unused")
	private void gestionButtuntable()
    {
    	btn_valider.setDisable(false);
    	//annuler_btn.setText("Annuler");
    	btn_supprimer.setDisable(true);//desactivation
         	btn_modifier.setDisable(true);
    }
    private void gestionButtunform()
    {
    	btn_valider.setDisable(true);
    	//annuler_btn.setText("Actualiser");
    	btn_supprimer.setDisable(false);//activation
    	btn_modifier.setDisable(false);
    }

   // private IClient ic= new ClientDB();
    private void loadTable(){
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("idclient"));
		nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomcolumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		adresscolumn.setCellValueFactory(new PropertyValueFactory<>("adress"));
		emailcolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		telcolumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		professioncolumn.setCellValueFactory(new PropertyValueFactory<>("profession"));
		salairecolumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));
		employeurcolumn.setCellValueFactory(new PropertyValueFactory<>("numero"));

		ObservableList<Client> clients = FXCollections.observableArrayList();
		try {
			List<Client> listAgences = factory.getClientdao().liste();
			for (Client client : listAgences ) {

				clients.add(client);
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		tableclient.setItems(clients);

	}
    @FXML
    void valider(ActionEvent event)
    {
    	if (txtnom.getText().isEmpty() || txtprenom.getText().isEmpty() ||
    			txtnom.getText().isEmpty() || txtprenom.getText().isEmpty() ) {
    		  	tools.Notification.NotifError("error", "donnees  non  ajoutes!!!");
    	} else {
    		try {
    			  Client  co = new   Client();
    			  co.setNom(txtnom.getText());
    			  co.setPrenom(txtprenom.getText());
    			  co.setAdress(txtadress.getText());
    			  co.setEmail(txtemail.getText());
    			  co.setTelephone(txttel.getText());
    			  co.setProfession(txtprofession.getText());
    			  co.setSalaire(txtsalaire.getText());
    			 co.setNumero(combo.getSelectionModel().getSelectedItem());
    			   int ok= 0;
    			   ok = factory.getClientdao().add(co);
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
    private IEmployeur im = factory.getEmployeurdao() ;
    private void loadCombo(){

    	ObservableList<Employeur> employe = FXCollections.observableArrayList();
    	Employeur em = new Employeur();
    	em.setNumero(1);
    	em.setNomemployeur("..");
        em.setAdresse("Faite votre choix");
        em.setRaisonsocial("..");

    	employe.add(em);
		try {
			im.liste().stream()
								.forEach(e->{
									employe.add(e);
								});
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		combo.setItems(employe);

    	combo.getSelectionModel().selectFirst();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadTable();
		loadCombo();

	}

}
