package sn.isi.entities;

import java.io.Serializable;

public class Agence implements Serializable {
@Override
	public String toString() {
		return idA + " "+ nomAgence +" " + adress ;
	}
private int idA;
private String nomAgence;
private String adress;
public Agence() {
	super();
}
public Agence(int idA, String nomAgence, String adress) {
	super();
	this.idA = idA;
	this.nomAgence = nomAgence;
	this.adress = adress;
}
public int getIdA() {
	return idA;
}
public void setIdA(int idA) {
	this.idA = idA;
}
public String getNomAgence() {
	return nomAgence;
}
public void setNomAgence(String nomAgence) {
	this.nomAgence = nomAgence;
}
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}

}
