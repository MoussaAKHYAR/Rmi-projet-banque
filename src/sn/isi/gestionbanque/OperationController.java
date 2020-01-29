package sn.isi.gestionbanque;

import com.itextpdf.text.Image;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import sn.isi.controller.IAgence;
import sn.isi.controller.IClient;
import sn.isi.controller.ICompte;
import sn.isi.controller.IOperation;
import sn.isi.entities.Agence;
import sn.isi.entities.Client;
import sn.isi.entities.Compte;
import sn.isi.entities.Operation;
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

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OperationController implements Initializable{

    @FXML
    private JFXTextField txtmontant;

    @FXML
    private JFXButton btn_valider;

    @FXML
    private JFXButton btn_modifier;

    @FXML
    private JFXButton btn_supprimer;

    @FXML
    private TableView< Operation> tableoperation;

    @FXML
    private TableColumn< Operation, Integer> idcolumn;

    @FXML
    private TableColumn< Operation, Date> datecolumn;

    @FXML
    private TableColumn< Operation, Integer> montantcolumn;

    @FXML
    private TableColumn< Operation, String> typecolumn;

    @FXML
    private TableColumn< Operation, Compte> comptecolumn;

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
    private JFXComboBox<Agence> agence;

    @FXML
    private JFXComboBox<Client> client;

    @FXML
    private JFXDatePicker date;

    Factory factory = new Factory();
    @FXML
    void actualiser(ActionEvent event) {
    	txtmontant.setText("");
	    type.getSelectionModel().selectFirst();
	    agence.getSelectionModel().selectFirst();
	    client.getSelectionModel().selectFirst();
		compte.getSelectionModel().selectFirst();

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
private int idOp;
    @FXML
    void tableclick(MouseEvent event) {
    	Operation ip=tableoperation.getSelectionModel().getSelectedItem();
    	idOp=ip.getIdOp();
    	//txtmontant.setText(ope.getMontantOp().toSt);
    	//date.getChronology();
    	type.getSelectionModel().select(ip.getTypeOp());
    	agence.getSelectionModel().select(ip.getIdA());
    	compte.getSelectionModel().select(ip.getNumCpt());
    	client.getSelectionModel().select(ip.getIdclient());
	    gestionButtunform();


    }

    private void gestionButtunform()
    {
    	btn_valider.setDisable(true);
    	//annuler_btn.setText("Actualiser");
    	btn_supprimer.setDisable(false);//activation
    	btn_modifier.setDisable(false);
    }

    private IOperation ip = factory.getOperationdao() ;

    private int ok;
    private int ok1;
    private int ok2;
    private int ok3;
    private int ok4;
    @FXML

    void valider(ActionEvent event)
    {
    	if (txtmontant.getText().isEmpty() ) {
    		  tools.Notification.NotifError("error", "donnees  non  ajoutes!!!");
    	} else {
    		try {
    			 Operation  co = new Operation();
    			 co.setMontantOp(Integer.parseInt(txtmontant.getText()));
    			 co.setDateOp(date.getValue().toString());
    			 co.setTypeOp(type.getSelectionModel().getSelectedItem());
    			 co.setNumCpt(compte.getSelectionModel().getSelectedItem());
    			 co.setIdA((agence.getSelectionModel().getSelectedItem()));
    			 co.setIdclient(client.getSelectionModel().getSelectedItem());

    			 String typee=compte.getSelectionModel().getSelectedItem().getType();
    			 String etat=compte.getSelectionModel().getSelectedItem().getEtat();

    			 int montant=Integer.parseInt(txtmontant.getText());
    			 int solde = compte.getSelectionModel().getSelectedItem().getSolde();
    			 if(type.getSelectionModel().getSelectedItem().equalsIgnoreCase("Depot") )
    			 {
    				 if(montant>0 )
    				 {
    					 if(typee.equalsIgnoreCase("Courant") || typee.equalsIgnoreCase("Epargne") )
	    				 {
    						 if(etat.equalsIgnoreCase("Fermer"))
    						 {

    						 }else
    							 {
    								 ok1=ip.verser(montant, compte.getSelectionModel().getSelectedItem().getNumCpt().toString());
    			                	 ok=ip.depot(co);
    			                	 ok3=1;
    			                	 loadTable();
    							 }

	    				 }
    				 }else
    				 {
						ok3=0;
    				 }
    				 	if(ok3==0)
		 					{
		 						tools.Outils.showErrorMessage("erreur", "le montant doit erreur positif et le type de compte doit etre epargne ou courant et actif!!!");

		 					}

                 	}
    			 		if(type.getSelectionModel().getSelectedItem().equalsIgnoreCase("Retrait") && montant>0)
    			 		{
		    				 if(solde>montant)
		    			 	{
		    				 if(typee.equalsIgnoreCase("Courant") || typee.equalsIgnoreCase("Epargne") )
		    				 {
		    					 if(etat.equalsIgnoreCase("Fermer"))
	    						 {

	    						 }else
	    						 {
									 ok1=ip.retirer(montant, compte.getSelectionModel().getSelectedItem().getNumCpt().toString());
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
				 					tools.Outils.showErrorMessage("erreur", "un compte bloque ou fermer ne peut pas effectuer un retrait!!!");

				 				}
		                 }else
		                 {
		                	 ok2=0;
		                 }
		    			 if(ok2==0)
		 				{
		 					tools.Outils.showErrorMessage("erreur", "le montant retir� est superieur au solde!!!");

		 				}



    			 }

				if(ok1!=0 && ok!=0)
    			   {
    				   tools.Notification.NotifSucces("Sucess", "donnees ajoutes!!!");
    				   Recu();


    			   }else
    			   {
    				   tools.Notification.NotifError("error", "donnees  non  ajoutes!!!");

    			   }


    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		}
    }
private IAgence ia = factory.getAgencedao() ;
private IClient iC = factory.getClientdao();
private ICompte ico = factory.getComptedao();
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
		ip.listeothers().stream().forEach(e->{
								opera.add(e);
							});
	} catch (RemoteException e) {
		e.printStackTrace();
	}
	tableoperation.setItems(opera);


  }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadTable();
		ObservableList<String> s = FXCollections.observableArrayList();
    	s.addAll("faite votre choix","Depot","Retrait");
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
		} catch (RemoteException e) {
			e.printStackTrace();
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
		} catch (RemoteException e) {
			e.printStackTrace();
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
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		compte.setItems(com);
		compte.getSelectionModel().selectFirst();

	}

    public void Recu() throws DocumentException, IOException {

    	int montant = Integer.parseInt(txtmontant.getText());
		String compte1 = compte.getSelectionModel().getSelectedItem().getNumCpt();
		int soldecompte = compte.getSelectionModel().getSelectedItem().getSolde();
		String type1 = type.getSelectionModel().getSelectedItem();
		String agence1 = agence.getSelectionModel().getSelectedItem().getNomAgence();
		String nom=client.getSelectionModel().getSelectedItem().getNom();
		String prenom=client.getSelectionModel().getSelectedItem().getPrenom();
		String adress=client.getSelectionModel().getSelectedItem().getAdress();
		String tel=client.getSelectionModel().getSelectedItem().getTelephone();

		//Operation o= new Operation();
		//int numero=o.getIdOp();

		//name=c.getNomcomplet();
		//LocalDateTime.now();
		int number=(int)(Math.random()*5000000);
		String src = "C:\\Users\\Amrane\\Desktop\\RECU\\Recu+"+number+".pdf";
		Document doc = new Document();
		PdfWriter.getInstance(doc, new FileOutputStream(src));
		doc.open();
		 Image background = Image.getInstance("log1.png");

	    	doc.add(background);
		doc.add(new Paragraph("BANQUE DU PEUPLE", FontFactory.getFont(FontFactory.TIMES_ROMAN,50, BaseColor.RED) ));
		doc.add(new Paragraph("_____________________________________________________________________________"));
		doc.add(new Paragraph("RECU DE : "+type1 , FontFactory.getFont(FontFactory.TIMES_ROMAN,25, BaseColor.ORANGE)));
		doc.add(new Paragraph("_____________________________________________________________________________"));

		doc.add(new Paragraph("Agence  : "+agence1+" "                                            +"Tel : 338352231", FontFactory.getFont(FontFactory.TIMES_ROMAN,12) ));
		doc.add(new Paragraph("Dakar le   : "+LocalDate.now(), FontFactory.getFont(FontFactory.TIMES_ROMAN,12) ));
		doc.add(new Paragraph("_____________________________________________________________________________"));
		doc.add(new Paragraph("Informations de la transaction : ", FontFactory.getFont(FontFactory.TIMES_ROMAN,12) ));
		doc.add(new Paragraph("Nom du Client  : "+nom, FontFactory.getFont(FontFactory.TIMES_ROMAN,12) ));
		doc.add(new Paragraph("Prenoom du Client  : "+prenom, FontFactory.getFont(FontFactory.TIMES_ROMAN,12) ));
		doc.add(new Paragraph("Adresse du Client  : "+adress, FontFactory.getFont(FontFactory.TIMES_ROMAN,12) ));
		doc.add(new Paragraph("Telephone du Client  : "+tel, FontFactory.getFont(FontFactory.TIMES_ROMAN,12) ));
		doc.add(new Paragraph("Numero de Compte  : "+compte1, FontFactory.getFont(FontFactory.TIMES_ROMAN,12) ));
		doc.add(new Paragraph("Solde du Compte  : "+soldecompte, FontFactory.getFont(FontFactory.TIMES_ROMAN,12) ));
		doc.add(new Paragraph("Montant : "+montant, FontFactory.getFont(FontFactory.TIMES_ROMAN,12) ));
		doc.add(new Paragraph("Type d'Operation  : "+type1 , FontFactory.getFont(FontFactory.TIMES_ROMAN,12) ));
		doc.add(new Paragraph("_____________________________________________________________________________"));
		doc.close();

		Desktop.getDesktop().open(new File (src));
    }

}
