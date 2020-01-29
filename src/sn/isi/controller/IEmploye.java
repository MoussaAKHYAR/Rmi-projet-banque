package sn.isi.controller;

import sn.isi.entities.Employe;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IEmploye extends Remote
{
	public int add(Employe e) throws RemoteException;
	public int delete(int idemp) throws RemoteException;
	public int update(Employe e) throws RemoteException;
	public List<Employe> liste() throws RemoteException;
	public Employe get(int idemp) throws RemoteException;
	public Employe getlogin(String login, String password) throws RemoteException;
	public String getType(String login) throws RemoteException;
}
