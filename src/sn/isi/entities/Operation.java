package sn.isi.entities;

import java.io.Serializable;

public class Operation implements Serializable
{
private int idOp;
private String dateOp;
private int montantOp;
private String typeOp;
private Compte numCpt;
private Agence idA;
private Client idclient;
public Operation() {
	super();
}
public Operation(int idOp, String dateOp, int montantOp, String typeOp, Compte numCpt, Agence idA, Client idclient) {
	super();
	this.idOp = idOp;
	this.dateOp = dateOp;
	this.montantOp = montantOp;
	this.typeOp = typeOp;
	this.numCpt = numCpt;
	this.idA = idA;
	this.idclient = idclient;
}
public int getIdOp() {
	return idOp;
}
public void setIdOp(int idOp) {
	this.idOp = idOp;
}
public String getDateOp() {
	return dateOp;
}
public void setDateOp(String dateOp) {
	this.dateOp = dateOp;
}
public int getMontantOp() {
	return montantOp;
}
public void setMontantOp(int montantOp) {
	this.montantOp = montantOp;
}
public String getTypeOp() {
	return typeOp;
}
public void setTypeOp(String typeOp) {
	this.typeOp = typeOp;
}
public Compte getNumCpt() {
	return numCpt;
}
public void setNumCpt(Compte numCpt) {
	this.numCpt = numCpt;
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

}
