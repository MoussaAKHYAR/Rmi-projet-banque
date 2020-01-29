package sn.isi.entities;


import java.io.Serializable;

public class Comptecourant  extends Compte implements Serializable {

	private int montantagio;

	public Comptecourant(int montantagio) {
		super();

	}



	public Comptecourant(String numCpt, String dateouverture, int solde, String etat, String type, Agence idA,
                         Client idclient, Employe idemp, int montantagio) {
		super(numCpt, dateouverture, solde, etat, type, idA, idclient, idemp);
		this.montantagio = montantagio;
	}



	public int getMontantagio() {
		return montantagio;
	}

	public void setMontantagio(int montantagio) {
		this.montantagio = montantagio;
	}


}
