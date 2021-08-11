package br.com.denisyudi.projeto.ast;

public class CommandIncremento extends AbstractCommand {
	
	private String id;
	
	public CommandIncremento(String id) {
		this.id = id;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		return "\t\t" + id + "++;";
	}

	@Override
	public String toString() {
		return "CommandIncremento ["+ id + "++]";
	}

}
