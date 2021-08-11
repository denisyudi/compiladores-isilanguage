// Generated from IsiLang.g4 by ANTLR 4.4

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__11=1, T__10=2, T__9=3, T__8=4, T__7=5, T__6=6, T__5=7, T__4=8, T__3=9, 
		T__2=10, T__1=11, T__0=12, AP=13, FP=14, SC=15, OP=16, ATTR=17, VIR=18, 
		ACH=19, FCH=20, OPREL=21, ID=22, NUMBER=23, TEXT=24, WS=25, IC=26, BLCOMMENT=27, 
		COMMENT=28;
	public static final String[] tokenNames = {
		"<INVALID>", "'ate'", "'passo'", "'se'", "'escreva'", "'fimprog;'", "'enquanto'", 
		"'senao'", "'para'", "'numero'", "'programa'", "'leia'", "'texto'", "'('", 
		"')'", "';'", "OP", "'='", "','", "'{'", "'}'", "OPREL", "ID", "NUMBER", 
		"TEXT", "WS", "'++'", "BLCOMMENT", "COMMENT"
	};
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_cmdpara = 11, RULE_cmdincremento = 12, 
		RULE_expr = 13, RULE_termo = 14;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdselecao", "cmdrepeticao", "cmdpara", "cmdincremento", 
		"expr", "termo"
	};

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); match(T__2);
			setState(31); decl();
			setState(32); bloco();
			setState(33); match(T__7);

						program.setVarTable(symbolTable);
						program.setComandos(stack.pop());
						for(IsiSymbol symbol: symbolTable.getAll()){
			           	  	if (!symbol.isUsed()){
				            	throw new IsiSemanticException("Symbol " + symbol.getName() + " declared but not used.");
			           	  	}
			            }	
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(36); declaravar();
				}
				}
				setState(39); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 || _la==T__0 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); tipo();
			setState(42); match(ID);

								_varName = _input.LT(-1).getText();
								_varValue = null;
								symbol = new IsiVariable(_varName, _tipo, _varValue);
								if (!symbolTable.exists(_varName)){
									symbolTable.add(symbol);
								} else {
									throw new IsiSemanticException("Symbol " + _varName+ " already declared");
								}
						 
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(44); match(VIR);
				setState(45); match(ID);

									_varName = _input.LT(-1).getText();
									_varValue = null;
									symbol = new IsiVariable(_varName, _tipo, _varValue);
									if (!symbolTable.exists(_varName)){
										symbolTable.add(symbol);
									} else {
										throw new IsiSemanticException("Symbol " + _varName+ " already declared");
									}
							 
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52); match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(58);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(54); match(T__3);
				 _tipo = IsiVariable.NUMBER; 
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(56); match(T__0);
				 _tipo = IsiVariable.TEXT; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 
						currentThread = new ArrayList<AbstractCommand>(); 
						stack.push(currentThread);
					
			setState(62); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(61); cmd();
				}
				}
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__8) | (1L << T__6) | (1L << T__4) | (1L << T__1) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdrepeticaoContext cmdrepeticao() {
			return getRuleContext(CmdrepeticaoContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdincrementoContext cmdincremento() {
			return getRuleContext(CmdincrementoContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdparaContext cmdpara() {
			return getRuleContext(CmdparaContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(73);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(66); cmdleitura();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67); cmdescrita();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(68); cmdattrib();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(69); cmdselecao();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(70); cmdrepeticao();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(71); cmdpara();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(72); cmdincremento();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); match(T__1);
			setState(76); match(AP);
			setState(77); match(ID);

								verificaID(_input.LT(-1).getText()); 
								_readID = _input.LT(-1).getText();
								symbolTable.get(_readID).setUsed();
						 
			setState(79); match(FP);
			setState(80); match(SC);

								IsiVariable var = (IsiVariable) symbolTable.get(_readID);
								CommandLeitura cmd = new CommandLeitura(_readID, var);
								stack.peek().add(cmd);
						 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); match(T__8);
			setState(84); match(AP);
			setState(85); match(ID);
			 
								verificaID(_input.LT(-1).getText());
								_writeID = _input.LT(-1).getText();
								symbolTable.get(_writeID).setUsed();
						 
			setState(87); match(FP);
			setState(88); match(SC);

								CommandEscrita cmd = new CommandEscrita(_writeID);
								stack.peek().add(cmd);
						 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(ID);
			 
								verificaID(_input.LT(-1).getText()); 
								_exprID = _input.LT(-1).getText();
								symbolTable.get(_exprID).setUsed();
						
			setState(93); match(ATTR);

						 		 _exprContent = "";
						
			setState(95); expr();
			setState(96); match(SC);

			 					CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
								stack.peek().add(cmd);
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdselecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdselecao(this);
		}
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); match(T__9);
			setState(100); match(AP);
			setState(101); match(ID);
			 
						 		_exprDecision = _input.LT(-1).getText();
						 		symbolTable.get(_exprDecision).setUsed();
						 
			setState(103); match(OPREL);
			 
								_exprDecision += _input.LT(-1).getText();
						 
			setState(105);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			 
								_exprDecision += _input.LT(-1).getText();
						 
			setState(107); match(FP);
			setState(108); match(ACH);
			 
						 		currentThread = new ArrayList<AbstractCommand>(); 
								stack.push(currentThread);
						 
			setState(111); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(110); cmd();
				}
				}
				setState(113); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__8) | (1L << T__6) | (1L << T__4) | (1L << T__1) | (1L << ID))) != 0) );
			setState(115); match(FCH);

								listaTrue = stack.pop();
						 
			setState(128);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(117); match(T__5);
				setState(118); match(ACH);

							 		currentThread = new ArrayList<AbstractCommand>(); 
									stack.push(currentThread);
							 
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(120); cmd();
					}
					}
					setState(123); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__8) | (1L << T__6) | (1L << T__4) | (1L << T__1) | (1L << ID))) != 0) );
				setState(125); match(FCH);

							 		listaFalse = stack.pop();
							 		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
							 		stack.peek().add(cmd);
							 
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdrepeticaoContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdrepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdrepeticao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdrepeticao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdrepeticao(this);
		}
	}

	public final CmdrepeticaoContext cmdrepeticao() throws RecognitionException {
		CmdrepeticaoContext _localctx = new CmdrepeticaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdrepeticao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); match(T__6);
			setState(131); match(AP);
			setState(132); match(ID);

						    		_exprWhile = _input.LT(-1).getText();
						    		symbolTable.get(_exprWhile).setUsed();
							
			setState(134); match(OPREL);

									_exprWhile += _input.LT(-1).getText();
							
			setState(136);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			 
			                   		_exprWhile += _input.LT(-1).getText();
					      	
			setState(138); match(FP);
			setState(139); match(ACH);
			 
									currentThread = new ArrayList<AbstractCommand>(); 
					            	stack.push(currentThread);
							
			setState(142); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(141); cmd();
				}
				}
				setState(144); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__8) | (1L << T__6) | (1L << T__4) | (1L << T__1) | (1L << ID))) != 0) );
			setState(146); match(FCH);

									listaComando = stack.pop();
									CommandEnquanto cmd = new CommandEnquanto(_exprWhile, listaComando);
									stack.peek().add(cmd);
							
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdparaContext extends ParserRuleContext {
		public TerminalNode NUMBER(int i) {
			return getToken(IsiLangParser.NUMBER, i);
		}
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode IC() { return getToken(IsiLangParser.IC, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(IsiLangParser.NUMBER); }
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdparaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdpara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdpara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdpara(this);
		}
	}

	public final CmdparaContext cmdpara() throws RecognitionException {
		CmdparaContext _localctx = new CmdparaContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdpara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); match(T__4);
			setState(150); match(AP);
			setState(151); match(ID);
			 
								_exprForStart = _input.LT(-1).getText();
								symbolTable.get(_exprForStart).setUsed();
					  
			setState(153); match(ATTR);

						 		_exprForStart += "=";
					  
			setState(155);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			 
			                   	_exprForStart += _input.LT(-1).getText();
					  
			setState(157); match(T__11);
			setState(158); match(ID);
			 
								_exprForDecision = _input.LT(-1).getText();
					  
			setState(160); match(OPREL);

						 		_exprForDecision += _input.LT(-1).getText();
					  
			setState(162);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			 
			                   	_exprForDecision += _input.LT(-1).getText();
					  
			setState(164); match(T__10);
			setState(165); match(ID);
			 
								_exprForIncrement = _input.LT(-1).getText();
					  
			setState(167); match(IC);

			 		  			_exprForIncrement += "++";
			 		  
			setState(169); match(FP);
			setState(170); match(ACH);
			 
								currentThread = new ArrayList<AbstractCommand>(); 
					            stack.push(currentThread);
					  
			setState(173); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(172); cmd();
				}
				}
				setState(175); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__8) | (1L << T__6) | (1L << T__4) | (1L << T__1) | (1L << ID))) != 0) );
			setState(177); match(FCH);

								listaComando = stack.pop();
								CommandPara cmd = new CommandPara(_exprForStart, _exprForDecision, _exprForIncrement,  listaComando);
								stack.peek().add(cmd);
					  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdincrementoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode IC() { return getToken(IsiLangParser.IC, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdincrementoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdincremento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdincremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdincremento(this);
		}
	}

	public final CmdincrementoContext cmdincremento() throws RecognitionException {
		CmdincrementoContext _localctx = new CmdincrementoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cmdincremento);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); match(ID);
			 
									verificaID(_input.LT(-1).getText()); 
									_exprID = _input.LT(-1).getText();
									symbolTable.get(_exprID).setUsed();
							
			setState(182); match(IC);
			setState(183); match(SC);

			 						CommandIncremento cmd = new CommandIncremento(_exprID);
									stack.peek().add(cmd);
							
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(IsiLangParser.TEXT, 0); }
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr);
		int _la;
		try {
			setState(197);
			switch (_input.LA(1)) {
			case ID:
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(186); termo();
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OP) {
					{
					{
					setState(187); match(OP);
					 
						   			_exprContent += _input.LT(-1).getText();
						   
					setState(189); termo();
					}
					}
					setState(194);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(195); match(TEXT);

					   			_exprContent += _input.LT(-1).getText();
					   
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termo);
		try {
			setState(203);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(199); match(ID);
				 
								verificaID(_input.LT(-1).getText()); 
								_exprContent += _input.LT(-1).getText();
						
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(201); match(NUMBER);
				 
								_exprContent += _input.LT(-1).getText();
						
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\36\u00d0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\6\3(\n\3\r\3\16\3)\3\4\3\4\3\4\3\4\3\4\3\4\7\4\62\n\4\f\4\16"+
		"\4\65\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5=\n\5\3\6\3\6\6\6A\n\6\r\6\16\6"+
		"B\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7L\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13r\n\13\r\13"+
		"\16\13s\3\13\3\13\3\13\3\13\3\13\3\13\6\13|\n\13\r\13\16\13}\3\13\3\13"+
		"\3\13\5\13\u0083\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\6\f\u0091\n\f\r\f\16\f\u0092\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6"+
		"\r\u00b0\n\r\r\r\16\r\u00b1\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\7\17\u00c1\n\17\f\17\16\17\u00c4\13\17\3\17\3\17"+
		"\5\17\u00c8\n\17\3\20\3\20\3\20\3\20\5\20\u00ce\n\20\3\20\2\2\21\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36\2\3\3\2\30\31\u00d2\2 \3\2\2\2\4\'\3"+
		"\2\2\2\6+\3\2\2\2\b<\3\2\2\2\n>\3\2\2\2\fK\3\2\2\2\16M\3\2\2\2\20U\3\2"+
		"\2\2\22]\3\2\2\2\24e\3\2\2\2\26\u0084\3\2\2\2\30\u0097\3\2\2\2\32\u00b6"+
		"\3\2\2\2\34\u00c7\3\2\2\2\36\u00cd\3\2\2\2 !\7\f\2\2!\"\5\4\3\2\"#\5\n"+
		"\6\2#$\7\7\2\2$%\b\2\1\2%\3\3\2\2\2&(\5\6\4\2\'&\3\2\2\2()\3\2\2\2)\'"+
		"\3\2\2\2)*\3\2\2\2*\5\3\2\2\2+,\5\b\5\2,-\7\30\2\2-\63\b\4\1\2./\7\24"+
		"\2\2/\60\7\30\2\2\60\62\b\4\1\2\61.\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2"+
		"\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66\67\7\21\2\2\67\7\3\2\2"+
		"\289\7\13\2\29=\b\5\1\2:;\7\16\2\2;=\b\5\1\2<8\3\2\2\2<:\3\2\2\2=\t\3"+
		"\2\2\2>@\b\6\1\2?A\5\f\7\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\13"+
		"\3\2\2\2DL\5\16\b\2EL\5\20\t\2FL\5\22\n\2GL\5\24\13\2HL\5\26\f\2IL\5\30"+
		"\r\2JL\5\32\16\2KD\3\2\2\2KE\3\2\2\2KF\3\2\2\2KG\3\2\2\2KH\3\2\2\2KI\3"+
		"\2\2\2KJ\3\2\2\2L\r\3\2\2\2MN\7\r\2\2NO\7\17\2\2OP\7\30\2\2PQ\b\b\1\2"+
		"QR\7\20\2\2RS\7\21\2\2ST\b\b\1\2T\17\3\2\2\2UV\7\6\2\2VW\7\17\2\2WX\7"+
		"\30\2\2XY\b\t\1\2YZ\7\20\2\2Z[\7\21\2\2[\\\b\t\1\2\\\21\3\2\2\2]^\7\30"+
		"\2\2^_\b\n\1\2_`\7\23\2\2`a\b\n\1\2ab\5\34\17\2bc\7\21\2\2cd\b\n\1\2d"+
		"\23\3\2\2\2ef\7\5\2\2fg\7\17\2\2gh\7\30\2\2hi\b\13\1\2ij\7\27\2\2jk\b"+
		"\13\1\2kl\t\2\2\2lm\b\13\1\2mn\7\20\2\2no\7\25\2\2oq\b\13\1\2pr\5\f\7"+
		"\2qp\3\2\2\2rs\3\2\2\2sq\3\2\2\2st\3\2\2\2tu\3\2\2\2uv\7\26\2\2v\u0082"+
		"\b\13\1\2wx\7\t\2\2xy\7\25\2\2y{\b\13\1\2z|\5\f\7\2{z\3\2\2\2|}\3\2\2"+
		"\2}{\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\7\26\2\2\u0080\u0081\b\13"+
		"\1\2\u0081\u0083\3\2\2\2\u0082w\3\2\2\2\u0082\u0083\3\2\2\2\u0083\25\3"+
		"\2\2\2\u0084\u0085\7\b\2\2\u0085\u0086\7\17\2\2\u0086\u0087\7\30\2\2\u0087"+
		"\u0088\b\f\1\2\u0088\u0089\7\27\2\2\u0089\u008a\b\f\1\2\u008a\u008b\t"+
		"\2\2\2\u008b\u008c\b\f\1\2\u008c\u008d\7\20\2\2\u008d\u008e\7\25\2\2\u008e"+
		"\u0090\b\f\1\2\u008f\u0091\5\f\7\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0095\7\26\2\2\u0095\u0096\b\f\1\2\u0096\27\3\2\2\2\u0097\u0098\7\n\2"+
		"\2\u0098\u0099\7\17\2\2\u0099\u009a\7\30\2\2\u009a\u009b\b\r\1\2\u009b"+
		"\u009c\7\23\2\2\u009c\u009d\b\r\1\2\u009d\u009e\t\2\2\2\u009e\u009f\b"+
		"\r\1\2\u009f\u00a0\7\3\2\2\u00a0\u00a1\7\30\2\2\u00a1\u00a2\b\r\1\2\u00a2"+
		"\u00a3\7\27\2\2\u00a3\u00a4\b\r\1\2\u00a4\u00a5\t\2\2\2\u00a5\u00a6\b"+
		"\r\1\2\u00a6\u00a7\7\4\2\2\u00a7\u00a8\7\30\2\2\u00a8\u00a9\b\r\1\2\u00a9"+
		"\u00aa\7\34\2\2\u00aa\u00ab\b\r\1\2\u00ab\u00ac\7\20\2\2\u00ac\u00ad\7"+
		"\25\2\2\u00ad\u00af\b\r\1\2\u00ae\u00b0\5\f\7\2\u00af\u00ae\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2"+
		"\2\2\u00b3\u00b4\7\26\2\2\u00b4\u00b5\b\r\1\2\u00b5\31\3\2\2\2\u00b6\u00b7"+
		"\7\30\2\2\u00b7\u00b8\b\16\1\2\u00b8\u00b9\7\34\2\2\u00b9\u00ba\7\21\2"+
		"\2\u00ba\u00bb\b\16\1\2\u00bb\33\3\2\2\2\u00bc\u00c2\5\36\20\2\u00bd\u00be"+
		"\7\22\2\2\u00be\u00bf\b\17\1\2\u00bf\u00c1\5\36\20\2\u00c0\u00bd\3\2\2"+
		"\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c8"+
		"\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\7\32\2\2\u00c6\u00c8\b\17\1\2"+
		"\u00c7\u00bc\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\35\3\2\2\2\u00c9\u00ca"+
		"\7\30\2\2\u00ca\u00ce\b\20\1\2\u00cb\u00cc\7\31\2\2\u00cc\u00ce\b\20\1"+
		"\2\u00cd\u00c9\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\37\3\2\2\2\17)\63<BK"+
		"s}\u0082\u0092\u00b1\u00c2\u00c7\u00cd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}