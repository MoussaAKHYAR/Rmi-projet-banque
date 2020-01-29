package sn.isi.gestionbanque;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import sn.isi.entities.Agence;
import sn.isi.entities.Employe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tools.Outils;
import sn.isi.controller.IAgence;
import sn.isi.controller.IEmploye;
import tools.Factory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EmployeController implements Initializable{

    @FXML
    private JFXTextField txtnom;

    @FXML
    private JFXTextField txtprenom;

    @FXML
    private JFXTextField txttel;

    @FXML
    private JFXTextField txtsalaire;

    @FXML
    private JFXTextField txtlogin;

    @FXML
    private JFXButton btn_valider;

    @FXML
    private JFXButton btn_modifier;

    @FXML
    private JFXButton btn_supprimer;

    @FXML
    private TableView<Employe> tableemploye;

    @FXML
    private TableColumn<Employe, ?> idcolumn;

    @FXML
    private TableColumn<Employe, ?> nomcolumn;

    @FXML
    private TableColumn<Employe, String> prenomcolumn;
    @FXML
    private TableColumn<Employe, String> telcolumn;

    @FXML
    private TableColumn<Employe, String> salairecolumn;

    @FXML
    private TableColumn<Employe, String> typecolumn;

    @FXML
    private TableColumn<Employe, String> loginolumn;

    @FXML
    private TableColumn<Employe, String> passcolumn;

    @FXML
    private TableColumn<Employe, Agence> agencecolumn;

    @FXML
    private JFXButton btn_actualiser;

    @FXML
    private JFXTextField txtpassword;

    @FXML
    private JFXComboBox<Agence> combo;

    @FXML
    private JFXComboBox<String> type;

    Factory factory = new Factory();

    @FXML
    void actualiser(ActionEvent event) {
    	txtnom.setText("");
	    txtprenom.setText("");

	    txttel.setText("");
	    txtlogin.setText("");
	    txtpassword.setText("");
	 	btn_valider.setDisable(false);
	 	txtsalaire.setText("");;
	 	combo.getSelectionModel().selectFirst();
	 	type.getSelectionModel().selectFirst();
    }

    @FXML
    void modifier(ActionEvent event) {
    	int ok = 0;
    	try
  	    {
    		 Employe  e =new   Employe();
    		 e.setIdemp(idemp);
    		 e.setNom(txtnom.getText());
    		 e.setPrenom(txtprenom.getText());
    		 e.setTel(txttel.getText());
    		 e.setSalaire(txtsalaire.getText());
    		 e.setType(type.getValue());
    		 e.setLogin(txtlogin.getText());
    		 e.setPassword(txtpassword.getText());
    		 e.setIdA(combo.getValue());

			 ok = factory.getEmployedao().update(e);

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
	      Outils.load(event, url,"Menu");
    }

    @FXML
    void supprimer(ActionEvent event) {
    	try {

		    int ok = 0 ;
		    ok = factory.getEmployedao().delete(idemp);
		 		   if(ok!=0)
		 		   {
		 			  actualiser(event);
		 			  tools.Notification.NotifSucces("Sucess", "donnees supprim�!!!");
		 		   }else
		 		   {
		 			   tools.Notification.NotifError("error", "donnees  non  supprim�!!");
		 		    }

    	}catch (Exception e) {
 	    		e.printStackTrace();
 	    	}
	  loadTable();
    }
private int idemp;
    @FXML
    void tableclick(MouseEvent event) {

    	Employe em=tableemploye.getSelectionModel().getSelectedItem();
    	idemp=em.getIdemp();
	    txtnom.setText(em.getNom());
	    txtprenom.setText(em.getPrenom());
	    txttel.setText(em.getTel());
	    txtsalaire.setText(em.getSalaire());
	    type.getSelectionModel().select(em.getType());
	    txtlogin.setText(em.getLogin());
	    txtpassword.setText(em.getPassword());
	    combo.getSelectionModel().select(em.getIdA());

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
    	if (txtnom.getText().isEmpty() || txtprenom.getText().isEmpty() ||
    			txtlogin.getText().isEmpty() || txtpassword.getText().isEmpty()||
    			txtsalaire.getText().isEmpty() || txttel.getText().isEmpty() ) {
    		  	tools.Notification.NotifError("error", "donnees  non  ajoutes!!!");
    	} else {
    		try {
    			  Employe  e =new   Employe();

    			  e.setNom(txtnom.getText());
    			  e.setPrenom(txtprenom.getText());
    			  e.setTel(txttel.getText());
    			  e.setSalaire(txtsalaire.getText());
    			  e.setType(type.getSelectionModel().getSelectedItem());
    			  e.setLogin(txtlogin.getText());
    			  e.setPassword(txtpassword.getText());
    			 e.setIdA(combo.getSelectionModel().getSelectedItem());
    			   int ok = 0 ;
    			   ok = factory.getEmployedao().add(e);
    			   if(ok!=0)
    			   {
    				   tools.Notification.NotifSucces("Sucess", "donnees ajoutes!!!");
    				   loadTable();
    				   actualiser(event);

    			   }else
    			   {
    				   tools.Notification.NotifError("error", "donnees  non  ajoutes!!!");

    			   }
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		}
    }
    private IAgence ia = factory.getAgencedao();
    private IEmploye ie = factory.getEmployedao();
    private void loadCombo(){

    	ObservableList<Agence> agence = FXCollections.observableArrayList();
    	Agence em = new Agence();
    	em.setIdA(1);
    	em.setNomAgence("Faite votre choix");
        em.setAdress("..");

    	agence.add(em);
    	try {
			ia.liste().stream()
					.forEach(e->{
						agence.add(e);
					});
		}catch (Exception ex){
    		ex.printStackTrace();
		}

    	combo.setItems(agence);

    	combo.getSelectionModel().selectFirst();
    }
    private void loadTable(){
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("idemp"));
		nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomcolumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		telcolumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
		salairecolumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));
		typecolumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		loginolumn.setCellValueFactory(new PropertyValueFactory<>("login"));
		passcolumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		agencecolumn.setCellValueFactory(new PropertyValueFactory<>("idA"));

		ObservableList<Employe> cli = FXCollections.observableArrayList();
		try {
			ie.liste().stream().forEach(v-> cli.add(v));
		}catch (Exception ex){
			ex.printStackTrace();
		}

	 	tableemploye.setItems(cli);
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> s = FXCollections.observableArrayList();
    	s.addAll("faite votre choix","Admin","Responsable","Caissier");
    	type.setItems(s);
    	loadCombo();
     loadTable();
	}

}
