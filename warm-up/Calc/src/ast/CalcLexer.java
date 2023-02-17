// Generated from src/ast/Calc.g4 by ANTLR 4.9.1

	package ast;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalcLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PUT=1, SET=2, ASSN=3, PLUS=4, MINUS=5, TIMES=6, DIV=7, LPAR=8, RPAR=9, 
		ID=10, NUM=11, EOL=12, SPACE=13, COMMENT=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PUT", "SET", "ASSN", "PLUS", "MINUS", "TIMES", "DIV", "LPAR", "RPAR", 
			"ID", "NUM", "EOL", "SPACE", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'put'", "'set'", "'='", "'+'", "'-'", "'*'", "'/'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PUT", "SET", "ASSN", "PLUS", "MINUS", "TIMES", "DIV", "LPAR", 
			"RPAR", "ID", "NUM", "EOL", "SPACE", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public CalcLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Calc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20U\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\f\6\f9\n\f\r\f\16\f:\3\r\5\r>\n\r\3\r\3\r\3\16\6\16C\n\16\r\16\16\16"+
		"D\3\16\3\16\3\17\3\17\3\17\3\17\7\17M\n\17\f\17\16\17P\13\17\3\17\3\17"+
		"\3\17\3\17\3N\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\3\2\3\4\2\13\13\"\"\2X\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\3\37\3\2\2\2\5#\3\2\2\2\7\'\3\2\2\2\t)\3\2\2\2\13+\3\2\2\2"+
		"\r-\3\2\2\2\17/\3\2\2\2\21\61\3\2\2\2\23\63\3\2\2\2\25\65\3\2\2\2\278"+
		"\3\2\2\2\31=\3\2\2\2\33B\3\2\2\2\35H\3\2\2\2\37 \7r\2\2 !\7w\2\2!\"\7"+
		"v\2\2\"\4\3\2\2\2#$\7u\2\2$%\7g\2\2%&\7v\2\2&\6\3\2\2\2\'(\7?\2\2(\b\3"+
		"\2\2\2)*\7-\2\2*\n\3\2\2\2+,\7/\2\2,\f\3\2\2\2-.\7,\2\2.\16\3\2\2\2/\60"+
		"\7\61\2\2\60\20\3\2\2\2\61\62\7*\2\2\62\22\3\2\2\2\63\64\7+\2\2\64\24"+
		"\3\2\2\2\65\66\4c|\2\66\26\3\2\2\2\679\4\62;\28\67\3\2\2\29:\3\2\2\2:"+
		"8\3\2\2\2:;\3\2\2\2;\30\3\2\2\2<>\7\17\2\2=<\3\2\2\2=>\3\2\2\2>?\3\2\2"+
		"\2?@\7\f\2\2@\32\3\2\2\2AC\t\2\2\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2"+
		"\2\2EF\3\2\2\2FG\b\16\2\2G\34\3\2\2\2HI\7\61\2\2IJ\7\61\2\2JN\3\2\2\2"+
		"KM\13\2\2\2LK\3\2\2\2MP\3\2\2\2NO\3\2\2\2NL\3\2\2\2OQ\3\2\2\2PN\3\2\2"+
		"\2QR\5\31\r\2RS\3\2\2\2ST\b\17\2\2T\36\3\2\2\2\7\2:=DN\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}