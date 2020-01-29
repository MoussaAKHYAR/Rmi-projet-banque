package tools;

import sn.isi.controller.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Factory {
    public IAgence agencedao;
    public ICompte comptedao;
    public IEmploye employedao;

    public IAgence getAgencedao() {
        return agencedao;
    }

    public ICompte getComptedao() {
        return comptedao;
    }

    public IEmploye getEmployedao() {
        return employedao;
    }

    public IEmployeur getEmployeurdao() {
        return employeurdao;
    }

    public IOperation getOperationdao() {
        return operationdao;
    }

    public IClient getClientdao() {
        return clientdao;
    }

    public IEmployeur employeurdao;
    public IOperation operationdao;
    public IClient clientdao;


    public Factory() {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1",1488);
            agencedao = (IAgence) registry.lookup("agencedao");
            comptedao = (ICompte) registry.lookup("comptedao");
            employedao = (IEmploye) registry.lookup("employedao");
            employeurdao = (IEmployeur) registry.lookup("employeurdao");
            operationdao = (IOperation) registry.lookup("operationdao");
            clientdao = (IClient) registry.lookup("clientdao");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
