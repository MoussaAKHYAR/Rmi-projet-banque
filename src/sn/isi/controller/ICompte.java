package sn.isi.controller;

import sn.isi.entities.Compte;
import sn.isi.entities.Comptebloque;
import sn.isi.entities.Comptecourant;
import sn.isi.entities.Compteepargne;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface ICompte extends Remote {
	public int addcbloque(Comptebloque c) throws RemoteException;
	public int addcourant(Comptecourant c) throws RemoteException;
	public int addepargne(Compteepargne c) throws RemoteException;
	public int delete(String numCpt) throws RemoteException;
	public int update(Compte c) throws RemoteException;
	public List<Compte> liste() throws RemoteException;
	public Compte get(String numCpt) throws RemoteException;
	public int activer(String numCpt) throws RemoteException;
	public int fermer(String numCpt) throws RemoteException;

}
