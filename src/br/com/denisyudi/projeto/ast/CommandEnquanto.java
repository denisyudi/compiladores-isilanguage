package br.com.denisyudi.projeto.ast;

import java.util.ArrayList;

public class CommandEnquanto extends AbstractCommand{
	
	private String condition;
	private ArrayList<AbstractCommand> listaComando;
	
	public CommandEnquanto(String condition, ArrayList<AbstractCommand> lc) {
		this.condition = condition;
		this.listaComando = lc;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("\t\twhile ("+condition+") {\n\t\t\t");
		for (AbstractCommand cmd: listaComando) {
			str.append(cmd.generateJavaCode()+"\n");
		}
		str.append("\t\t}");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandEnquanto [condition=" + condition + ", comandos=" + listaComando + "]";
	}

}
