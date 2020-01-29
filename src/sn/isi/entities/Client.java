package sn.isi.entities;

import java.io.Serializable;

public class Client implements Serializable
{
	private int idclient;
	private String nom;
	private String prenom;
	private String adress;
	private String email;
	private String telephone;
	private String profession;
	private String salaire;
	private Employeur numero;
	public Client() {
		super();
	}
	public Client(int idclient, String nom, String prenom, String adress, String email, String telephone,
			String profession, String salaire, Employeur numero) {
		super();
		this.idclient = idclient;
		this.nom = nom;
		this.prenom = prenom;
		this.adress = adress;
		this.email = email;
		this.telephone = telephone;
		this.profession = profession;
		this.salaire = salaire;
		this.numero = numero;
	}
	public int getIdclient() {
		return idclient;
	}
	public void setIdclient(int idclient) {
		this.idclient = idclient;
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getSalaire() {
		return salaire;
	}
	public void setSalaire(String salaire) {
		this.salaire = salaire;
	}
	public Employeur getNumero() {
		return numero;
	}
	public void setNumero(Employeur numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return idclient+" " + " "+ nom+" " + prenom ;
	}

}
