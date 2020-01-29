package sn.isi.entities;

import java.io.Serializable;

public class Employeur implements Serializable
{
	private int numero ;
	private String nomemployeur;
	private String adresse;
	private String raisonsocial;
	public Employeur() {
		super();
	}
	@Override
	public String toString() {

		return   numero + "  " + adresse+ "  " + raisonsocial ;
	}
	public Employeur(int numero, String nomemployeur, String adresse, String raisonsocial) {
		super();
		this.numero = numero;
		this.nomemployeur = nomemployeur;
		this.adresse = adresse;
		this.raisonsocial = raisonsocial;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNomemployeur() {
		return nomemployeur;
	}
	public void setNomemployeur(String nomemployeur) {
		this.nomemployeur = nomemployeur;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getRaisonsocial() {
		return raisonsocial;
	}
	public void setRaisonsocial(String raisonsocial) {
		this.raisonsocial = raisonsocial;
	}


}
