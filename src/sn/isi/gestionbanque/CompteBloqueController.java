package sn.isi.gestionbanque;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import sn.isi.controller.IAgence;
import sn.isi.controller.IClient;
import sn.isi.controller.ICompte;
import sn.isi.controller.IEmploye;
import sn.isi.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tools.Factory;
import tools.Outils;


import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ResourceBundle;

public class CompteBloqueController implements Initializable {


    @FXML
    private JFXDatePicker date1;

    @FXML
    private JFXTextField txtnumero;

    @FXML
    private JFXTextField txtsolde;

    @FXML
    private JFXTextField txttype;

    @FXML
    private JFXButton btn_valider;

    @FXML
    private JFXButton btn_modifier;

    @FXML
    private JFXButton btn_supprimer;

    @FXML
    private TableView<Compte> tablecomptecourant;

    @FXML
    private TableColumn<Compte, String> numcolumn;

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



    @FXML
    private JFXButton btn_actualiser;

    @FXML
    private JFXComboBox<Agence> comboagence;


    @FXML
    private DatePicker date;

    @FXML
    private JFXTextField txtetat;

    @FXML
    private JFXComboBox<Client> comboclient;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXComboBox<Employe> comboemploye;

    @FXML
    private JFXTextField txtagio;

    Factory factory = new Factory();

    @FXML
    void actualiser(ActionEvent event) {
    	txtsolde.setText("");
    	txtetat.setText("");
	    type.getSelectionModel().selectFirst();
	    comboagence.getSelectionModel().selectFirst();
	    comboclient.getSelectionModel().selectFirst();
		comboemploye.getSelectionModel().selectFirst();

	 	btn_valider.setDisable(false);
    }

    @FXML
    void modifier(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {
		String url="/sn/isi/gestionbanque/menu.fxml";
		try {
			Outils.load(event, url,"");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

    @FXML
    void supprimer(ActionEvent event) {
		int ok = 0;
    	try {

		    ok = factory.getComptedao().delete(numCpt);
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
private String numCpt;
    @FXML
    void tableclick(MouseEvent event)
    {
    	Compte cc=tablecomptecourant.getSelectionModel().getSelectedItem();
    	//Compteepargne cc=(Compteepargne) tablecomptecourant.getSelectionModel().getSelectedItem();
    	numCpt=cc.getNumCpt();
    	txtetat.setText(cc.getEtat());
    	//date.setText(cc.getDateouverture().toString());
    	//txtagio.setText(cc.getMontantagio());
    	//txtsolde.setText(Integer.parseInt(cc.getSolde());
    	comboagence.getSelectionModel().select(cc.getIdA());
    	comboclient.getSelectionModel().select(cc.getIdclient());
    	comboemploye.getSelectionModel().select(cc.getIdemp());

	    gestionButtunform();


    }

    private void gestionButtunform()
    {
    	btn_valider.setDisable(true);
    	//annuler_btn.setText("Actualiser");
    	btn_supprimer.setDisable(false);//activation
    	btn_modifier.setDisable(false);
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
			ic1.liste().stream().forEach(k-> compt.add(k));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		tablecomptecourant.setItems(compt);

      }
private ICompte ic1 = factory.getComptedao();
//private ComptesDB ici=new ComptesDB();
private int ok;
    @FXML
    void valider(ActionEvent event)
    {
    	if (txtetat.getText().isEmpty() ||
    			txtnumero.getText().isEmpty() || txtsolde.getText().isEmpty()
    			 ) {
    		  tools.Notification.NotifError("error", "donnees  non  ajoutes!!!");
    	} else {
    		try {
					String typee=type.getSelectionModel().getSelectedItem();
					int soldee=Integer.parseInt(txtsolde.getText());

    				if(type.getSelectionModel().getSelectedItem().equals("Bloque") && soldee>10000 )
    				{
    					Comptebloque co =new
						Comptebloque(txtnumero.getText(),date.getValue().toString(), Integer.parseInt(txtsolde.getText())
        				, txtetat.getText(),type.getSelectionModel().getSelectedItem(),
        				comboagence.getSelectionModel().getSelectedItem(),
       					 comboclient.getSelectionModel().getSelectedItem()
       					 , comboemploye.getSelectionModel().getSelectedItem()
						, 7000, date1.getValue().toString(), 20000);
      					  ok=ic1.addcbloque(co);
    				}
    			//		System.out.println(typee);
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

    private IEmploye im= factory.getEmployedao();
    private IAgence ia= factory.getAgencedao();
    private IClient ic= factory.getClientdao();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadTable();
		ObservableList<String> s = FXCollections.observableArrayList();
    	s.addAll("faite votre choix","Bloque");
    	type.setItems(s);

		ObservableList<Employe> employe = FXCollections.observableArrayList();
    	Employe em = new Employe();
       	em.setIdemp(1);
    	em.setNom("..");
        em.setPrenom("Faite votre choix");
        em.setTel("..");

    	employe.add(em);
		try {
			im.liste().stream()
								.forEach(e->{
									employe.add(e);
								});
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		comboemploye.setItems(employe);

    	comboemploye.getSelectionModel().selectFirst();

    	ObservableList<Client> emp = FXCollections.observableArrayList();
    	Client c = new Client();
       	c.setIdclient(1);
    	c.setNom("..");
        c.setPrenom("Faite votre choix");
        c.setProfession("..");

    	emp.add(c);
		try {
			ic.liste().stream()
								.forEach(e->{
									emp.add(e);
								});
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		comboclient.setItems(emp);
    	comboclient.getSelectionModel().selectFirst();

        ObservableList<Agence> ag = FXCollections.observableArrayList();
    	Agence a=new Agence();
       	a.setIdA(1);
    	a.setNomAgence("..");
        a.setAdress("Faite votre choix");

    	ag.add(a);
		try {
			ia.liste().stream()
								.forEach(e->{
									ag.add(e);
								});
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		comboagence.setItems(ag);
        comboagence.getSelectionModel().selectFirst();

	}


}
