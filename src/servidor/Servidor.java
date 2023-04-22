package servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;

public class Servidor {
	public static final int LEITURA = 0;
	public static final int ESCRITA = 1;
	public static final int CLIENTE_1 = 0;
	public static final int CLIENTE_2 = 1;
	public static final int CLIENTE_3 = 2;
	
	public static void main(String args[]) {
		try {
			Servidor servidor = new Servidor();
			
			LocateRegistry.createRegistry(20000);
			InterfaceRequisicao interfaceRequisicao = new RemoteRequisicao(servidor);
			Naming.rebind("rmi://localhost:20000/request", interfaceRequisicao);
			
			System.out.println("Servidor rodando na porta 20000.");
		}
		catch (Exception e) {
            System.err.println("Exceção no Servidor: " + e.toString());
            e.printStackTrace();
            System.exit(0);
		}
	}
}
