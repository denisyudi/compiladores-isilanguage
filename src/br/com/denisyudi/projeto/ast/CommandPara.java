package br.com.denisyudi.projeto.ast;

import java.util.ArrayList;

public class CommandPara extends AbstractCommand{
	
	private String condition;
	private String start;
	private String increment;
	private ArrayList<AbstractCommand> listaComando;
	
	public CommandPara(String start, String condition, String increment, ArrayList<AbstractCommand> lc) {
		this.start = start;
		this.condition = condition;
		this.increment = increment;
		this.listaComando = lc;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("for (" + start + ";" + condition + ";" + increment + ") {\n");
		for (AbstractCommand cmd: listaComando) {
			str.append(cmd.generateJavaCode()+"\n");
		}
		str.append("}");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandPara [start=[" + start + "],condition=[" + condition + "], increment=[" + increment + "], listaComando=" + listaComando + "]";
	}
}
