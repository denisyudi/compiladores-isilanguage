package br.com.denisyudi.projeto.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.com.denisyudi.projeto.exceptions.IsiSemanticException;
import br.com.denisyudi.projeto.parser.IsiLangLexer;
import br.com.denisyudi.projeto.parser.IsiLangParser;

public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;
			
			lexer = new IsiLangLexer(CharStreams.fromFileName("input.isi"));
			
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			parser = new IsiLangParser(tokenStream);
			
			parser.prog();
			
			System.out.println("Compilação feita com sucesso!");
			
			parser.exibeComandos();
			
			parser.generateCode();
			
		} 
		catch (IsiSemanticException e) {
			System.err.println("Semantic error - " + e.getMessage());
		}
		catch (Exception e) {
			System.err.println("ERROR " + e.getMessage());
		}
	}
}
