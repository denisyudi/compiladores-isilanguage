grammar IsiLang;

@header{
	import br.com.denisyudi.projeto.datastructures.IsiSymbol;
	import br.com.denisyudi.projeto.datastructures.IsiVariable;
	import br.com.denisyudi.projeto.datastructures.IsiSymbolTable;
	import br.com.denisyudi.projeto.exceptions.IsiSemanticException;
	import br.com.denisyudi.projeto.ast.IsiProgram;
	import br.com.denisyudi.projeto.ast.AbstractCommand;
 	import br.com.denisyudi.projeto.ast.CommandLeitura;
	import br.com.denisyudi.projeto.ast.CommandEscrita;
	import br.com.denisyudi.projeto.ast.CommandAtribuicao;
	import br.com.denisyudi.projeto.ast.CommandDecisao;
	import br.com.denisyudi.projeto.ast.CommandEnquanto;
	import br.com.denisyudi.projeto.ast.CommandIncremento;
	import br.com.denisyudi.projeto.ast.CommandPara;
	import br.com.denisyudi.projeto.ast.CommandComentario;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> currentThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprDecision;
	private String _exprWhile;
	private String _exprForStart;
	private String _exprForDecision;
	private String _exprForIncrement;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private ArrayList<AbstractCommand> listaComando;
	
	public IsiSymbol getSymbolByID(String id){
		return symbolTable.get(id);
	}
	
	public void verificaID (String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol " + id + " not declared");
		}
	}
	
	public void exibeComandos() {
		for (AbstractCommand c: program.getComandos()) {
			System.out.println(c);
		}
	}
	
	public void generateCode() {
		program.generateTarget();
	}
}

prog : 'programa' decl bloco 'fimprog;'
		{
			program.setVarTable(symbolTable);
			program.setComandos(stack.pop());
			for(IsiSymbol symbol: symbolTable.getAll()){
           	  	if (!symbol.isUsed()){
	            	throw new IsiSemanticException("Symbol " + symbol.getName() + " declared but not used.");
           	  	}
            }	
		}
	 ;
		
decl : (declaravar)+
	 ;
		
declaravar : tipo 
			 ID {
					_varName = _input.LT(-1).getText();
					_varValue = null;
					symbol = new IsiVariable(_varName, _tipo, _varValue);
					if (!symbolTable.exists(_varName)){
						symbolTable.add(symbol);
					} else {
						throw new IsiSemanticException("Symbol " + _varName+ " already declared");
					}
			 }
             (
             VIR
             ID {
					_varName = _input.LT(-1).getText();
					_varValue = null;
					symbol = new IsiVariable(_varName, _tipo, _varValue);
					if (!symbolTable.exists(_varName)){
						symbolTable.add(symbol);
					} else {
						throw new IsiSemanticException("Symbol " + _varName+ " already declared");
					}
			 }
             )* 
             SC
		   ;
		  
tipo : 'numero' { _tipo = IsiVariable.NUMBER; }
	 | 'texto'  { _tipo = IsiVariable.TEXT; }
	 ;
	 	
bloco : { 
			currentThread = new ArrayList<AbstractCommand>(); 
			stack.push(currentThread);
		}
		(cmd)+
	  ;
		
cmd	: cmdleitura | cmdescrita | cmdattrib | cmdselecao | cmdrepeticao | cmdpara | cmdincremento 
	;
		
cmdleitura : 'leia' 
			 AP 
			 ID {
					verificaID(_input.LT(-1).getText()); 
					_readID = _input.LT(-1).getText();
					symbolTable.get(_readID).setUsed();
			 }
			 FP 
			 SC {
					IsiVariable var = (IsiVariable) symbolTable.get(_readID);
					CommandLeitura cmd = new CommandLeitura(_readID, var);
					stack.peek().add(cmd);
			 }	
		   ;
		   
cmdescrita : 'escreva' 
			 AP
			 ID { 
					verificaID(_input.LT(-1).getText());
					_writeID = _input.LT(-1).getText();
					symbolTable.get(_writeID).setUsed();
			 } 
			 FP 
			 SC {
					CommandEscrita cmd = new CommandEscrita(_writeID);
					stack.peek().add(cmd);
			 }
		   ;

cmdattrib : ID { 
					verificaID(_input.LT(-1).getText()); 
					_exprID = _input.LT(-1).getText();
					symbolTable.get(_exprID).setUsed();
			} 
			ATTR {
			 		 _exprContent = "";
			}
 			expr 
 			SC {
 					CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
					stack.peek().add(cmd);
			}
		  ;
		  
cmdselecao : 'se' 
			 AP 
			 ID { 
			 		_exprDecision = _input.LT(-1).getText();
			 		symbolTable.get(_exprDecision).setUsed();
			 }
			 OPREL { 
					_exprDecision += _input.LT(-1).getText();
			 }
			 (ID | NUMBER) { 
					_exprDecision += _input.LT(-1).getText();
			 }	
			 FP ACH { 
			 		currentThread = new ArrayList<AbstractCommand>(); 
					stack.push(currentThread);
			 }
			 (cmd)+ 
			 FCH {
					listaTrue = stack.pop();
			 }
			 ('senao' 
			 ACH {
			 		currentThread = new ArrayList<AbstractCommand>(); 
					stack.push(currentThread);
			 }
			 (cmd)+ 
			 FCH {
			 		listaFalse = stack.pop();
			 		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
			 		stack.peek().add(cmd);
			 }
			 )?	
		   ;	  

cmdrepeticao : 'enquanto'
			    AP
			    ID {
			    		_exprWhile = _input.LT(-1).getText();
			    		symbolTable.get(_exprWhile).setUsed();
				}
				OPREL {
						_exprWhile += _input.LT(-1).getText();
				}
				(ID | NUMBER) { 
                   		_exprWhile += _input.LT(-1).getText();
		      	}
				FP
				ACH { 
						currentThread = new ArrayList<AbstractCommand>(); 
		            	stack.push(currentThread);
				}
				(cmd)+ 
				FCH {
						listaComando = stack.pop();
						CommandEnquanto cmd = new CommandEnquanto(_exprWhile, listaComando);
						stack.peek().add(cmd);
				}
			 ;

cmdpara : 'para'
		  AP
		  ID { 
					_exprForStart = _input.LT(-1).getText();
					symbolTable.get(_exprForStart).setUsed();
		  } 
		  ATTR {
			 		_exprForStart += "=";
		  }
		  (ID | NUMBER) { 
                   	_exprForStart += _input.LT(-1).getText();
		  }
		  'ate'
		  ID { 
					_exprForDecision = _input.LT(-1).getText();
		  } 
		  OPREL {
			 		_exprForDecision += _input.LT(-1).getText();
		  }
		  (ID | NUMBER) { 
                   	_exprForDecision += _input.LT(-1).getText();
		  }
		  'passo'
		  ID { 
					_exprForIncrement = _input.LT(-1).getText();
		  }
 		  IC {
 		  			_exprForIncrement += "++";
 		  }
		  FP
		  ACH { 
					currentThread = new ArrayList<AbstractCommand>(); 
		            stack.push(currentThread);
		  }
		  (cmd)+ 
		  FCH {
					listaComando = stack.pop();
					CommandPara cmd = new CommandPara(_exprForStart, _exprForDecision, _exprForIncrement,  listaComando);
					stack.peek().add(cmd);
		  }	
		;

cmdincremento : ID { 
						verificaID(_input.LT(-1).getText()); 
						_exprID = _input.LT(-1).getText();
						symbolTable.get(_exprID).setUsed();
				}
 				IC
 				SC {
 						CommandIncremento cmd = new CommandIncremento(_exprID);
						stack.peek().add(cmd);
				}
			  ;
			  
			  
expr : termo
	   ( OP { 
	   			_exprContent += _input.LT(-1).getText();
	   }
	   termo
	   )*
	   | TEXT {
	   			_exprContent += _input.LT(-1).getText();
	   }
	 ;

termo : ID { 
				verificaID(_input.LT(-1).getText()); 
				_exprContent += _input.LT(-1).getText();
		} 
		| NUMBER { 
				_exprContent += _input.LT(-1).getText();
		}
	  ;
		   
AP	: '('
	;
	
FP  : ')'
	;

SC	: ';'
	;
	
OP	: '+' | '-' | '*' | '/'
	;

ATTR : '='
	 ;

VIR : ','
	;

ACH : '{'
	;
	
FCH : '}'
    ;	

OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
	  ;

ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
NUMBER : [0-9]+ ('.' [0-9]+)?
	   ;
	   
TEXT : '"' (~["\\] | '\\' .)* '"'
;

WS	: (' ' | '\t' | '\n' | '\r') -> skip;

IC : '++'
   ;

BLCOMMENT
    :   '/*' .*? '*/' -> skip
    ;

COMMENT
    :   '//' ~[\r\n]* -> skip
    ;
