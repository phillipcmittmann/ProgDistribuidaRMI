package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InterfaceRequisicao extends Remote {
	ArrayList<Thread> listaClientes = null;
	Servidor servidor = null;
	
	public String requisicao(String id, int p, String conteudo) throws RemoteException;
}
