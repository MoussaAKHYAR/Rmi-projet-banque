package sn.isi.controller;

import sn.isi.entities.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IClient extends Remote
{
	public int add(Client c) throws RemoteException;
	public int delete(int idclient) throws RemoteException;
	public int update(Client c) throws RemoteException;
	public List<Client> liste() throws RemoteException;
	public Client get(int idclient) throws RemoteException;

}
