package sn.isi.controller;

import sn.isi.entities.Agence;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IAgence extends Remote
{
	public int add(Agence a) throws RemoteException;
	public int delete(int idA) throws RemoteException;
	public int update(Agence a) throws RemoteException;
	public List<Agence> liste() throws RemoteException;
	public Agence get(int idA) throws RemoteException;

}
