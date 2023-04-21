package servidor;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class RemoteRequisicao extends UnicastRemoteObject implements InterfaceRequisicao {
	private Servidor servidor;
	private ArrayList<Operacao> listaThreads;
}
