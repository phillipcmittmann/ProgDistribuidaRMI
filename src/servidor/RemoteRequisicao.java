package servidor;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.rmi.RemoteException;

public class RemoteRequisicao extends UnicastRemoteObject implements InterfaceRequisicao {
	private Servidor servidor;
	private ArrayList<Operacao> listaOperacoes;
	private int qtdOperacoes;
	
	protected RemoteRequisicao(Servidor servidor) throws RemoteException {
		super();
		this.servidor = servidor;
		this.qtdOperacoes = 0;
	}

	public String requisicao(int cliente, int tipoOperacao, String conteudo) throws RemoteException {
		try {
			Operacao operacao = new Operacao(qtdOperacoes+1, tipoOperacao, conteudo);
			
			this.qtdOperacoes++;
			
			listaOperacoes.add(operacao);
			
			return operacao.resposta;
		} catch (Exception e) {
			System.err.println("Exceção no RemoteRequisicao, método requisicao: " + e.toString());
            e.printStackTrace();
            System.exit(0);
		}
		
		return null;
	}
}
