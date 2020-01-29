package sn.isi.controller;

import sn.isi.entities.Employeur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IEmployeur extends Remote
{
	public int add(Employeur e) throws RemoteException;
	public int delete(int numero) throws RemoteException;
	public int update(Employeur e) throws RemoteException;
	public List<Employeur> liste() throws RemoteException;
	public Employeur get(int numero) throws RemoteException;


}
