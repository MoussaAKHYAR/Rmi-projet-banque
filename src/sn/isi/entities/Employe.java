package sn.isi.entities;

import java.io.Serializable;

public class Employe implements Serializable
{
 private int idemp;
 private String nom;
 private String prenom;
 private String tel;
 private String salaire;
 private String type;
 private String login;
 private String password;
 private Agence idA;
 public static String label_connexion;
@Override
public String toString() {
	return idemp +  nom +  prenom + " "+ type  ;
}
public Employe() {
	super();
}
public Employe(int idemp, String nom, String prenom, String tel, String salaire, String type, String login,
		String password, Agence idA) {
	super();
	this.idemp = idemp;
	this.nom = nom;
	this.prenom = prenom;
	this.tel = tel;
	this.salaire = salaire;
	this.type = type;
	this.login = login;
	this.password = password;
	this.idA = idA;
}
public int getIdemp() {
	return idemp;
}
public void setIdemp(int idemp) {
	this.idemp = idemp;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getSalaire() {
	return salaire;
}
public void setSalaire(String salaire) {
	this.salaire = salaire;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Agence getIdA() {
	return idA;
}
public void setIdA(Agence idA) {
	this.idA = idA;
}



}
