package sn.isi.entities;


import java.io.Serializable;

public class Compte implements Serializable
{
@Override
	public String toString() {
		return numCpt +"  "+ " "+type +" " + idclient ;
	}
protected String numCpt;
protected String dateouverture;
protected int solde;
protected String etat;
protected String type;
protected Agence idA;
protected Client idclient;
protected Employe idemp;
public Compte() {
	super();
}

public Compte(String numCpt, String dateouverture, int solde, String etat, String type, Agence idA, Client idclient,
              Employe idemp) {
	super();
	this.numCpt = numCpt;
	this.dateouverture = dateouverture;
	this.solde = solde;
	this.etat = etat;
	this.type = type;
	this.idA = idA;
	this.idclient = idclient;
	this.idemp = idemp;
}

public String getNumCpt() {
	return numCpt;
}
public void setNumCpt(String numCpt) {
	this.numCpt = numCpt;
}

public String getDateouverture() {
	return dateouverture;
}

public void setDateouverture(String dateouverture) {
	this.dateouverture = dateouverture;
}

public int getSolde() {
	return solde;
}
public void setSolde(int solde) {
	this.solde = solde;
}
public String getEtat() {
	return etat;
}
public void setEtat(String etat) {
	this.etat = etat;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Agence getIdA() {
	return idA;
}
public void setIdA(Agence idA) {
	this.idA = idA;
}
public Client getIdclient() {
	return idclient;
}
public void setIdclient(Client idclient) {
	this.idclient = idclient;
}
public Employe getIdemp() {
	return idemp;
}
public void setIdemp(Employe idemp) {
	this.idemp = idemp;
}

}
