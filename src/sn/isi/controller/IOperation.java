package sn.isi.controller;

import sn.isi.entities.Operation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IOperation extends Remote
{
	public int retrait(Operation o) throws RemoteException;
	public int depot(Operation o) throws RemoteException;
	public int verser(int montant, String numCpt) throws RemoteException;
	public int retirer(int montant, String numCpt) throws RemoteException;
	public List<Operation> listevirement() throws RemoteException;
	public List<Operation> listeothers() throws RemoteException;
	public List<Operation> liste() throws RemoteException;
	public int delete(int idOp) throws RemoteException;

}
