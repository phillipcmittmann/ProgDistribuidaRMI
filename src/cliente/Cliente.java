package cliente;

import java.rmi.Naming;
import java.util.Scanner;

import servidor.InterfaceRequisicao;

public class Cliente {
	public String id;
	public InterfaceRequisicao interfaceRequisicao;
	
	public Cliente(String id) {
		this.id = id;
		
		try {
			interfaceRequisicao = (InterfaceRequisicao) Naming.lookup("rmi://localhost:20000/Request");
		}
		catch (Exception e) {
			System.err.println("Exceção no Cliente - obtenção do objeto remoto: " + e.toString());
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void main(String args[]) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("De um nome para o cliente: ");
			
			Cliente cliente = new Cliente(scanner.nextLine());
			
			System.out.println("Digite a operacao desejada: 0-Escrita; 1-Leitura");
			
			String conteudo = null;
			int operacao = 1;
			
			switch (scanner.nextInt()) {
				case 0:
					conteudo = "Cliente " + cliente.id + " escreveu no arquivo.";
					operacao = 0;
					break;
					
				case 1:
					operacao = 1;
					conteudo = null;
					break;
					
				default:
					System.out.println("Operacao nao encontrada.");
			}
			
			try {
				String retorno = cliente.interfaceRequisicao.requisicao(cliente.id, operacao, conteudo);
				System.out.println(retorno);
			}
			catch (Exception e) {
				System.err.println("RemoteException no Cliente.");
                e.printStackTrace();
			}
		}
		catch (Exception e) {
			System.err.println("Exceção no Cliente: " + e.toString());
			e.printStackTrace();
			System.exit(0);
		}
	}
}
