package sn.isi.gestionbanque;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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

import sn.isi.controller.IAgence;
import sn.isi.controller.IClient;
import sn.isi.controller.ICompte;
import sn.isi.controller.IOperation;
import sn.isi.entities.Agence;
import sn.isi.entities.Client;
import sn.isi.entities.Compte;
import sn.isi.entities.Operation;
import tools.Factory;
import tools.Outils;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class  VirementController implements Initializable{

    @FXML
    private JFXTextField txtmontant;

    @FXML
    private JFXButton btn_valider;

    @FXML
    private JFXButton btn_modifier;

    @FXML
    private JFXButton btn_supprimer;

    @FXML
    private TableView<Operation> tableoperation;

    @FXML
    private TableColumn<Operation, Integer> idcolumn;

    @FXML
    private TableColumn<Operation, Date> datecolumn;

    @FXML
    private TableColumn<Operation, Integer> montantcolumn;

    @FXML
    private TableColumn<Operation, String> typecolumn;

    @FXML
    private TableColumn<Operation, Compte> comptecolumn;

    @FXML
    private TableColumn< Operation, Agence> agencecolumn;

    @FXML
    private TableColumn< Operation, Client> clientcolumn;

    @FXML
    private JFXButton btn_actualiser;

    @FXML
    private JFXComboBox<String> type;

    @FXML
    private JFXComboBox<Compte> compte;
    @FXML
    private JFXComboBox<Compte> compte1;

    Factory factory = new Factory();

    @FXML
    private JFXComboBox<Agence> agence;

    @FXML
    private JFXComboBox<Client> client;

    @FXML
    private JFXDatePicker date;

    @FXML
    void actualiser(ActionEvent event) {
    	txtmontant.setText("");
	    type.getSelectionModel().selectFirst();
	    agence.getSelectionModel().selectFirst();
	    client.getSelectionModel().selectFirst();
		compte.getSelectionModel().selectFirst();
		compte1.getSelectionModel().selectFirst();

	 	btn_valider.setDisable(false);
    }

    @FXML
    void modifier(ActionEvent event) {

    }


    	 @FXML
    	    void retour(ActionEvent event) throws IOException {
    	    	String url="/sn/isi/gestionbanque/Menuoperation.fxml";
    		      Outils.load(event, url,"Menu operation");
    	    }


    @FXML
    void supprimer(ActionEvent event) {
    	try {

		    ok =ip.delete(idOp);
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
private int idOp;
    @FXML
    void tableclick(MouseEvent event) {
    	Operation ope=tableoperation.getSelectionModel().getSelectedItem();
    	idOp=ope.getIdOp();
    	//txtmontant.setText(ope.getMontantOp().toSt);
    	//date.getChronology();
    	type.getSelectionModel().select(ope.getTypeOp());
    	agence.getSelectionModel().select(ope.getIdA());
    	compte.getSelectionModel().select(ope.getNumCpt());
    	compte1.getSelectionModel().select(ope.getNumCpt());
    	client.getSelectionModel().select(ope.getIdclient());
	    gestionButtunform();


    }

    private void gestionButtunform()
    {
    	btn_valider.setDisable(true);
    	//annuler_btn.setText("Actualiser");
    	btn_supprimer.setDisable(false);//activation
    	btn_modifier.setDisable(false);
    }

    private IOperation ip = factory.getOperationdao();
    private int ok;
    private int ok1;
    private int ok2;
    private int ok4;

    @FXML

    void valider(ActionEvent event)
    {
    	if (txtmontant.getText().isEmpty() ) {
    		  tools.Notification.NotifError("error", "donnees  non  ajoutes!!!");
    	} else {
    		try {
		    			 Operation  co =new   Operation();
		    			 co.setMontantOp(Integer.parseInt(txtmontant.getText()));
		    			 co.setDateOp(date.getValue().toString());
		    			 co.setTypeOp(type.getSelectionModel().getSelectedItem());
		    			 co.setNumCpt(compte.getSelectionModel().getSelectedItem());
		    			 co.setIdA((agence.getSelectionModel().getSelectedItem()));
		    			 co.setIdclient(client.getSelectionModel().getSelectedItem());
		    			 String typee=compte.getSelectionModel().getSelectedItem().getType();
		    			 int montant=Integer.parseInt(txtmontant.getText());
		                 int solde1=compte.getSelectionModel().getSelectedItem().getSolde();
		                 String etat=compte.getSelectionModel().getSelectedItem().getEtat();
		                 //int solde2=compte1.getSelectionModel().getSelectedItem().getSolde();

	                  if (montant>0)
	                  {
	                	  if(solde1>montant)
	                	  {
	                		  if(typee.equalsIgnoreCase("Courant") || typee.equalsIgnoreCase("Epargne") )
			    				 {
	                			  if(etat.equalsIgnoreCase("Fermer"))
		    						 {

		    						 }else
		    						 {
		                 ok1=ip.retirer(montant, compte.getSelectionModel().getSelectedItem().getNumCpt().toString());
		                 ok1=ip.verser(montant, compte1.getSelectionModel().getSelectedItem().getNumCpt().toString());
		                 ok=ip.retrait(co);
		                 ok2=1;
		                 ok4=1;
		                 loadTable();
		    						 }
		    				 }else
		    				 {
		    					ok4=0;
		    				 }
		    				 if(ok4==0)
				 				{
				 					tools.Outils.showErrorMessage("erreur", "un compte bloque ou fermer ,le type de compte doit etre actif ne peut pas effectuer un virement!!!");

				 				}
	                  } else
	                  {
	                 	 ok2=0;
	                  }


	                	  if(ok2==0)
		     				{
		     					tools.Outils.showErrorMessage("erreur", "le montant retir� est superieur au solde du compte!!!");

		     				}
	                  }
				if(ok1!=0 && ok!=0)
    			   {
    				   tools.Notification.NotifSucces("Sucess", "donnees ajoutes!!!");



    			   }else
    			   {
    				   tools.Notification.NotifError("error", "donnees  non  ajoutes!!!");

    			   }

    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		}
    }
private IAgence ia= factory.getAgencedao();
private IClient iC= factory.getClientdao();
private ICompte ico= factory.getComptedao();
private void loadTable(){
	idcolumn.setCellValueFactory(new PropertyValueFactory<>("idOp"));
	datecolumn.setCellValueFactory(new PropertyValueFactory<>("dateOp"));
	montantcolumn.setCellValueFactory(new PropertyValueFactory<>("montantOp"));
	typecolumn.setCellValueFactory(new PropertyValueFactory<>("typeOp"));
	comptecolumn.setCellValueFactory(new PropertyValueFactory<>("numCpt"));
	agencecolumn.setCellValueFactory(new PropertyValueFactory<>("idA"));
	clientcolumn.setCellValueFactory(new PropertyValueFactory<>("idclient"));

    ObservableList<Operation> opera= FXCollections.observableArrayList();
    try {
		ip.listevirement().stream().forEach(e->{
			opera.add(e);
		});
	}catch (Exception ex){
    	ex.printStackTrace();
	}

   tableoperation.setItems(opera);


  }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

loadTable();
		ObservableList<String> s = FXCollections.observableArrayList();
    	s.addAll("faite votre choix","Virement");
    	type.setItems(s);

       	ObservableList<Client> emp = FXCollections.observableArrayList();
    	Client c = new Client();
    	c.setIdclient(1);
    	c.setNom("..");
        c.setPrenom("Faite votre choix");
        c.setProfession("..");

    	emp.add(c);
    	try {
			iC.liste().stream()
					.forEach(e->{
						emp.add(e);
					});
		}catch (Exception ex){
    		ex.printStackTrace();
		}

    	client.setItems(emp);
    	client.getSelectionModel().selectFirst();


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
    	agence.setItems(ag);
        agence.getSelectionModel().selectFirst();

        ObservableList<Compte> com= FXCollections.observableArrayList();
        Compte ca=new Compte();
        ca.setNumCpt("");
    	ca.setEtat("");
        ca.setType(" faites votre choix");

    	com.add(ca);
    	try {
			ico.liste().stream()
					.forEach(e->{
						com.add(e);
					});
		}catch (Exception ex){
    		ex.printStackTrace();
		}

    compte.setItems(com);
    compte.getSelectionModel().selectFirst();

    ObservableList<Compte> com1= FXCollections.observableArrayList();
    Compte ca1=new Compte();
     ca1.setNumCpt("");
	ca1.setEtat("");
    ca1.setType(" compte de destination");

	com1.add(ca);
	try {
		ico.liste().stream()
				.forEach(e->{
					com1.add(e);
				});
	}catch (Exception ex){
		ex.printStackTrace();
	}

compte1.setItems(com1);
compte1.getSelectionModel().selectFirst();

	}

}
