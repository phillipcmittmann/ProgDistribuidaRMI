package servidor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Arquivo {

    private String caminho = "src/clienteservidor/arquivos/";
    private File arquivo;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    
    public Arquivo() {

        this.arquivo = new File(caminho + "texto.txt");
        
        if (!arquivo.canRead() || !arquivo.canWrite()) {
            System.err.println("Arquivo não está acessível para leitura ou para escrita.");
            System.exit(0);
        }
    }
    
    public String leitura(String nomeCliente) {
    	try {
    		readWriteLock.readLock().lock();
    		
    		String conteudo = "";
    		Scanner scanner = new Scanner(this.arquivo);
    		
    		System.out.printf("Cliente %s lendo o arquivo.%n", nomeCliente);
    		
    		while (scanner.hasNext()) {
    			conteudo += scanner.nextLine();
    		}
    		
    		scanner.close();
    		
    		System.out.printf("Cliente %s parou de ler o arquivo.%n", nomeCliente);
    		
    		return conteudo;
    	}
    	catch (Exception e) {
    		System.err.println("Exceção na escrita do arquivo: " + e.toString());
            e.printStackTrace();
    	}
    	finally {
    		readWriteLock.readLock().unlock();
    	}
    	
    	return null;
    }
    
    public void escrita(String conteudo, String nomeCliente) {
    	try {
    		readWriteLock.writeLock().lock();
    		
    		System.out.printf("Cliente %s bloqueou o arquivo para escrita.%n", nomeCliente);
    		
    		PrintWriter writer = new PrintWriter(new FileOutputStream(arquivo, true));
    		
    		writer.println(conteudo);
    		writer.close();
    		
    		System.out.printf("Cliente %s parou de escrever no arquivo.%n", nomeCliente);
    	}
    	catch (Exception e) {
    		System.err.println("Exceção na escrita do arquivo: " + e.toString());
            e.printStackTrace();
            System.exit(0);
    	}
    	finally {
            readWriteLock.writeLock().unlock();
    	}
    }
}
