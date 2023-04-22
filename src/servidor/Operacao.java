package servidor;

public class Operacao {
	int operacao;
	String resposta;
	String conteudo;
	int numOperacao;
	
	public Operacao(int numOperacao, int operacao, String conteudo) {
		 if (operacao < 0 || operacao > 1) {
            System.err.println("Exceção no construtor de Operacao.");
            throw new IllegalArgumentException("O valor de operacao deve ser 0 (leitura) ou 1 (escrita).");
		 }
		 else if (operacao == 1 && conteudo == null) {
			System.err.println("Erro em Operacao: Operação de escrita mas conteúdo nulo. Nada será escrito.");
            conteudo = "";
		 }
		 
		 this.operacao = operacao;
		 this.conteudo = conteudo;
	}
}
