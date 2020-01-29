package main;

import sn.isi.entities.Employe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tools.Factory;
import tools.Notification;
import tools.Outils;

import java.io.IOException;

public class MainController
{
	 @FXML
	 private TextField email;
	 @FXML
	 private TextField password;
	 public static String label_connexion;

	@FXML
	void connexion(ActionEvent event) throws IOException
	{

	  	String log=email.getText();
	  	String pass=password.getText();
	  	Employe employe = null;
         if(log.equals("") || pass.equals(""))
         {

       	  Notification.NotifError("Message", "veuillez remplir les champs SVP !!!");
         }else
         {
         	Factory factory = new Factory();
         	employe = factory.getEmployedao().getlogin(log,pass);
       	  String type=factory.getEmployedao().getType(log);
       	  if(employe!=null)
       	  {
       		 if(type.equalsIgnoreCase("Responsable"))
       		 {
       			Employe.label_connexion="Bienvenue"+" Responsable MR(s)"+" "+employe.getNom()+" "+employe.getPrenom();
       			String url="/sn/isi/gestionbanque/MenuResponsable.fxml";
       			tools.Notification.NotifSucces("VOUS ETES CONNECTE EN TANTQUE RESPONSABLE", "CONNEXION REUSSIE!!!");
      	      	Outils.load(event,url,"Responsable");
       		 }
       		if(type.equalsIgnoreCase("Caissier"))
       		{
       			Employe.label_connexion="Bienvenue"+" Caissier  MR(s)"+employe.getNom()+" "+employe.getPrenom();
				String url="/sn/isi/gestionbanque/Menucaissier.fxml";
				tools.Notification.NotifSucces("VOUS ETES CONNECTE EN TANTQUE CAISSIER(E)", "CONNEXION REUSSIE!!!");
				Outils.load(event,url,"Caissier");
       		}
       		if(type.equalsIgnoreCase("Admin"))
       		{
       			Employe.label_connexion="Bienvenue L'ADMIN MR(s)"+employe.getNom()+" "+employe.getPrenom();
       			String url="/sn/isi/gestionbanque/menu.fxml";
       			tools.Notification.NotifSucces("VOUS ETES CONNECTE EN TANTQUE ADMIN", "CONNEXION REUSSIE!!!");
      	      	Outils.load(event,url,"Administrateur");
       		}
       	 }else
       	  {
       		  Notification.NotifError("Message", "ERREUR !!!");
       	  }

         }

	  }

}
