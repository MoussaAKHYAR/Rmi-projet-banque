package sn.isi.entities;


import java.io.Serializable;

public class Comptebloque extends Compte implements Serializable
{
 private int farisouvertureCB;
 private String delaiblocage;
 private int remunerationCB;
public Comptebloque() {
	super();
}


public Comptebloque(String numCpt, String dateouverture, int solde, String etat, String type, Agence idA,
		Client idclient, Employe idemp, int farisouvertureCB, String delaiblocage, int remunerationCB) {
	super(numCpt, dateouverture, solde, etat, type, idA, idclient, idemp);
	this.farisouvertureCB = farisouvertureCB;
	this.delaiblocage = delaiblocage;
	this.remunerationCB = remunerationCB;
}


public int getFarisouvertureCB() {
	return farisouvertureCB;
}
public void setFarisouvertureCB(int farisouvertureCB) {
	this.farisouvertureCB = farisouvertureCB;
}



public String getDelaiblocage() {
	return delaiblocage;
}


public void setDelaiblocage(String delaiblocage) {
	this.delaiblocage = delaiblocage;
}


public int getRemunerationCB() {
	return remunerationCB;
}
public void setRemunerationCB(int remunerationCB) {
	this.remunerationCB = remunerationCB;
}


}
