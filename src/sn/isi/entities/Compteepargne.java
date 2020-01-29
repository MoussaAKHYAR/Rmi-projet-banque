package sn.isi.entities;


import java.io.Serializable;

public class Compteepargne extends Compte implements Serializable
{
	private int fraisouvertureCE;
	private int renumerationCE;
	public Compteepargne() {
		super();
	}

	public Compteepargne(String numCpt, String dateouverture, int solde, String etat, String type, Agence idA,
			Client idclient, Employe idemp, int fraisouvertureCE, int renumerationCE) {
		super(numCpt, dateouverture, solde, etat, type, idA, idclient, idemp);
		this.fraisouvertureCE = fraisouvertureCE;
		this.renumerationCE = renumerationCE;
	}

	public int getFraisouvertureCE() {
		return fraisouvertureCE;
	}
	public void setFraisouvertureCE(int fraisouvertureCE) {
		this.fraisouvertureCE = fraisouvertureCE;
	}
	public int getRenumerationCE() {
		return renumerationCE;
	}
	public void setRenumerationCE(int renumerationCE) {
		this.renumerationCE = renumerationCE;
	}


}
