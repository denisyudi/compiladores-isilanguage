package br.com.denisyudi.projeto.ast;

public class CommandComentario extends AbstractCommand {
	
	private String expr;
	
	public CommandComentario(String expr) {
		this.expr = expr;
	}
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		return "//" + expr;
	}

	@Override
	public String toString() {
		return "CommandComentario [//" + expr + "]";
	}
}
