package sn.isi.gestionbanque;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ResourceBundle;


public class CompteCourantController implements Initializable {

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

    @FXML
    private TableColumn<Compte, Integer> agicolumn;

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
	    type.getSelectionModel().selectFirst();
	    comboagence.getSelectionModel().selectFirst();
	    comboclient.getSelectionModel().selectFirst();
		comboemploye.getSelectionModel().selectFirst();
		txtetat.setText("");
	 	btn_valider.setDisable(false);
    }

    @FXML
    void modifier(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {

    }

    @FXML
    void supprimer(ActionEvent event) {
    	int ok =0;
    	try {
		     	ok = factory.getComptedao().delete(numCpt);
		 		   if(ok!=0)
		 		   {
		 			   tools.Notification.NotifSucces("Sucess", "donnees supprimé!!!");
		 		   }else
		 		   {
		 			   tools.Notification.NotifError("error", "donnees  non  supprimé!!");
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
  		agicolumn.setCellValueFactory(new PropertyValueFactory<>("montantagio"));

  		ObservableList<Compte> compt = FXCollections.observableArrayList();
		try {
			ic1.liste().stream().forEach(k-> compt.add(k));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		tablecomptecourant.setItems(compt);

      }
private ICompte ic1= factory.getComptedao();
//private ComptesDB ici=new ComptesDB();
private int ok;
private int ok2;
private int ok1;
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
    			if(typee.equalsIgnoreCase("Courant")  )
    			{
    				if(soldee>=10000)
    				{
    					Comptecourant co =new Comptecourant(txtnumero.getText(),date.getValue().toString(), Integer.parseInt(txtsolde.getText())
             			, txtetat.getText(),type.getSelectionModel().getSelectedItem(),
             			comboagence.getSelectionModel().getSelectedItem(), comboclient.getSelectionModel().getSelectedItem()
           			  , comboemploye.getSelectionModel().getSelectedItem()
          			   ,7500);
						ok2=1;
						ok=ic1.addcourant(co);
  						loadTable();
						actualiser(event);
    				}else
                 		{
                	 		ok2=0;
                 		}
    			 			if(ok2==0)
 							{
 								tools.Outils.showErrorMessage("erreur", "les solde doit etre superieur ou egale a 10000 francs!!!");
 							}
    			}
    			if(typee.equals("Epargne")   )
    			{
    				if(soldee>=10000)
    				{
    				Compteepargne co =new
  						Compteepargne(txtnumero.getText(),date.getValue().toString(), Integer.parseInt(txtsolde.getText())
          				, txtetat.getText(),type.getSelectionModel().getSelectedItem(),
          				comboagence.getSelectionModel().getSelectedItem(),
          				comboclient.getSelectionModel().getSelectedItem()
							, comboemploye.getSelectionModel().getSelectedItem()
					  ,5000,15000);
      				   ok=ic1.addepargne(co);
      				  loadTable();
      			  		actualiser(event);
      			  		ok1=1;
    			    }else
    			    {
               	    	ok1=0;
                    }
   			 		if(ok1==0)
					{
						tools.Outils.showErrorMessage("erreur", "le solde doit etre superieur ou egale a 10000francs!!!");

					}
    			}

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

    private IEmploye im = factory.getEmployedao();
    private IAgence ia= factory.getAgencedao();
    private IClient ic= factory.getClientdao();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadTable();
		ObservableList<String> s = FXCollections.observableArrayList();
    	s.addAll("faite votre choix","Courant","Epargne");
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
		}catch (Exception ex){
    		ex.printStackTrace();
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
		}catch (Exception ex){
			ex.printStackTrace();
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
		}catch (Exception ex){
			ex.printStackTrace();
		}

    	comboagence.setItems(ag);
        comboagence.getSelectionModel().selectFirst();

	}

}
